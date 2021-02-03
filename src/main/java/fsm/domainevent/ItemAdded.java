package fsm.domainevent;

import fsm.model.Item;

public class ItemAdded implements DomainEvent {
    private final Item item;

    public ItemAdded(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }
}
