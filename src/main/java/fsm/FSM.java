package fsm;

import akka.persistence.fsm.AbstractPersistentLoggingFSM;
import fsm.cmd.AddItem;
import fsm.cmd.GetCurrentCart;
import fsm.domainevent.DomainEvent;
import fsm.domainevent.ItemAdded;
import fsm.model.UserState;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class FSM extends AbstractPersistentLoggingFSM<UserState, ShoppingCart, DomainEvent> {

    private void FSM() {

        startWith(UserState.INACTIVE, new ShoppingCart());
    }


    {
        when(
                UserState.LOOKING_AROUND,
                matchEvent(
                        AddItem.class,
                        (event, data) ->
                                goTo(UserState.SHOPPING)
                                        .applying(new ItemAdded(event.getItem()))
                                        .forMax(Duration.ofSeconds(1)))
                        .event(GetCurrentCart.class, (event, data) -> stay().replying(data)));
    }

    @Override
    public void preStart() throws Exception {
        super.preStart();
        getContext().setReceiveTimeout(scala.concurrent.duration.Duration.create(15, TimeUnit.MINUTES));
    }

    @Override
    public String persistenceId() {
        return getSelf().path().name();
    }


    @Override
    public Class domainEventClass() {
        return DomainEvent.class;
    }

    @Override
    public ShoppingCart applyEvent(DomainEvent domainEvent, ShoppingCart currentData) {
        return currentData;
    }
}
