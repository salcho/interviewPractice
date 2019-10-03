package datastructures;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

public class DynamicProgramming {
    @Test
    void ZeroOneKnapsackTest() {
        int[] weights = new int[]{1, 3, 5, 8, 9};
        int[] values = new int[]{5, 3, 1, 2, 8};
        int capacity = 6;
        assertThat(zeroOneKnapsack(weights, values, capacity)).containsExactly(1, 0);

        weights = new int[]{ 1, 3, 4, 5 };
        values = new int[]{ 1, 4, 5, 7 };
        capacity = 7;
        assertThat(zeroOneKnapsack(weights, values, capacity)).containsExactly(2, 1);
    }

    private List<Integer> zeroOneKnapsack(int[] weights, int[] values, int capacity) {
        int[][] matrix = buildDPMatrix(weights, values, capacity);

        List<Integer> items = new ArrayList<>();
        int row = matrix.length - 1;
        int col = matrix[0].length - 1;
        int maxValue = matrix[row][col];
        while (row >= 0 && col > 0) {
            int chosenItem;
            if (row - 1 >= 0) {
                if (maxValue == matrix[row - 1][col]) {
                    row--;
                    continue;
                } else {
                    items.add(row);
                    chosenItem = row;
                }
            } else {
                items.add(row);
                chosenItem = row;
            }

            row--;
            col -= weights[chosenItem];
        }

        return items;
    }

    private int[][] buildDPMatrix(int[] weights, int[] values, int capacity) {
        int[][] matrix = new int[weights.length][capacity + 1];

        for (int col = 1; col < matrix[0].length; col++) {	// first row
            matrix[0][col] = values[0];
        }

        for (int row = 1; row < matrix.length; row++) {
            for (int col = 1; col < matrix[0].length; col++) {
                if (col < weights[row]) {
                    matrix[row][col] = matrix[row - 1][col];
                } else {
                    int withCurrent = values[row];			// current value
                    int remainingCap = col - weights[row];
                    withCurrent += matrix[row - 1][remainingCap];	// plus max value of remaining cap

                    int withoutCurrent = matrix[row - 1][col];
                    matrix[row][col] = max(withCurrent, withoutCurrent);
                }
            }
        }

        return matrix;
    }

    @Test
    void numberOfWaysToMakeAmountTest() {
        int[] coins = new int[] { 1, 2, 5 };
        int amount = 6;
        assertThat(numberOfWaysToMakeAmount(coins, amount)).isEqualTo(5);
    }

    private int numberOfWaysToMakeAmount(int[] coins, int amount) {
        int[] combinations = new int[amount + 1];
        combinations[0] = 1;

        for (int i = 0; i < coins.length; i++) {
            int coin = coins[i];
            for (int j = coin; j < combinations.length; j++) {
                combinations[j] += combinations[j - coin];
            }
        }

        return combinations[amount];
    }

    @Test
    void minNumberOfJumpsTest() {
        int[] steps = new int[] { 2, 3, 1, 1, 2, 4, 2, 0, 1, 1 };

        Tuple<Integer, List<Integer>> actual = minNumberOfJumps(steps);
        assertThat(actual.left).isEqualTo(4);
        assertThat(actual.right).containsExactly(0, 1, 4, 5, 9);
    }

    private Tuple<Integer, List<Integer>> minNumberOfJumps(int[] steps) {
        int[] minJumps = new int[steps.length];
        int[] predecesor = new int[steps.length];

        minJumps[0] = 0;
        predecesor[0] = 0;

        for (int i = 1; i < steps.length; i++) {
            for (int j = 0; j < i; j++) {
                if (j + steps[j] >= i) {
                    int tentative = 1 + minJumps[j];
                    if (minJumps[i] == 0 || tentative < minJumps[i]) {
                        minJumps[i] = tentative;
                        predecesor[i] = j;
                    }
                }
            }
        }

        List<Integer> path = new ArrayList<>();
        path.add(steps.length - 1);
        int i = steps.length - 1;
        while (i > 0) {
            path.add(predecesor[i]);
            i = predecesor[i];
        }
        path.sort(Integer::compareTo);

        return new Tuple<>(minJumps[minJumps.length - 1], path);
    }
}
