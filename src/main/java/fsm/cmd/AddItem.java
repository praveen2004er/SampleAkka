package fsm.cmd;

import fsm.model.Item;

public class AddItem implements Command {

    private final Item item;

    public AddItem(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }
}
