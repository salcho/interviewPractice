package datastructures;

import java.util.*;

public class Graph {
    boolean[][] adjacencyList;

    public Graph(int nNodes) {
        adjacencyList = new boolean[nNodes][nNodes];
    }

    void addLink(int i, int j) {
        adjacencyList[i][j] = true;
    }

    boolean isReachableBfs(int i, int j) {
        if (i == j) return true;

        LinkedList<boolean[]> searchSpace = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        searchSpace.add(adjacencyList[i]);

        while (!searchSpace.isEmpty()) {
            boolean[] children = searchSpace.poll();
            for (int k = 0; k < children.length; k++) {
                if (visited.contains(k))    continue;

                if (children[k]) {
                    if (k == j) return true;
                    visited.add(k);
                    searchSpace.add(adjacencyList[k]);
                }
            }
        }

        return false;
    }

    public int[] posOrder() {
        return null;
    }

    Queue<Integer> findIndependentProjects() {
        Queue<Integer> independent = new LinkedList<>();
        nodes:
        for (int col = 0; col < adjacencyList[0].length; col++) {
            for (int row = 0; row < adjacencyList.length; row++) {
                if (adjacencyList[row][col]) continue nodes;
            }
            independent.add(col);
        }

        return independent;
    }
}
