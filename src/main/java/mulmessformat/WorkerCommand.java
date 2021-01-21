package mulmessformat;

import akka.actor.typed.ActorRef;

import java.io.Serializable;

public class WorkerCommand implements Serializable {
    private static final long serialVersionUID = 1L;
    private String message;
    private ActorRef<ManagerCommand> sender;

    public WorkerCommand(String message, ActorRef<ManagerCommand> sender) {
        this.message = message;
        this.sender = sender;
    }

    public String getMessage() {
        return message;

    }

    public ActorRef<ManagerCommand> getSender() {
        return sender;
    }

}
