import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

public class ChildSimpleBehavior extends AbstractBehavior<String> {

    private ChildSimpleBehavior(ActorContext<String> context) {
        super(context);
    }

    public static Behavior<String> create() {
        return Behaviors.setup(ChildSimpleBehavior::new);
    }

    @Override
    public Receive<String> createReceive() {
        return newReceiveBuilder()
                .onMessageEquals("path", () -> {
                    System.out.println("path = "+ getContext().getSelf().path());
                    return this;
                })
                .onMessageEquals("spawn", () -> {
                    ActorRef<String> secondActor = getContext().spawn(FirstSimpleBehaviour.create(), "actorChild");
                    secondActor.tell("path");
                    return this;
                })
                .onAnyMessage(message -> {
                    System.out.println("Message received "+message);
                    return this;
                })
                .build();
    }
}
