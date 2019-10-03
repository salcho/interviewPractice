package datastructures;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Recursion {
    @Test
    void binarySearch() {
        int[] numbers = new int[]{ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };

        for (int i = 1; i < numbers.length; i++) {
            assertThat(binarySearchIndexOf(numbers, i)).as("Looking for " + i).isEqualTo(i - 1);
            assertThat(binarySearchIndexOfIterative(numbers, i)).as("Looking for " + i).isEqualTo(i - 1);
        }
    }

    private int binarySearchIndexOfIterative(int[] numbers, int searchFor) {
        int from = 0;
        int to = numbers.length - 1;
        while (true) {
            int middleIndex = (to - from) / 2 + from;
            int middle = numbers[middleIndex];
            if (middle == searchFor) {
                return middleIndex;
            } else if (middle > searchFor) {
                to = middleIndex - 1;
                continue;
            }

            from = middleIndex + 1;
        }
    }

    private int binarySearchIndexOf(int[] numbers, int searchFor) {
        return binarySearchIndexOf(numbers, searchFor, 0, numbers.length - 1);
    }
    private int binarySearchIndexOf(int[] numbers, int searchFor, int from, int to) {
        int middleIndex = (to - from)/ 2 + from;
        int middle = numbers[middleIndex];
        if (middle == searchFor) {
            return middleIndex;
        }
        else if (middle > searchFor) {
            return binarySearchIndexOf(numbers, searchFor, from, middleIndex);
        }

        return binarySearchIndexOf(numbers, searchFor, middleIndex + 1, to);
    }
}
