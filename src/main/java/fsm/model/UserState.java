package fsm.model;

import akka.persistence.fsm.PersistentFSM;

public enum UserState implements PersistentFSM.FSMState {
    LOOKING_AROUND("Looking Around"),
    SHOPPING("Shopping"),
    INACTIVE("Inactive"),
    PAID("Paid");

    private final String stateIdentifier;

    UserState(String stateIdentifier) {
        this.stateIdentifier = stateIdentifier;
    }

    @Override
    public String identifier() {
        return stateIdentifier;
    }
}
