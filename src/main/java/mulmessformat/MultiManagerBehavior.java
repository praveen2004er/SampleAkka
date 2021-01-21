package mulmessformat;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

import java.math.BigInteger;
import java.util.SortedSet;
import java.util.TreeSet;

public class MultiManagerBehavior extends AbstractBehavior<ManagerCommand> {

    private SortedSet<BigInteger> primes = new TreeSet<>();
    private MultiManagerBehavior(ActorContext<ManagerCommand> context) {
        super(context);
    }

    public static Behavior<ManagerCommand> create() {
        return Behaviors.setup(MultiManagerBehavior::new);
    }

    @Override
    public Receive<ManagerCommand> createReceive() {
        return newReceiveBuilder()
                .onMessage(InstructionCommand.class, command -> {
                    if(command.getMessage().equals("start")) {
                        for(int i=0;i<20;i++) {
                            ActorRef<WorkerCommand> worker = getContext().spawn(MultiWorkerBehavior.create(), "worker"+i);
                            worker.tell(new WorkerCommand("start", getContext().getSelf()));
                        }
                    }
                    return this;
                })
                .onMessage(ResultCommand.class, command -> {
                    primes.add(command.getPrime());
                    System.out.println("size =" +primes.size());
                    if(primes.size() == 20) {
                        primes.forEach(System.out::println);
                    }

                    return this;
                })
                .build();
    }
}
