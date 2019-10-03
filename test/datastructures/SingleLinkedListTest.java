package datastructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;

class SingleLinkedListTest {

    private SingleLinkedList linkedList;
    private LinkedList<Item> javaLinkedList;

    @BeforeEach
    void setUp() {
        linkedList = new SingleLinkedList();
        javaLinkedList = new LinkedList<>();
    }

    @Test
    void removeDups() {
        Item item0 = new Item(0);
        addToBoth(item0);
        addToBoth(new Item(0));

        Item item1 = new Item(1);
        addToBoth(item1);

        Item item2 = new Item(2);
        addToBoth(item2);

        addToBoth(new Item(1));

        Item item3 = new Item(3);
        addToBoth(item3);
        addToBoth(new Item(3));

        linkedList.deDupWithSet();

        assertThat(linkedList.asCollection()).isEqualTo(asList(item0, item1, item2, item3));
    }

    @Test
    void returnKToLastTest() {
        Item item0 = new Item(0);
        addToBoth(item0);

        Item item1 = new Item(1);
        addToBoth(item1);

        Item item2 = new Item(2);
        addToBoth(item2);

        Item item3 = new Item(3);
        addToBoth(item3);

        for (int k = 0; k < 4; k++) {
            assertThat(linkedList.kToLast(k)).isEqualTo(3 - k);
        }
    }

    @Test
    public void adding() {
        assertThat(linkedList.head()).isNull();

        Item item0 = new Item(0);

        addToBoth(item0);
        addToBoth(new Item(1));
        addToBoth(new Item(2));

        Item item3 = new Item(3);
        addToBoth(item3);

        assertThat(linkedList.head()).isEqualTo(item0);
        assertThat(javaLinkedList.getFirst()).isEqualTo(item0);

        assertThat(linkedList.tail()).isEqualTo(item3);
        assertThat(javaLinkedList.getLast()).isEqualTo(item3);
    }

    @Test
    public void size() {
        addToBoth(new Item(0));
        addToBoth(new Item(1));
        addToBoth(new Item(2));

        assertThat(javaLinkedList.size()).isEqualTo(3);
        assertThat(linkedList.size()).isEqualTo(3);
    }

    @Test
    void removeEmpty() {
        Item item = new Item(1);
        assertThat(linkedList.remove(item)).isFalse();
        assertThat(javaLinkedList.remove(item)).isFalse();

        assertThat(linkedList.tail()).isNull();
    }

    @Test
    void removeHeadEmpty() {
        Item item = new Item(1);
        addToBoth(item);

        assertThat(linkedList.size()).isEqualTo(1);
        assertThat(linkedList.tail()).isEqualTo(item);
        assertThat(linkedList.remove(item)).isTrue();
        assertThat(linkedList.size()).isEqualTo(0);
        assertThat(linkedList.tail()).isNull();
    }

    @Test
    void removeHead() {
        Item item = new Item(1);
        addToBoth(item);

        Item item1 = new Item(2);
        addToBoth(item1);

        assertThat(linkedList.size()).isEqualTo(2);
        assertThat(linkedList.tail()).isEqualTo(item1);
        assertThat(linkedList.remove(item)).isTrue();
        assertThat(linkedList.size()).isEqualTo(1);
        assertThat(linkedList.head()).isEqualTo(item1);
        assertThat(linkedList.tail()).isEqualTo(item1);
    }

    @Test
    void remove() {
        Item item0 = new Item(0);
        addToBoth(item0);
        Item item1 = new Item(1);
        addToBoth(item1);
        Item item2 = new Item(2);
        addToBoth(item2);
        Item item3 = new Item(3);
        addToBoth(item3);
        Item item4 = new Item(4);
        addToBoth(item4);
        Item item5 = new Item(5);
        addToBoth(item5);

        assertThat(linkedList.size()).isEqualTo(6);
        assertThat(linkedList.remove(item1)).isTrue();
        assertThat(linkedList.size()).isEqualTo(5);

        assertThat(linkedList.asCollection()).isEqualTo(asList(item0, item2, item3, item4, item5));
        assertThat(linkedList.tail()).isEqualTo(item5);
    }

