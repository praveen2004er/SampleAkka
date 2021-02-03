package fsm;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import fsm.cmd.AddItem;
import fsm.model.Item;

import java.util.Scanner;

public class FSMMain {

    public static void main(String[] args) {
        ActorSystem SYSTEM = ActorSystem.create();
        Props props = Props.create(FSM.class);
        ActorRef cartActor = SYSTEM.actorOf(props, "sampleCart");
        cartActor.tell(new AddItem(new Item("milk", "milk", 3.00f)), cartActor);
        System.out.println("Message sent");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        System.out.println("done");
     }
}
