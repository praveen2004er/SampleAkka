package custommessage;

import akka.actor.typed.ActorSystem;

public class CustomMain {
    public static void main(String[] args) {
        ActorSystem<String> bigPrimes = ActorSystem.create(CustomManagerBehavior.create(), "bigPrimes");
        bigPrimes.tell("start");
    }
}
