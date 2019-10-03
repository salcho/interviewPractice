package datastructures;

import java.util.*;

import static java.util.stream.Collectors.toList;

class SingleLinkedList {
    private Item head;
    private Item tail;

    private void setHead(Item newHead) {
        if (head == null){
            head = newHead;
            return;
        }

        newHead.setNext(head);
        head = newHead;
    }

    void add(Item item) {
        if (head != null) {
            Item current = head;
            while (current.next().isPresent()) {
                current = current.next().get();
            }

            current.setNext(item);
            tail = item;
        } else {
            head = item;
            tail = item;
        }
    }

    Item head() {
        return head;
    }

    Item tail() {
        return tail;
    }

    int size() {
        int size = 0;
        if (head == null) {
            return size;
        }

        Item current = head;
        size++;
        while (current.next().isPresent()) {
            size++;
            current = current.next().get();
        }

        return size;
    }

    boolean remove(Item item) {
        if (head == null) {
            return false;
        }

        if (head.data().equals(item.data())) {
            head = head.next().orElse(null);
            if (head == null) {
                tail = null;
            }
            return true;
        }

        Item current = head;
        Item previous = current;
        while (current.next().isPresent()) {
            current = current.next().get();
            if (current.data().equals(item.data())) {
                Item next = current.next().orElse(null);
                previous.setNext(next);
                return true;
            }

            previous = previous.next().get();
        }

        return false;
    }

    Item findMiddle() {
        if (head == null) {
            return null;
        }

        Item slow = head;
        Item fast = head;
        while (fast != null) {
            fast = fast.next().orElse(null); // plusOne
            if (fast == null) {
                break;
            }
            fast = fast.next().orElse(null);
            slow = slow.next().orElse(null);
        }

        return slow;
    }

    boolean hasLoop() {
        if (head == null) {
            return false;
        }

        Item fast = head;
        Item slow = head;
        while (fast != null) {
            fast = fast.next().orElse(null);
            fast = fast != null ? fast.next().orElse(null) : null;
            if (fast == null) {
                return false;
            }

            slow = slow.next().orElse(null);
            if (fast.equals(slow)) {
                return true;
            }
        }

        return false;
    }

    Collection<Item> asCollection() {
        List<Item> list = new ArrayList<>();

        Item current = head;
        while (current != null) {
            list.add(current);
            current = current.next().orElse(null);
        }

        return list;
    }

    Collection<Item> reverseWithRecursion() {
        List<Item> reversed = new ArrayList<>();

        if (head == null) {
            return reversed;
        }

        head.reverseWithRecursion(reversed);
        return reversed;
    }

    void reverse() {
        if (head == null || !head.next().isPresent()) {
            return;
        }

        Item next = head.next().get();
        Item current = head.next().get();
        Item previous = head;
        head.setNext(null);
        tail = head;

        while (next != null) {
            next = current.next().orElse(null);
            current.setNext(previous);
            previous = current;
            current = next;
        }

        head = previous;
    }

    boolean isSortedInDescent() {
        if (head == null || !head.next().isPresent()) {
            return true;
        }

        return head.isSortedInDescent();
    }

    Item findFromEnd(int k) {
        if (head == null) {
            return null;
        }

        Item item = head;
        Item kth = head;
        int length = 0;
        while (item != null) {
            if (length > k) {
                kth = kth.next().orElse(null);
            }

            item = item.next().orElse(null);
            length++;
        }

        return kth;
    }

    void deDupWithSet() {
        if (head == null || !head.next().isPresent()) return;

        Set<Integer> values = new HashSet<>();

        Item previous = null;
        Item current = head;
        while (current.next().isPresent()) {
            Integer data = current.data();

            if (values.contains(data)) {
                previous.setNext(current.next().get());
            }

            previous = current;
            values.add(data);
            current = current.next().get();
        }

        if (previous.data().equals(current.data())) {
            tail = previous;
            previous.setNext(null);
        }
    }

    Integer kToLast(int k) {
        if (head == null) return null;

        int length = 0;
        Item current = head;
        Item target = head;
        while (current != null) {
            if (length > k) {
                target = target.next().get();
            }

            length++;
            current = current.next().orElse(null);
        }

        return target.data();
    }

    void deleteNodeInMiddle(Item node) {
        Item next = node.next().orElse(null);
        if (next == null)	return;

        node.setData(next.data());
        node.setNext(next.next().orElse(null));
    }

    void partitionAround(int data) {
        if (head == null)   return;

        SingleLinkedList low = new SingleLinkedList();
        SingleLinkedList high = new SingleLinkedList();
        Item current = head;
        while (current != null) {
            if (current.data() < data)
                low.add(new Item(current.data()));
            else if (current.data() > data)
                high.add(new Item(current.data()));
            else if (current.data() == data) {
                high.setHead(new Item(current.data()));
            }

            current = current.next().orElse(null);
        }

        low.add(high.head());
        head = low.head();
    }

    public List<Integer> asIntList() {
        return asCollection().stream().map(Item::data).collect(toList());
    }

    public boolean isPalindrome() {
        if (head == null)   return true;

        Map<Integer, Integer> seen = new HashMap<>();
        Item current = head;
        while (current != null) {
            int data = current.data();
            seen.putIfAbsent(data, 0);
            seen.put(data, seen.get(data) + 1);
            current = current.next().orElse(null);
        }

        boolean sawOne = false;
        for(int data : seen.keySet())
        {
            int freq = seen.get(data);
            if (freq == 1) {
                if (sawOne)	return false;
                else		sawOne = true;

                continue;
            }

            if (freq != 2)		return false;
        }

        return true;
    }
}
