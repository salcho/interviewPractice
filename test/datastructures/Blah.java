package datastructures;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static java.lang.Math.max;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class Blah {

    static void decode(String input) {
        Node root = new Node(1);

        int index = 2;

        Node iNode = root;
        Node dNode = root;

        for (int i = 0; i < input.length(); i++) {
            char charAt = input.charAt(i);
            Node newNode = new Node(index++);
            if (charAt == 'I') {
                iNode.right = newNode;
                iNode = newNode;
                dNode = newNode;
            } else {
                dNode.left = newNode;
                dNode = newNode;
            }
        }

        System.out.println(root.inOrderSearch());
    }

    @Test
    void incrementDecrementPattern() {
        decode("DDIDDIID");
    }

    @Test
    void localMaximaTest() {
        List<Integer> maxima = localMaxima(new int[]{1, 3, 5, 4, 7, 10, 6});
        assertThat(maxima).containsExactly(5, 10);

        maxima = localMaxima(new int[]{5, 10, 20, 15});
        assertThat(maxima).containsExactly(20);

        maxima = localMaxima(new int[]{10, 20, 15, 2, 23, 90, 67});
        assertThat(maxima).containsExactly(20, 90);

        maxima = localMaxima(new int[]{10, 20, 30, 40, 50});
        assertThat(maxima).containsExactly(50);

        maxima = localMaxima(new int[]{10, 9, 8, 7, 6});
        assertThat(maxima).containsExactly(10);
    }

    List<Integer> localMaxima(int[] values) {
        List<Integer> maxima = new ArrayList<>();
        for (int i = 0; i < values.length; i++) {
            if (i == 0) {
                if (values[i] > values[i + 1]) {
                    maxima.add(values[i]);
                }
            } else if (i <= values.length - 2) {
                if (values[i] > values[i - 1] && values[i] > values[i + 1]) {
                    maxima.add(values[i]);
                }
            } else {
                if (values[i] > values[i - 1]) {
                    maxima.add(values[i]);
                }
            }
        }

        return maxima;
    }

    @Test
    void bracketValidator() {
        assertThat(validateBrackets("({[][]})")).isTrue();
        assertThat(validateBrackets("({[]]})")).isFalse();
    }

    private boolean validateBrackets(String s) {
        Stack<Integer> curly = new Stack<>();
        Stack<Integer> square = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char charAt = s.charAt(i);
            if (charAt == '{') {
                curly.push(1);
            } else if (charAt == '}') {
                if (curly.isEmpty()) return false;
                curly.pop();
            } else if (charAt == '[') {
                square.push(1);
            } else if (charAt == ']') {
                if (square.isEmpty()) return false;
                square.pop();
            }
        }

        return curly.isEmpty() && square.isEmpty();
    }

    @Test
    void sumSetTest() {
        Set<Integer> A = new HashSet<>(asList(2, 1));
        Set<Integer> C = new HashSet<>(asList(3, 4, 5));

        Set<Integer> B = new HashSet<>();

        int maxC = 0;
        int maxA = 0;

        Iterator<Integer> iterator = C.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if (next > maxC) {
                maxC = next;
            }
        }

        iterator = A.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if (next > maxA) {
                maxA = next;
            }
        }

        for (int b = maxC - maxA; b >= 0; b--) {
            if (C.isEmpty()) {
                break;
            }

            iterator = A.iterator();
            while (iterator.hasNext()) {
                Integer next = iterator.next();
                if (C.contains(next + b)) {
                    B.add(b);
                    C.remove(next + b);
                }
            }
        }

        assertThat(B).containsExactly(2, 3);
    }

    @Test
    void frogPathTest() {
        int[] coords = new int[]{2, 2};
        int s = 3;
        int t = 6;

        assertThat(getPotentialCoords(coords, s, t)).isEqualTo(6);
    }

    private int getPotentialCoords(int[] coords, int s, int t) {
        int x = coords[0];
        int y = coords[1];
        int count = 0;
        move:
        for (int i = x; i <= x + s; i++) {
            for (int j = y; j <= y + s; j++) {
                // T(x) >= S(x)
                if ((-i + t) >= j) {
                    System.out.println(i + ";" + j);
                    count++;
                } else {
                    continue move;
                }
            }
        }

        return count;
    }

    @Test
    void xTotalShapesTest() {
        char[][] matrix = new char[6][6];
        matrix[0] = new char[]{'0', '0', 'X', 'X', '0', '0'};
        matrix[1] = new char[]{'X', 'X', 'X', 'X', 'X', 'X'};
        matrix[2] = new char[]{'0', '0', '0', '0', '0', '0'};
        matrix[3] = new char[]{'0', '0', 'X', 'X', '0', '0'};
        matrix[4] = new char[]{'X', 'X', 'X', 'X', 'X', 'X'};
        matrix[5] = new char[]{'0', '0', 'X', 'X', '0', '0'};

        assertThat(xTotalShapes(matrix)).isEqualTo(2);

        matrix[0] = new char[]{'0', '0', 'X', 'X', '0', '0'};
        matrix[1] = new char[]{'X', 'X', 'X', 'X', 'X', 'X'};
        matrix[2] = new char[]{'0', '0', 'X', 'X', '0', '0'};
        matrix[3] = new char[]{'0', '0', 'X', 'X', '0', '0'};
        matrix[4] = new char[]{'X', 'X', 'X', 'X', 'X', 'X'};
        matrix[5] = new char[]{'0', '0', 'X', 'X', '0', '0'};

        assertThat(xTotalShapes(matrix)).isEqualTo(1);

        matrix[0] = new char[]{'0', '0', '0', '0', '0', '0'};
        matrix[1] = new char[]{'0', '0', '0', '0', '0', '0'};
        matrix[2] = new char[]{'0', '0', '0', '0', '0', '0'};
        matrix[3] = new char[]{'0', '0', '0', '0', '0', '0'};
        matrix[4] = new char[]{'0', '0', '0', '0', '0', '0'};
        matrix[5] = new char[]{'0', '0', '0', '0', '0', '0'};

        assertThat(xTotalShapes(matrix)).isEqualTo(0);

    }

    private int xTotalShapes(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;

        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        int shapeCount = 0;
        LinkedList<Tuple<Integer, Integer>> nodes = new LinkedList<>();
        nodes.add(new Tuple<>(0, 0));

        boolean shapeMode = false;
        while (!nodes.isEmpty()) {
            Tuple<Integer, Integer> coords = nodes.poll();
            visited[coords.left][coords.right] = true;
            char symbol = matrix[coords.left][coords.right];
            if (symbol != 'X') {
                if (shapeMode) {
                    shapeMode = false;
                    shapeCount++;
                }

                List<List<Tuple<Integer, Integer>>> neighbours = getNeighbours(matrix, coords, visited);
                neighbours.get(0).stream().filter(t -> !visited[t.left][t.right]).forEach(nodes::addFirst);
                neighbours.get(1).stream().filter(t -> !visited[t.left][t.right]).forEach(nodes::addLast);
            } else {
                if (!shapeMode) shapeMode = true;
                List<List<Tuple<Integer, Integer>>> neighbours = getNeighbours(matrix, coords, visited);
                neighbours.get(0).stream().filter(t -> !visited[t.left][t.right]).forEach(nodes::addFirst);
                neighbours.get(1).stream().filter(t -> !visited[t.left][t.right]).forEach(nodes::addLast);
            }
        }

        return shapeCount;
    }

    List<List<Tuple<Integer, Integer>>> getNeighbours(char[][] matrix, Tuple<Integer, Integer> coords, boolean[][] visited) {
        int row = coords.left;
        int col = coords.right;
        int numRows = matrix.length;
        int numCols = matrix[0].length;
        List<Tuple<Integer, Integer>> x = new ArrayList<>();
        List<Tuple<Integer, Integer>> o = new ArrayList<>();

        if (row - 1 >= 0 && col - 1 >= 0 && !visited[row - 1][col - 1]) {
            if (matrix[row - 1][col - 1] == 'X') {
                x.add(new Tuple<>(row - 1, col - 1));
            } else {
                o.add(new Tuple<>(row - 1, col - 1));
            }
        }
        if (row - 1 >= 0 && !visited[row - 1][col]) {
            if (matrix[row - 1][col] == 'X') {
                x.add(new Tuple<>(row - 1, col));
            } else {
                o.add(new Tuple<>(row - 1, col));
            }
        }
        if (row - 1 >= 0 && col + 1 < numCols && !visited[row - 1][col + 1]) {
            if (matrix[row - 1][col + 1] == 'X') {
                x.add(new Tuple<>(row - 1, col + 1));
            } else {
                o.add(new Tuple<>(row - 1, col + 1));
            }
        }
        if (col - 1 >= 0 && !visited[row][col - 1]) {
            if (matrix[row][col - 1] == 'X') {
                x.add(new Tuple<>(row, col - 1));
            } else {
                o.add(new Tuple<>(row, col - 1));
            }
        }
        if (col + 1 < numCols && !visited[row][col + 1]) {
            if (matrix[row][col + 1] == 'X') {
                x.add(new Tuple<>(row, col + 1));
            } else {
                o.add(new Tuple<>(row, col + 1));
            }
        }
        if (row + 1 < numRows && col - 1 >= 0 && !visited[row + 1][col - 1]) {
            if (matrix[row + 1][col - 1] == 'X') {
                x.add(new Tuple<>(row + 1, col - 1));
            } else {
                o.add(new Tuple<>(row + 1, col - 1));
            }
        }
        if (row + 1 < numRows && !visited[row + 1][col]) {
            if (matrix[row + 1][col] == 'X') {
                x.add(new Tuple<>(row + 1, col));
            } else {
                o.add(new Tuple<>(row + 1, col));
            }
        }
        if (row + 1 < numRows && col + 1 < numCols && !visited[row + 1][col + 1]) {
            if (matrix[row + 1][col + 1] == 'X') {
                x.add(new Tuple<>(row + 1, col + 1));
            } else {
                o.add(new Tuple<>(row + 1, col + 1));
            }
        }

        List<List<Tuple<Integer, Integer>>> neighbours = new ArrayList<>();
        neighbours.add(x);
        neighbours.add(o);
        return neighbours;
    }

    @Test
    void countNodesInRange() {
        int[] values = new int[]{8, 4, 12, 2, 6, 10, 14, 1, 3, 5, 7, 9, 11, 13, 15};
        BSTNode root = BSTNode.makeBst(values);

        assertThat(root.numberOfNodesWithinRange(0, 8)).isEqualTo(8);
        assertThat(root.numberOfNodesWithinRange(8, 20)).isEqualTo(8);
        assertThat(root.numberOfNodesWithinRange(4, 10)).isEqualTo(7);
        assertThat(root.numberOfNodesWithinRange(0, 0)).isEqualTo(0);
    }

    @Test
    void isSudokuValidTest() {
        int[][] sudoku = new int[9][9];
        sudoku[0] = new int[]{5, 3, 0, 0, 7, 0, 0, 0, 0};
        sudoku[1] = new int[]{6, 0, 0, 1, 9, 5, 0, 0, 0};
        sudoku[2] = new int[]{0, 9, 8, 0, 0, 0, 0, 6, 0};
        sudoku[3] = new int[]{8, 0, 0, 0, 6, 0, 0, 0, 3};
        sudoku[4] = new int[]{4, 0, 0, 8, 0, 3, 0, 0, 1};
        sudoku[5] = new int[]{7, 0, 0, 0, 2, 0, 0, 0, 6};
        sudoku[6] = new int[]{0, 6, 0, 0, 0, 0, 2, 8, 0};
        sudoku[7] = new int[]{0, 0, 0, 4, 1, 9, 0, 0, 5};
        sudoku[8] = new int[]{0, 0, 0, 0, 8, 0, 0, 7, 9};

        assertThat(isValidSudoku(sudoku)).isTrue();

        sudoku[0] = new int[]{5, 3, 0, 0, 7, 0, 0, 0, 3};
        sudoku[1] = new int[]{6, 0, 0, 1, 9, 5, 0, 0, 0};
        sudoku[2] = new int[]{0, 9, 8, 0, 0, 0, 0, 6, 0};
        sudoku[3] = new int[]{8, 0, 0, 0, 6, 0, 0, 0, 3};
        sudoku[4] = new int[]{4, 0, 0, 8, 0, 3, 0, 0, 1};
        sudoku[5] = new int[]{7, 0, 0, 0, 2, 0, 0, 0, 6};
        sudoku[6] = new int[]{0, 6, 0, 0, 0, 0, 2, 8, 0};
        sudoku[7] = new int[]{0, 0, 0, 4, 1, 9, 0, 0, 5};
        sudoku[8] = new int[]{0, 0, 0, 0, 8, 0, 0, 7, 9};

        assertThat(isValidSudoku(sudoku)).isFalse();
    }

    private boolean isValidSudoku(int[][] sudoku) {
        if (sudoku == null || sudoku.length == 0 || sudoku[0].length == 0) return false;

        // check columns
        for (int col = 0; col < sudoku[0].length; col++) {
            boolean[] values = new boolean[10];
            for (int row = 0; row < sudoku.length; row++) {
                int value = sudoku[row][col];
                if (value < 0 || value > 9 || (value > 0 && values[value])) {
                    return false;
                }
                values[value] = true;
            }
        }

        // check rows
        for (int row = 0; row < sudoku.length; row++) {
            boolean[] values = new boolean[10];
            for (int col = 0; col < sudoku[0].length; col++) {
                int value = sudoku[row][col];
                if (value < 0 || value > 9 || (value > 0 && values[value])) {
                    return false;
                }
                values[value] = true;
            }
        }

        // check 3x3
        for (int xOffset = 0; xOffset < 7; xOffset++) {
            for (int yOffset = 0; yOffset < 7; yOffset++) {
                for (int row = 0; row < 3; row++) {
                    boolean[] values = new boolean[10];
                    for (int col = 0; col < 3; col++) {
                        int value = sudoku[row][col];
                        if (value < 0 || value > 9 || (value > 0 && values[value])) {
                            return false;
                        }
                        values[value] = true;
                    }
                }
            }
        }

        return true;
    }

    @Test
    void countSumOfPathsFromRootToLeaves() {
        int[] values = new int[]{8, 4, 12, 2, 6, 10, 14, 1, 3, 5, 7, 9, 11, 13, 15};
        BSTNode root = BSTNode.makeBst(values);

        assertThat(root.countSumOfPathsFromRootToLeaves()).isEqualTo(71104);
    }

    @Test
    void findSumZeroTriple() {
        int[] values = new int[]{-1, -3, 4, 5, 5, 6, 7, 3, 0};
        assertThat(findTriplets(values)).containsExactly(new int[]{-1, 4, -3}, new int[]{-3, 0, 3});
        assertThat(findTripletsSorted(values)).containsExactly(new int[]{-1, 4, -3}, new int[]{0, 3, -3});
    }

    private List<int[]> findTripletsSorted(int[] values) {
        Arrays.sort(values);
        List<int[]> triplets = new ArrayList<>();
        for (int i = 0; i < values.length; i++) {
            int l = i + 1;
            int r = values.length - 2;
            while (l < r) {
                int partialSum = values[l] + values[r];
                if (partialSum + values[i] == 0) {
                    triplets.add(new int[]{values[l], values[r], values[i]});
                    l++;
                    r--;
                } else if (partialSum > values[i]) r--;
                else l++;
            }
        }

        return triplets;
    }

    private List<int[]> findTriplets(int[] values) {
        List<int[]> triplets = new ArrayList<>();
        for (int i = 0; i < values.length; i++) {
            Set<Integer> seen = new LinkedHashSet<>();
            for (int j = i + 1; j < values.length; j++) {
                if (seen.contains(-(values[i] + values[j]))) {
                    triplets.add(new int[]{values[i], values[j], -(values[i] + values[j])});
                } else {
                    seen.add(values[j]);
                }
            }
        }
        return triplets;
    }

    @Test
    void metaStringsTest() {
        String one = "string";
        String two = "tsring";
        assertThat(areMetaStrings(one, two)).isTrue();

        one = "string";
        two = "gtrins";
        assertThat(areMetaStrings(one, two)).isTrue();

        one = "string";
        two = "string";
        assertThat(areMetaStrings(one, two)).isFalse();

        one = "string";
        two = "abcdef";
        assertThat(areMetaStrings(one, two)).isFalse();
    }

    private boolean areMetaStrings(String one, String two) {
        if (one == null || two == null || one.length() != two.length()) return false;

        int missmatch = 0;
        boolean foundSwap = false;
        char lookingFor = 0;
        char swapFor = 0;
        for (int i = 0; i < one.length(); i++) {
            if (missmatch > 1) return false;
            if (one.charAt(i) != two.charAt(i)) {
                if (missmatch != 0) {
                    if (one.charAt(i) == swapFor && two.charAt(i) == lookingFor) {
                        foundSwap = true;
                        missmatch--;
                    }
                } else {
                    missmatch++;
                    lookingFor = one.charAt(i);
                    swapFor = two.charAt(i);
                }
            }
        }
        return missmatch == 0 && foundSwap;
    }

    @Test
    void connectNodesAtSameLevelTest() {
        int[] values = new int[]{8, 4, 12, 2, 6, 10, 14, 1, 3, 5, 7, 9, 11, 13, 15};
        BSTNode root = BSTNode.makeBst(values);
        root.connectNodesAtSameLevel();

        assertThat(root.levelOrder()).containsExactly(8, 4, 12, 2, 6, 10, 14, 1, 3, 5, 7, 9, 11, 13, 15);
        assertThat(root.inOrder()).containsExactly(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
    }

    @Test
    void largestWordInDictionary() {
        List<String> dictionary = new ArrayList<>(asList("ale", "apple", "monkey", "plea"));
        String input = "abpcplea";

        assertThat(findLargestMatch(dictionary, input)).isEqualTo("apple");

        dictionary = new ArrayList<>(asList("pintu", "geeksfor", "geeksforgeeks", " forgeek"));
        input = "geeksforgeeks";

        assertThat(findLargestMatch(dictionary, input)).isEqualTo("geeksforgeeks");
    }

    private String findLargestMatch(List<String> dictionary, String input) {
        String maxString = "";

        for (String word : dictionary) {
            if (word.length() > input.length()) continue;
            boolean noMatch = false;
            int lastIndex = -1;
            nextLetter:
            for (int i = 0; i < word.length(); i++) {
                // O(len(input)*maxWordLen*size(dict))
                for (int j = lastIndex + 1; j < input.length(); j++) {
                    if (word.charAt(i) == input.charAt(j)) {
                        continue nextLetter;
                    }
                }
                noMatch = true;
            }

            if (!noMatch && word.length() > maxString.length()) maxString = word;
        }

        return maxString;
    }

    @Test
    void findKthSmallesInBST() {
        int[] values = new int[]{8, 4, 12, 2, 6, 10, 14, 1, 3, 5, 7, 9, 11, 13, 15};
        BSTNode root = BSTNode.makeBst(values);

        List<Integer> inOrder = root.inOrder();

        for (int i = 0; i < values.length; i++) {
            Optional<Integer> kthSmallest = root.findKthSmallest(i + 1);
            assertThat(kthSmallest).isNotEmpty();
            assertThat(kthSmallest.get()).isEqualTo(inOrder.get(i));
        }

        assertThat(root.findKthSmallest(Integer.MAX_VALUE)).isEmpty();
    }

    @Test
    void theCelebrityProblemTest() {
        Graph graph = new Graph(5);
        graph.addLink(0, 0);
        graph.addLink(1, 1);
        graph.addLink(2, 2);
        graph.addLink(3, 3);
        graph.addLink(4, 4);
        //----
        graph.addLink(0, 3);
        graph.addLink(1, 3);
        graph.addLink(2, 3);
        graph.addLink(4, 3);
        graph.addLink(1, 2);
        graph.addLink(4, 1);
        graph.addLink(4, 2);
        graph.addLink(2, 0);
        graph.addLink(0, 2);

        assertThat(findCelebrity(graph.adjacencyList)).isEqualTo(3);
    }

    private int findCelebrity(boolean[][] graph) {
        nextCol:
        for (int col = 0; col < graph[0].length; col++) {
            for (int row = 0; row < graph.length; row++) {
                if (!graph[row][col]) continue nextCol;
            }
            // found candidate
            for (int i = 0; i < graph[0].length; i++) {
                if (i != col && graph[col][i]) continue nextCol; // they know somebody
            }
            return col;
        }

        return -1;
    }

    @Test
    void overlappingIntervalsTest() {
        List<Tuple<Integer, Integer>> intervals = new ArrayList<>(asList(
                new Tuple<>(15, 18),
                new Tuple<>(2, 6),
                new Tuple<>(8, 10),
                new Tuple<>(16, 20),
                new Tuple<>(1, 3)
        ));

        mergeIntervals(intervals);

        assertThat(intervals).containsExactly(
                new Tuple<>(1, 6),
                new Tuple<>(8, 10),
                new Tuple<>(15, 20)
        );
    }

    private void mergeIntervals(List<Tuple<Integer, Integer>> intervals) {
        intervals.sort((a, b) -> a.left.equals(b.left) ? 0 : a.left < b.left ? -1 : 1);

        int index = 0;
        while (index < intervals.size()) {
            Tuple<Integer, Integer> first = intervals.get(index);
            if (index + 1 >= intervals.size()) {
                break;
            }
            Tuple<Integer, Integer> second = intervals.get(index + 1);

            if (first.right >= second.left) {
                intervals.set(index, new Tuple<>(first.left, second.right));
                intervals.remove(index + 1);
            } else {
                index++;
            }
        }
    }

    @Test
    void floodFill() {
        int[][] matrix = new int[8][8];
        matrix[0] = new int[]{1, 1, 1, 1, 1, 1, 1, 1};
        matrix[1] = new int[]{1, 1, 1, 1, 1, 1, 0, 0};
        matrix[2] = new int[]{1, 0, 0, 1, 1, 0, 1, 1};
        matrix[3] = new int[]{1, 2, 2, 2, 2, 0, 1, 0};
        matrix[4] = new int[]{1, 1, 1, 2, 2, 0, 1, 0};
        matrix[5] = new int[]{1, 1, 1, 2, 2, 2, 2, 0};
        matrix[6] = new int[]{1, 1, 1, 1, 1, 2, 1, 1};
        matrix[7] = new int[]{1, 1, 1, 1, 1, 2, 2, 1};

        repaint(matrix, new Tuple<>(4, 4), 3);

        assertThat(matrix[0]).containsExactly(1, 1, 1, 1, 1, 1, 1, 1);
        assertThat(matrix[1]).containsExactly(1, 1, 1, 1, 1, 1, 0, 0);
        assertThat(matrix[2]).containsExactly(1, 0, 0, 1, 1, 0, 1, 1);
        assertThat(matrix[3]).containsExactly(1, 3, 3, 3, 3, 0, 1, 0);
        assertThat(matrix[4]).containsExactly(1, 1, 1, 3, 3, 0, 1, 0);
        assertThat(matrix[5]).containsExactly(1, 1, 1, 3, 3, 3, 3, 0);
        assertThat(matrix[6]).containsExactly(1, 1, 1, 1, 1, 3, 1, 1);
        assertThat(matrix[7]).containsExactly(1, 1, 1, 1, 1, 3, 3, 1);
    }

    private void repaint(int[][] matrix, Tuple<Integer, Integer> startCoords, int newColor) {
        Queue<Tuple<Integer, Integer>> queue = new LinkedList<>();
        int oldColor = matrix[startCoords.left][startCoords.right];
        queue.add(startCoords);

        while (!queue.isEmpty()) {
            Tuple<Integer, Integer> coords = queue.poll();
            int row = coords.left;
            int col = coords.right;
            if (matrix[row][col] != oldColor) continue;
            matrix[row][col] = newColor;

            if (row - 1 >= 0 && col - 1 >= 0 && matrix[row - 1][col - 1] == oldColor)
                queue.add(new Tuple<>(row - 1, col - 1));
            if (row - 1 >= 0 && matrix[row - 1][col] == oldColor) queue.add(new Tuple<>(row - 1, col));
            if (row - 1 >= 0 && col + 1 < matrix.length && matrix[row - 1][col + 1] == oldColor)
                queue.add(new Tuple<>(row - 1, col + 1));
            if (col - 1 >= 0 && matrix[row][col - 1] == oldColor) queue.add(new Tuple<>(row, col - 1));
            if (col - 1 >= 0 && matrix[row][col - 1] == oldColor) queue.add(new Tuple<>(row, col + 1));
            if (row + 1 < matrix.length && col - 1 >= 0 && matrix[row + 1][col - 1] == oldColor)
                queue.add(new Tuple<>(row + 1, col - 1));
            if (row + 1 < matrix.length && matrix[row + 1][col] == oldColor) queue.add(new Tuple<>(row + 1, col));
            if (row + 1 < matrix.length && col + 1 < matrix.length && matrix[row + 1][col + 1] != newColor)
                queue.add(new Tuple<>(row + 1, col + 1));
        }
    }

    @Test
    void interviewTest() throws IOException {
        String conv = "[10:30] <john> hello there! \n" +
                "Wed 05/31 @ server01 <martha> hello!hello!" +
                "Wed 05/31 @ server01 <martha> hello!hello!";

        assertThat(topNUsers(2, conv)).containsExactly("martha", "john");
        assertThat(topNUsers(1, conv)).containsExactly("john");
    }

    List<String> topNUsers(int n, String logFile) throws IOException {
        Map<String, UserAndMessages> messages = new HashMap<>();
        BufferedReader reader = new BufferedReader(new StringReader(logFile));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = tokenize(line);
            String user = parts[0];
            String msg = parts[1];
            if (messages.get(user) == null) {
                messages.put(user, new UserAndMessages(user));
            } else {
                messages.get(user).addMessages(msg.split(" ").length);
            }
        }

        ArrayList<Map.Entry<String, UserAndMessages>> sorted = new ArrayList<>(messages.entrySet());
        sorted.sort((u1, u2) -> {
            if (u1.getValue().words > u2.getValue().words) {
                return 1;
            } else if (u2.getValue().words == u2.getValue().words) {
                return 0;
            } else {
                return -1;
            }
        });
        List<String> topUsers = new ArrayList<>();
        for (int i = sorted.size() - n; i < sorted.size(); i++) {
            topUsers.add(sorted.get(i).getKey());
        }

        return topUsers;
    }

    String[] tokenize(String line) {
        boolean userParsing = false;
        boolean msgParsing = false;
        String[] tokens = new String[2];
        StringBuilder user = new StringBuilder();
        StringBuilder msg = new StringBuilder();
        // fixme - start at <
        for (int i = 0; i < line.length(); i++) {
            char charAt = line.charAt(i);
            if (charAt == '>') {
                userParsing = false;
                msgParsing = true;
                i++;	// skip whitespace
                continue;
            }

            if (userParsing) {
                user.append(charAt);
                continue;
            } else if (msgParsing) {
                msg.append(charAt);
                continue;
            }

            if (charAt == '<') {
                userParsing = true;
            }
        }
        tokens[0] = user.toString();
        tokens[1] = msg.toString();
        return tokens;
    }

    class UserAndMessages {
        final String user;
        int words = 0;

        UserAndMessages(String user){
            this.user = user;
        }

        void addMessages(int numWords) {
            words += numWords;
        }
    }
}
