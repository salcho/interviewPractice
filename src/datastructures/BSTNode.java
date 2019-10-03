package datastructures;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.Collections.singletonList;

class BSTNode {
    private int value;
    private BSTNode leftNode;
    private BSTNode rightNode;
    // see datastructures.Blah.connectNodesAtSameLevelTest
    private BSTNode nextRight;

    private BSTNode(int value){
        this.value = value;
    }

    static BSTNode makeBst(int[] values) {
        if (values == null || values.length == 0)	return null;

        BSTNode root = new BSTNode(values[0]);
        Arrays.stream(values).skip(1).forEach(root::add);
        return root;
    }

    void add(int thatValue) {
        if (thatValue > value) {
            if (rightNode == null) {
                rightNode = new BSTNode(thatValue);
            }
            else {
                rightNode.add(thatValue);
            }
        }
        else {
            if (leftNode == null) {
                leftNode = new BSTNode(thatValue);
            } else {
                leftNode.add(thatValue);
            }
        }
    }

    int numberOfNodesWithinRange(int from, int to) {
        int count = 0;
        if (value >= to) {
            if (value == to)    count++;
            if (leftNode != null)
            {
                count += leftNode.numberOfNodesWithinRange(from, to);
            }
        }
        else if (value <= from)
        {
            if (value == from)  count++;
            if (rightNode != null) {
                count += rightNode.numberOfNodesWithinRange(from, to);
            }
        }
        else
        {
            count++;
            if (leftNode != null) {
                count += leftNode.numberOfNodesWithinRange(from, to);
            }
            if (rightNode != null) {
                count += rightNode.numberOfNodesWithinRange(from, to);
            }
        }

        return count;
    }

    int countSumOfPathsFromRootToLeaves() {
        AtomicInteger count = new AtomicInteger();
        if (leftNode != null) {
            leftNode.countSumOfPathsFromRootToLeaves(value).forEach(count::addAndGet);
        }
        if (rightNode != null) {
            rightNode.countSumOfPathsFromRootToLeaves(value).forEach(count::addAndGet);
        }
        return count.get();
    }

    private List<Integer> countSumOfPathsFromRootToLeaves(int previous) {
        int count = (previous * 10) + value;

        List<Integer> values = new ArrayList<>();
        if (leftNode != null) {
            values.addAll(leftNode.countSumOfPathsFromRootToLeaves(count));
        }
        if (rightNode != null) {
            values.addAll(rightNode.countSumOfPathsFromRootToLeaves(count));
        }

        return values.isEmpty() ? singletonList(count) : values;
    }

    void connectNodesAtSameLevel() {
        Queue<Tuple<BSTNode, Integer>> queue = new LinkedList<>();
        queue.add(new Tuple<>(this, 0));

        while (!queue.isEmpty()) {
            Tuple<BSTNode, Integer> current = queue.poll();
            Tuple<BSTNode, Integer> next = queue.peek();
            if (next == null || !next.right.equals(current.right)) {
                current.left.nextRight = null;
            } else {
                current.left.nextRight = queue.peek().left;
            }

            if (current.left.leftNode != null)	queue.add(new Tuple<>(current.left.leftNode, current.right + 1));
            if (current.left.rightNode != null)	queue.add(new Tuple<>(current.left.rightNode, current.right + 1));
        }
    }

    List<Integer> levelOrder() {
        List<Integer> levelOrder = new ArrayList<>();
        levelOrder(levelOrder);

        BSTNode current = this.leftNode;
        while (current != null) {
            current.levelOrder(levelOrder);
            current = current.leftNode;
        }

        return levelOrder;
    }

    private void levelOrder(List<Integer> levelOrder) {
        levelOrder.add(value);
        if (nextRight != null)	nextRight.levelOrder(levelOrder);
    }

    List<Integer> inOrder() {
        List<Integer> values = new ArrayList<>();
        inOrder(values);
        return values;
    }

    private void inOrder(List<Integer> values) {
        if (leftNode != null)	leftNode.inOrder(values);
        values.add(value);
        if (rightNode != null)	rightNode.inOrder(values);
    }

    Optional<Integer> findKthSmallest(int k) {
        AtomicInteger count = new AtomicInteger(0);
        return findKthSmallest(k, count);
    }

    private Optional<Integer> findKthSmallest(int k, AtomicInteger count) {
        if (leftNode != null) {
            Optional<Integer> leftCount = leftNode.findKthSmallest(k, count);
            if (count.get() == k)	return leftCount;
        }

        count.incrementAndGet();
        if (count.get() == k)	return Optional.of(value);

        if (rightNode != null) {
            Optional<Integer> rightCount = rightNode.findKthSmallest(k, count);
            if (count.get() == k)	return rightCount;
        }

        return Optional.empty();
    }

    @Override
    public String toString() {
        return value + "";
    }
}