    @Test
    void findMiddleSingle() {
        Item item0 = new Item(0);
        addToBoth(item0);

        assertThat(linkedList.findMiddle()).isEqualTo(item0);
    }

    @Test
    void deleteNodeInMiddleTest() {
        Item item0 = new Item(0);
        addToBoth(item0);
        Item item1 = new Item(1);
        addToBoth(item1);
        Item item2 = new Item(2);
        addToBoth(item2);
        Item item3 = new Item(3);
        addToBoth(item3);
        Item item4 = new Item(4);
        addToBoth(item4);
        Item item5 = new Item(5);
        addToBoth(item5);

        linkedList.deleteNodeInMiddle(item3);
        linkedList.deleteNodeInMiddle(item5);

        assertThat(linkedList.asCollection()).isEqualTo(asList(item0, item1, item2, item4, item5));
    }

    @Test
    void partitionTest() {
        addToBoth(new Item(1));
        addToBoth(new Item(4));
        addToBoth(new Item(3));
        addToBoth(new Item(2));
        addToBoth(new Item(5));
        addToBoth(new Item(2));
        addToBoth(new Item(3));

        linkedList.partitionAround(3);

        assertThat(linkedList.asIntList()).isEqualTo(asList(1, 2, 2, 3, 3, 4, 5));
    }

    @Test
    void sumListsTest() {
        linkedList.add(new Item(1));
        linkedList.add(new Item(2));
        linkedList.add(new Item(3));
        linkedList.add(new Item(4));

        SingleLinkedList secondLinkedList = new SingleLinkedList();
        secondLinkedList.add(new Item(1));
        secondLinkedList.add(new Item(2));
        secondLinkedList.add(new Item(3));
        secondLinkedList.add(new Item(4));

        assertThat(sumLists(linkedList, secondLinkedList).asIntList()).isEqualTo(asList(2, 4, 6, 8));

        linkedList = new SingleLinkedList();
        linkedList.add(new Item(9));
        linkedList.add(new Item(9));
        linkedList.add(new Item(9));
        linkedList.add(new Item(9));

        secondLinkedList = new SingleLinkedList();
        secondLinkedList.add(new Item(1));

        assertThat(sumLists(linkedList, secondLinkedList).asIntList()).isEqualTo(asList(0, 0, 0, 0, 1));
    }

    @Test
    void isPalindromeTest() {
        linkedList.add(new Item(1));
        linkedList.add(new Item(2));
        linkedList.add(new Item(3));
        linkedList.add(new Item(2));
        linkedList.add(new Item(1));

        assertThat(linkedList.isPalindrome()).isTrue();

        linkedList = new SingleLinkedList();
        linkedList.add(new Item(1));
        linkedList.add(new Item(2));
        linkedList.add(new Item(3));
        linkedList.add(new Item(1));
        linkedList.add(new Item(2));
        linkedList.add(new Item(3));

        assertThat(linkedList.isPalindrome()).isFalse();
    }

    private SingleLinkedList sumLists(SingleLinkedList listOne, SingleLinkedList listTwo) {
        int firstNumber = 0;
        int secondNumber = 0;
        int multiplier = 1;

        Item itemOne = listOne.head();
        while (itemOne != null) {
            firstNumber += itemOne.data() * multiplier;
            multiplier *= 10;
            itemOne = itemOne.next().orElse(null);
        }

        multiplier = 1;
        Item itemTwo = listTwo.head();
        while (itemTwo != null) {
            secondNumber += itemTwo.data() * multiplier;
            multiplier *= 10;
            itemTwo = itemTwo.next().orElse(null);
        }

        int result = firstNumber + secondNumber;
        SingleLinkedList list = new SingleLinkedList();

        while (result != 0) {
            int lowest = result % 10;
            list.add(new Item(lowest));
            result /= 10;
        }

        return list;
    }

