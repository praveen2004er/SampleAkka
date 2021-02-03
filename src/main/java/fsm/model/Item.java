package fsm.model;

import java.io.Serializable;

public class Item implements Serializable {
    private final String id;
    private final String name;
    private final float price;

    public Item(String id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public float getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("Item{id=%s, name=%s, price=%s}", id, price, name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        return item.price == price && id.equals(item.id) && name.equals(item.name);
    }
}
