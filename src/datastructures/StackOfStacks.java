package datastructures;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class StackOfStacks {
    private final int maxCapacity;
    private Stack<Stack<Integer>> stacks = new Stack<>();

    public StackOfStacks(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    void push(int value) {
        if (isEmpty()) {
            Stack<Integer> newStack = new Stack<>();
            stacks.push(newStack);
            newStack.push(value);
            return;
        }

        if (stacks.peek().size() + 1 > maxCapacity) {  // overflow
            Stack<Integer> newStack = new Stack<>();
            newStack.add(value);
            stacks.add(newStack);
        }
        else
        {
            stacks.peek().push(value);
        }
    }

    int pop() {
        if (stacks.peek().size() - 1 == 0) {        // empty
            Stack<Integer> oldStack = stacks.pop();
            return oldStack.pop();
        }

        return stacks.peek().pop();
    }

    public boolean isEmpty() {
        return stacks.isEmpty();
    }

    public List<Integer> asList() {
        List<Integer> values = new ArrayList<>();
        for (Stack<Integer> stack : stacks) {
            values.addAll(stack);
        }

        return values;
    }

    public int popAt(int index) {
        int stackNumber = index % maxCapacity;
        for (int i = 0; i < stacks.size(); i++) {
            if (i == stackNumber - 1) {
                Stack<Integer> s = stacks.get(i);
                return s.pop();
            }
        }

        return -1;
    }
}
