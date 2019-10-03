package datastructures;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

class Item {
    private Integer data;
    private Item next;

    Item(Integer data) {
        this.data = data;
    }

    Optional<Item> next() {
        return next != null ? Optional.of(next) : Optional.empty();
    }

    void setNext(Item item) {
        this.next = item;
    }

    void setData(Integer data) {
        this.data = data;
    }

    Integer data() {
        return data;
    }

    @Override
    public String toString() {
        return "Item{" +
                "data=" + data +
                ", next=" + next +
                '}';
    }

    void reverseWithRecursion(Collection<Item> reversed) {
        if (next != null) {
            next.reverseWithRecursion(reversed);
        }

        reversed.add(this);
    }

    boolean isSortedInDescent() {
        if (next != null && next.data().compareTo(data) > 0) {
            return false;
        }

        if (next == null) {
            return true;
        }

        return next.isSortedInDescent();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(data, item.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }
}
