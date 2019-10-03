package datastructures;

import org.junit.jupiter.api.Test;

import java.util.*;

import static datastructures.GraphsAndTrees.Tuple.of;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeFalse;

public class GraphsAndTrees {
    @Test
    void testBfs() {
        Graph graph = new Graph(5);

        graph.addLink(0, 1);
        graph.addLink(1, 2);
        graph.addLink(2, 3);
        graph.addLink(3, 4);
        graph.addLink(3, 1);

        assertThat(graph.isReachableBfs(0, 0)).isTrue();
        assertThat(graph.isReachableBfs(0, 4)).isTrue();
        assertThat(graph.isReachableBfs(2, 0)).isFalse();
        assertThat(graph.isReachableBfs(2, 1)).isTrue();
    }

    @Test
    void minimalTree() {
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Node root = makeBinarySearchTree(values);
        assertThat(root.inOrderSearch()).containsExactly(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    }

    @Test
    void checkIfBalancedTest() {
        Node node = makeBinarySearchTree(new int[]{1, 2, 3, 4, 5, 6, 7});
        assertThat(node.isBalanced()).isTrue();

        node = new Node(1);
        node.left = new Node(2);
        node.left.left = new Node(3);

        assertThat(node.isBalanced()).isFalse();
    }

    @Test
    void dependenciesTree() {
        int[] projects = new int[]{ 1, 2, 3, 4, 5};
        Tuple[] dependencies = new Tuple[] { of(1, 2), of(2, 3), of(3, 4), of(4, 5) };
        Graph graph = buildGraph(projects, dependencies);
        List<Integer> buildOrder = topologicalSort(graph);
        assertThat(buildOrder).containsExactly(1, 2, 3, 4, 5);

        dependencies = new Tuple[] { of(1, 2), of(2, 3), of(2, 4), of(3, 5), of(4, 5) };
        graph = buildGraph(projects, dependencies);
        buildOrder = topologicalSort(graph);
        assertThat(buildOrder).containsExactly(1, 2, 3, 4, 5);

        dependencies = new Tuple[] { of(1, 2), of(1, 3), of(1, 4), of(2, 5), of(3, 5), of (4, 5) };
        graph = buildGraph(projects, dependencies);
        buildOrder = topologicalSort(graph);
        assertThat(buildOrder).containsExactly(1, 2, 3, 4, 5);

        dependencies = new Tuple[] { of(5, 3), of(4, 2), of(3, 1), of(2, 1) };
        graph = buildGraph(projects, dependencies);
        buildOrder = topologicalSort(graph);
        assertThat(buildOrder).containsExactly(4, 5, 2, 3, 1);
    }

    private List<Integer> topologicalSort(Graph graph) {
        // find starting edges
        Queue<Integer> leafs = graph.findIndependentProjects();
        if (leafs.isEmpty()) {
            return null;  // circular dependency
        }

        List<Integer> buildOrder = new LinkedList<>();
        int doneProjs = 0;
        nextProj:
        while (!leafs.isEmpty()) {
            int proj = leafs.poll();

            buildOrder.add(proj + 1);
            if (++doneProjs == graph.adjacencyList.length)  break;

            for (int i = 0; i < graph.adjacencyList[proj].length; i++) {
                if (graph.adjacencyList[proj][i]) {
                    graph.adjacencyList[proj][i] = false;
                    for (int row = 0; row < graph.adjacencyList.length; row++) {
                        if (graph.adjacencyList[row][i])	continue nextProj;
                    }
                    leafs.add(i);
                }
            }
        }

        return buildOrder;
    }

    private Graph buildGraph(int[] projects, Tuple[] dependencies) {
        Graph graph = new Graph(projects.length);

        for (Tuple dependency : dependencies) {
            graph.addLink(dependency.left - 1, dependency.right - 1);
        }

        return graph;
    }

    static class Tuple {
        final int left;
        final int right;

        Tuple(int left, int right) {
            this.left = left;
            this.right = right;
        }

        static Tuple of(int left, int right) {
            return new Tuple(left, right);
        }
    }

    @Test
    void allNodesAtEachDepth() {
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Node root = makeBinarySearchTree(values);
        List<List<Node>> levels = listPerLevel(root);
        System.out.println(levels);
    }

    private List<List<Node>> listPerLevel(Node root) {
        List<List<Node>> levels = new ArrayList<>();
        LinkedList<List<Node>> searchSpace = new LinkedList<>();
        searchSpace.add(singletonList(root));

        while (!searchSpace.isEmpty()) {
            List<Node> poll = searchSpace.poll();
            levels.add(poll);

            for (Node node : poll) {
                List<Node> children = new ArrayList<>(2);
                if (node.left != null) children.add(node.left);
                if (node.right != null) children.add(node.right);

                searchSpace.add(children);
            }
        }

        return levels;
    }

    private Node makeBinarySearchTree(int[] values) {
        return makeBinarySearchTree(values, null);
    }

    private Node makeBinarySearchTree(int[] values, Node parent) {
        if (values.length == 0) {
            return parent;
        }

        int middleIndex = values.length / 2;

        Node root = new Node(values[middleIndex]);
        if (parent != null) {
            if (parent.value > root.value) {
                parent.left = root;
            }
            else
            {
                parent.right = root;
            }
        }

        makeBinarySearchTree(Arrays.copyOfRange(values, 0, middleIndex), root);
        makeBinarySearchTree(Arrays.copyOfRange(values, middleIndex + 1, values.length), root);

        return root;
    }

    @Test
    void name() {
        double[] doubles = calcEquation(
                asList(asList("a", "b"), asList("c", "d"), asList("e", "f"), asList("g", "h")),
                new double[]{4.5, 2.3, 8.9, 0.44},
                asList(asList("b", "e"), asList("a","c"), asList("d","f"), asList("h","e"), asList("b","e"), asList("d","h"), asList("g","f"), asList("c","g"))
        );

        assertThat(doubles).contains(-1, -1, -1, -1, -1, -1, -1);
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // symbol to index
        Map<String, Integer> symbols = new HashMap<>();
        int totalSyms = 0;
        for(List<String> eq: equations) {
            String first = eq.get(0);
            String second = eq.get(1);
            if (symbols.get(first) == null) symbols.put(first, totalSyms++);
            if (symbols.get(second) == null) symbols.put(second, totalSyms++);
        }

        double[][] graph = new double[totalSyms][totalSyms];
        int i = 0;
        for(List<String> eq : equations) {
            int first = symbols.get(eq.get(0));
            int second = symbols.get(eq.get(1));

            graph[first][second] = values[i];
            graph[second][first] = 1 / values[i];
            i++;
        }
        Arrays.stream(graph).forEach(g -> System.out.println(Arrays.toString(g)));

        double[] results = new double[queries.size()];
        i = 0;
        for(List<String> q : queries) {
            String symbolOne = q.get(0);
            String symbolTwo = q.get(1);

            if (symbols.get(symbolOne) == null || symbols.get(symbolTwo) == null) {
                results[i++] = -1;
                continue;
            }

            int first = symbols.get(symbolOne);
            int second = symbols.get(symbolTwo);
            if (first == second) {
                results[i++] = 1;
                continue;
            }

            double value;
            if (graph[first][second] != 0) {
                value = graph[first][second];
            } else {
                value = reaches(first, second, graph, new HashSet<>());
            }
            results[i++] = value;
            graph[first][second] = value;
            graph[second][first] = 1  / value;
        }

        return results;
    }

    double reaches(int first, int second, double[][] graph, Set<Integer> visited) {
        double[] edges = graph[first];
        visited.add(first);
        for(int i = 0; i < edges.length; i++) {
            double e = edges[i];
            if (e != 0 && i != first && !visited.contains(i)) {
                if (i == second)
                {
                    return e;
                }

                double tentative = reaches(i, second, graph, visited);
                if (tentative != -1) {
                    double value = e;
                    value *= tentative;
                    return value;
                }
            }
        }

        return -1;
    }

    @Test
    void alienDictionary() {
        String[] dictionary = new String[] { "wrt", "wrf", "er", "ett", "rftt" };
        assertThat(alienAlphabet(dictionary)).isEqualTo("wertf");
    }

    private String alienAlphabet(String[] dictionary) {
        Map<Character, Set<Character>> graph = new HashMap<>();
        Map<Character, Integer> freq = new HashMap<>();

        // build graph and freq
        for (String word : dictionary) {
            for (char charAt : word.toCharArray()) {
                graph.putIfAbsent(charAt, new HashSet<>());
                freq.putIfAbsent(charAt, 0);
            }
        }

        for (int i = 0; i < dictionary.length - 1; i++) {
            String first = dictionary[i];
            String second = dictionary[i + 1];

            int wordIndex = 0;
            while (wordIndex < first.length() && wordIndex < second.length()) {
                char firstChar = first.charAt(wordIndex);
                char secondChar = second.charAt(wordIndex);

                if (firstChar != secondChar) {
                    if (!graph.get(firstChar).contains(secondChar)) {
                        freq.put(secondChar, freq.get(secondChar) + 1);
                        graph.get(firstChar).add(secondChar);
                    }
                }

                wordIndex++;
            }
        }

        Queue<Character> startSet = new LinkedList<>();
        freq.keySet().stream().filter(k -> freq.get(k) == 0).forEach(startSet::add);

        // topological sort
        StringBuilder alphabet = new StringBuilder();
        while (!startSet.isEmpty()) {
            char current = startSet.poll();
            alphabet.append(current);

            for (char child : graph.get(current)) {
                freq.put(child, freq.get(child) - 1);
                if (freq.get(child) == 0) {
                    startSet.add(child);
                }
            }
        }

        return alphabet.toString();
    }
}
