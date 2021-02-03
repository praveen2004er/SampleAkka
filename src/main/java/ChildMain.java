import akka.actor.typed.ActorSystem;

public class ChildMain {
    public static void main(String[] args) {
        ActorSystem<String> actorSystem = ActorSystem.create(ChildSimpleBehavior.create(), "ChildActorSystem");
        actorSystem.tell("Hello World!");
        actorSystem.tell("Hello World! again");
        actorSystem.tell("path"); //parent actor path
        actorSystem.tell("spawn"); // creates child actor
    }
}
