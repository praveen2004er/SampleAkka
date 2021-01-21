import akka.actor.typed.ActorSystem;

public class PrimeMain {
    public static void main(String[] args) {
        ActorSystem<String> actorSystem = ActorSystem.create(ManagerBehavior.create(), "PrimeSystem");


        actorSystem.tell("start");
    }
}
