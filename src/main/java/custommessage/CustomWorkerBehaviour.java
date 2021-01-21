package custommessage;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Random;

public class CustomWorkerBehaviour extends AbstractBehavior<CustomWorkerBehaviour.Command> {
    public static class Command implements Serializable {
        private static final long serialVersionUID = 1L;
        private String message;
        private ActorRef<String> sender;

        public Command(String message, ActorRef<String> sender) {
            this.message = message;
            this.sender = sender;
        }

        public String getMessage() {
            return message;
        }

        public ActorRef<String> getSender() {
            return sender;
        }
    }

    private CustomWorkerBehaviour(ActorContext<Command> context) {
        super(context);
    }

    public static Behavior<CustomWorkerBehaviour.Command> create() {
        return Behaviors.setup(CustomWorkerBehaviour::new);
    }

    @Override
    public Receive<Command> createReceive() {
        return newReceiveBuilder()
                .onAnyMessage(command -> {
                    if(command.getMessage().equals("start")) {
                        BigInteger bigInteger = new BigInteger(2000, new Random());
                        System.out.println(bigInteger.nextProbablePrime());
                    }
                    return this;
                })
                .build();
    }
}
