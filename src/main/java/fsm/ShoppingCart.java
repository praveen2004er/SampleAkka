package fsm;

import fsm.model.Item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShoppingCart {
    private final List<Item> items = new ArrayList<>();

    public ShoppingCart(Item initialItem) {
        items.add(initialItem);
    }

    public ShoppingCart() {}

    public List<Item> getItems() {
        return Collections.unmodifiableList(items);
    }

    public ShoppingCart addItem(Item item) {
        items.add(item);
        return this;
    }

    public void empty() {
        items.clear();
    }
}
