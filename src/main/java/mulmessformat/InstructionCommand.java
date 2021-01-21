package mulmessformat;

public class InstructionCommand implements ManagerCommand {
    public static final long serialVersionUID = 1L;

    private String message;

    public InstructionCommand(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
