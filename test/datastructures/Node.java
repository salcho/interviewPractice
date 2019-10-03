package datastructures;

import java.util.ArrayList;
import java.util.List;

class Node {
    int value;
    Node left;
    Node right;

    Node(int value) {
        this.value = value;
    }

    List<Integer> inOrderSearch() {
        List<Integer> values = new ArrayList<>();
        if (left != null)   values.addAll(left.inOrderSearch());
        values.add(value);
        if (right != null)  values.addAll(right.inOrderSearch());

        return values;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    boolean isBalanced() {
        int leftSize = left == null ? 0 : left.size();
        int rightSize = right == null ? 0 : right.size();
        return Math.abs(leftSize - rightSize) < 2;
    }

    int size() {
        int leftCount = 0;
        int rightCount = 0;

        if (left != null) {
            leftCount = left.size();
        }
        if (right != null) {
            rightCount = right.size();
        }

        return leftCount + rightCount + 1;
        }
}
