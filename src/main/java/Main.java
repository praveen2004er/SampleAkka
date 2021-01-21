import akka.actor.typed.ActorSystem;

public class Main {
    public static void main(String[] args) {
       ActorSystem<String> actorSystem = ActorSystem.create(FirstSimpleBehaviour.create(), "FirstActorSystem");
       actorSystem.tell("Hello World!");
       actorSystem.tell("Hello World! again");
       actorSystem.tell("path");
    }
}
