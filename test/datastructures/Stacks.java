package datastructures;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.Arrays.sort;
import static org.assertj.core.api.Assertions.assertThat;

public class Stacks {
    @Test
    void stackOfStacks() {
        StackOfStacks stackOfStacks = new StackOfStacks(3);

        for (int i = 0; i < 10; i++) {
            stackOfStacks.push(i);
        }
        assertThat(stackOfStacks.isEmpty()).isFalse();

        for (int i = 9; i >= 0; i--) {
            assertThat(stackOfStacks.pop()).isEqualTo(i);
        }

        assertThat(stackOfStacks.isEmpty()).isTrue();
    }

    @Test
    void popAtTest() {
        StackOfStacks stackOfStacks = new StackOfStacks(3);

        assertThat(stackOfStacks.popAt(0)).isEqualTo(-1);

        for (int i = 0; i < 10; i++) {
            stackOfStacks.push(i);
        }

        stackOfStacks.popAt(5);

        assertThat(stackOfStacks.asList()).isEqualTo(asList(0, 1, 2, 3, 4, 6, 7, 8, 9));
    }

    @Test
    void findNthMinimum() {
        assertThat(getMin(new int[]{1, 2, 3, 4, 5, 6}, 1)).isEqualTo(1);
        assertThat(getMin(new int[]{1, 2, 3, 4, 5, 6}, 2)).isEqualTo(2);
        assertThat(getMin(new int[]{1, 2, 3, 4, 5, 6}, 3)).isEqualTo(3);
        assertThat(getMin(new int[]{1, 2, 3, 4, 5, 6}, 4)).isEqualTo(4);
        assertThat(getMin(new int[]{1, 2, 3, 4, 5, 6}, 5)).isEqualTo(5);
        assertThat(getMin(new int[]{1, 2, 3, 4, 5, 6}, 6)).isEqualTo(6);

        assertThat(getMin(new int[]{3, 2, 10, 21, 15, 36}, 1)).isEqualTo(2);
        assertThat(getMin(new int[]{3, 2, 10, 21, 15, 36}, 2)).isEqualTo(3);
        assertThat(getMin(new int[]{3, 2, 10, 21, 15, 36}, 3)).isEqualTo(10);
        assertThat(getMin(new int[]{3, 2, 10, 21, 15, 36}, 4)).isEqualTo(15);
        assertThat(getMin(new int[]{3, 2, 10, 21, 15, 36}, 5)).isEqualTo(21);
        assertThat(getMin(new int[]{3, 2, 10, 21, 15, 36}, 6)).isEqualTo(36);
    }

    private int getMin(int[] values, int m) {
        int pivot = values[0];
        int start = 0;
        int end = values.length - 1;
        int[] partitioned = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            if (values[i] > pivot){
                partitioned[end--] = values[i];
            } else if (values[i] < pivot) {
                partitioned[start++] = values[i];
            }
        }

        for (int i = start; i < end + 1; i++) {
            partitioned[i] = pivot;
        }

        if (m > start + 1) {
            return getMin(Arrays.copyOfRange(values, start + 1, partitioned.length), m - (start + 1));
        }
        if (m < start + 1) {
            return getMin(Arrays.copyOfRange(partitioned, 0, start), m);
        }

        return partitioned[start];
    }

    @Test
    void sortStackTest() {
        Stack<Integer> stack = new Stack<>();
        stack.push(3);
        stack.push(2);
        stack.push(21);
        stack.push(10);
        stack.push(3);
        stack.push(16);
        stack.push(-10);
        stack.push(36);

        Stack<Integer> sorted = sortStack(stack);

        List<Integer> elements = new ArrayList<>();
        while (!sorted.isEmpty()) {
            elements.add(sorted.pop());
        }

        assertThat(elements).isEqualTo(asList(-10, 2, 3, 3, 10, 16, 21, 36));
    }

    private Stack<Integer> sortStack(Stack<Integer> stack) {
        if (stack.isEmpty())	return stack;

        Stack<Integer> sorted = new Stack<>();

        sorted.push(stack.pop());
        if (stack.isEmpty())	return sorted;

        while (!stack.isEmpty()) {
            int value = stack.pop();
            int rewind = 0;
            while (!sorted.isEmpty()) {
                int sortedValue = sorted.pop();
                if (value > sortedValue) {
                    stack.push(sortedValue);
                    rewind++;
                    continue;
                }
                if (value <= sortedValue) {
                    sorted.push(sortedValue);
                    sorted.push(value);
                    break;
                }
            }

            while(rewind != 0) {
                sorted.push(stack.pop());
                rewind--;
            }
        }

        return sorted;
    }

    @Test
    void animalShelterTest() {
        AnimalShelter animalShelter = new AnimalShelter();

        animalShelter.enqueue(new Dog(1));
        animalShelter.enqueue(new Dog(2));
        animalShelter.enqueue(new Dog(3));
        animalShelter.enqueue(new Dog(4));
        animalShelter.enqueue(new Cat(1));
        animalShelter.enqueue(new Cat(2));
        animalShelter.enqueue(new Cat(3));

        Animal oldestAnimal = animalShelter.dequeueAny();
        assertThat(oldestAnimal).isInstanceOf(Dog.class);
        assertThat(oldestAnimal.age).isEqualTo(1);

        Dog dog = animalShelter.dequeueDog();
        assertThat(dog.age).isEqualTo(2);

        Cat cat = animalShelter.dequeueCat();
        assertThat(cat.age).isEqualTo(1);

        oldestAnimal = animalShelter.dequeueAny();
        assertThat(oldestAnimal).isInstanceOf(Dog.class);
        assertThat(oldestAnimal.age).isEqualTo(3);
    }
}
