package mulmessformat;

import akka.actor.typed.ActorSystem;

public class MultipleFormatMain {
    public static void main(String[] args) {
        ActorSystem<ManagerCommand> bigPrimes = ActorSystem.create(MultiManagerBehavior.create(), "bigPrimes");
        bigPrimes.tell(new InstructionCommand("start"));
    }
}
