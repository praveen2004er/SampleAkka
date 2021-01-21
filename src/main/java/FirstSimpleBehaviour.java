import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

public class FirstSimpleBehaviour extends AbstractBehavior<String> {

    private FirstSimpleBehaviour(ActorContext<String> context) {
        super(context);
    }

    public static Behavior<String> create() {
        return Behaviors.setup(FirstSimpleBehaviour::new);
    }

    public Receive<String> createReceive() {
        return newReceiveBuilder()
                .onMessageEquals("path", () -> {
                    System.out.println("path = "+ getContext().getSelf().path());
                    return this;
                })
                .onAnyMessage(message -> {
                    System.out.println("Message received "+message);
                    return this;
                })
                .build();
    }
}