    @Test
    void findMiddleEven() {
        Item item0 = new Item(0);
        addToBoth(item0);
        Item item1 = new Item(1);
        addToBoth(item1);
        Item item2 = new Item(2);
        addToBoth(item2);
        Item item3 = new Item(3);
        addToBoth(item3);
        Item item4 = new Item(4);
        addToBoth(item4);
        Item item5 = new Item(5);
        addToBoth(item5);

        assertThat(linkedList.size()).isEqualTo(6);
        assertThat(linkedList.findMiddle()).isEqualTo(item3);
    }

    @Test
    void findMiddleOdd() {
        Item item0 = new Item(0);
        addToBoth(item0);
        Item item1 = new Item(1);
        addToBoth(item1);
        Item item2 = new Item(2);
        addToBoth(item2);
        Item item3 = new Item(3);
        addToBoth(item3);
        Item item4 = new Item(4);
        addToBoth(item4);

        assertThat(linkedList.size()).isEqualTo(5);
        assertThat(linkedList.findMiddle()).isEqualTo(item2);
    }

    @Test
    void findLoopOneElement() {
        assertThat(linkedList.hasLoop()).isFalse();

        Item item0 = new Item(0);
        addToBoth(item0);
        addToBoth(item0);

        assertThat(linkedList.hasLoop()).isTrue();
    }

    @Test
    void findLoop() {
        Item item0 = new Item(0);
        addToBoth(item0);
        Item item1 = new Item(1);
        addToBoth(item1);
        Item item2 = new Item(2);
        addToBoth(item2);
        Item item3 = new Item(3);
        addToBoth(item3);
        Item item4 = new Item(4);
        addToBoth(item4);

        assertThat(linkedList.hasLoop()).isFalse();
        addToBoth(item2);
        assertThat(linkedList.hasLoop()).isTrue();
    }

    @Test
    void reverseWithRecursion() {
        assertThat(linkedList.reverseWithRecursion()).isEqualTo(emptyList());
        Item item0 = new Item(0);
        addToBoth(item0);
        Item item1 = new Item(1);
        addToBoth(item1);
        Item item2 = new Item(2);
        addToBoth(item2);
        Item item3 = new Item(3);
        addToBoth(item3);
        Item item4 = new Item(4);
        addToBoth(item4);

        assertThat(linkedList.reverseWithRecursion()).isEqualTo(asList(item4, item3, item2, item1, item0));
    }

    @Test
    void reverse() {
        Item item0 = new Item(0);
        addToBoth(item0);
        Item item1 = new Item(1);
        addToBoth(item1);
        Item item2 = new Item(2);
        addToBoth(item2);
        Item item3 = new Item(3);
        addToBoth(item3);
        Item item4 = new Item(4);
        addToBoth(item4);

        linkedList.reverse();
        assertThat(linkedList.asCollection()).isEqualTo(asList(item4, item3, item2, item1, item0));
    }

    @Test
    void isSortedInDescent() {
        Item item0 = new Item(0);
        addToBoth(item0);
        Item item1 = new Item(1);
        addToBoth(item1);
        Item item2 = new Item(2);
        addToBoth(item2);
        Item item3 = new Item(2);
        addToBoth(item3);
        Item item4 = new Item(4);
        addToBoth(item4);

        assertThat(linkedList.isSortedInDescent()).isFalse();

        linkedList.reverse();
        assertThat(linkedList.isSortedInDescent()).isTrue();
    }

    @Test
    void findKth() {
        List<Item> items = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Item item = new Item(i);
            addToBoth(item);
            items.add(item);
        }

        for (int i = 0; i < 5; i++) {
            assertThat(linkedList.findFromEnd(i)).isEqualTo(items.get(items.size() - 1 - i));
        }
    }

    void addToBoth(Item item) {
        linkedList.add(item);
        javaLinkedList.add(item);
    }
}