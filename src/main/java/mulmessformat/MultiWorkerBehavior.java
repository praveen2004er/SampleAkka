package mulmessformat;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

import java.math.BigInteger;
import java.util.Random;

public class MultiWorkerBehavior extends AbstractBehavior<WorkerCommand> {
    private MultiWorkerBehavior(ActorContext<WorkerCommand> context) {
        super(context);
    }

    public static Behavior<WorkerCommand> create() {
        return Behaviors.setup(MultiWorkerBehavior::new);
    }

    @Override
    public Receive<WorkerCommand> createReceive() {
        return newReceiveBuilder()
                .onAnyMessage(command -> {
                    if(command.getMessage().equals("start")) {
                        BigInteger bigInteger = new BigInteger(2000, new Random());
                        command.getSender().tell(new ResultCommand(bigInteger.nextProbablePrime()));
                    }
                    return this;
                })

                .build();
    }
}
