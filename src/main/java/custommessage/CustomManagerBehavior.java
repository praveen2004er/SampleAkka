package custommessage;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

public class CustomManagerBehavior extends AbstractBehavior<String> {


    private CustomManagerBehavior(ActorContext<String> context) {
        super(context);
    }

    public static Behavior<String> create() {
        return Behaviors.setup(CustomManagerBehavior::new);
    }

    @Override
    public Receive<String> createReceive() {

        return newReceiveBuilder()
                .onMessageEquals("start", () -> {
                    for(int i=0;i<20;i++) {
                        ActorRef<CustomWorkerBehaviour.Command> worker = getContext().spawn(CustomWorkerBehaviour.create(), "worker"+i);
                        worker.tell(new CustomWorkerBehaviour.Command("start", getContext().getSelf()));
                    }
                    return this;
                })

                .build();
    }
}
