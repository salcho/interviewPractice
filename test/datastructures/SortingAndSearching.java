package datastructures;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.Arrays.asList;

public class SortingAndSearching {
    @Test
    void bubbleSortTest() {
        int[] list = {2, 5, 3, 6, 8, 4, 1, 5, 8};
        bubbleSort(list);
        assertThat(list).isEqualTo(new int[] { 1, 2, 3, 4, 5, 5, 6, 8, 8 });

        list = new int[]{2, 5, 3, 6, 8, 4, 1, 5, 8};
        selectionSort(list);
        assertThat(list).isEqualTo(new int[] { 1, 2, 3, 4, 5, 5, 6, 8, 8 });

        list = new int[]{2, 5, 3, 6, 8, 4, 1, 5, 8};
        int[] sorted = mergeSort(list);
        assertThat(sorted).isEqualTo(new int[] { 1, 2, 3, 4, 5, 5, 6, 8, 8 });
    }

        private int[] mergeSort ( int[] list){
            if (list.length <= 1) {
                return list;
            }

            int middleIndex = list.length / 2;
            int[] firstHalf = mergeSort(Arrays.copyOfRange(list, 0, middleIndex));
            int[] secondHalf = mergeSort(Arrays.copyOfRange(list, middleIndex, list.length));
            return merge(firstHalf, secondHalf);
        }

        private int[] merge ( int[] list1, int[] list2){
            int[] helper = new int[list1.length + list2.length];
            int leftIndex = 0;
            int rightIndex = 0;
            int helperIndex = 0;

            while (leftIndex < list1.length && rightIndex < list2.length) {
                if (list1[leftIndex] < list2[rightIndex]) {
                    helper[helperIndex++] = list1[leftIndex++];
                    continue;
                }

                if (list2[rightIndex] <= list1[leftIndex]) {
                    helper[helperIndex++] = list2[rightIndex++];
                }
            }

            while (leftIndex < list1.length) {
                helper[helperIndex++] = list1[leftIndex++];
            }
            while (rightIndex < list2.length) {
                helper[helperIndex++] = list2[rightIndex++];
            }

            return helper;
        }

    private void selectionSort(int[] list) {
        int lastSorted = -1;
        while (lastSorted != list.length - 1) {
            int min = Integer.MAX_VALUE;
            int indexOfMin = -1;
            int beginAt = lastSorted + 1;
            for (int i = beginAt; i < list.length; i++) {
                if (list[i] < min) {
                    min = list[i];
                    indexOfMin = i;
                }
            }

            int tmp = list[indexOfMin];
            list[indexOfMin] = list[beginAt];
            list[beginAt] = tmp;
            lastSorted = beginAt;
        }
    }

    private void bubbleSort(int[] list) {
        boolean swapped = true;
        int until = list.length - 1;
        while (swapped) {
            swapped = false;
            int newUntil = 0;
            for (int i = 0; i < until; i++) {
                if (list[i] > list[i + 1]) {
                    int tmp = list[i + 1];
                    list[i + 1] = list[i];
                    list[i] = tmp;
                    swapped = true;
                    newUntil = i;
                }
            }

            until = newUntil;
        }
    }

    @Test
    void sortAnagramsTest() {
        String[] words = new String[] { "casa", "acas", "blah", "qwer", "foo", "saca" };
        assertThat(sortAnagrams(words)).containsExactly("casa", "saca", "blah", "qwer", "foo");
    }

    private String[] sortAnagrams(String[] words) {
        if (words.length <= 1) {
            return words;
        }

        int middleIndex = words.length / 2;

        String[] left = sortAnagrams(Arrays.copyOfRange(words, 0, middleIndex));
        String[] right = sortAnagrams(Arrays.copyOfRange(words, middleIndex, words.length));

        return mergeAnagrams(left, right);
    }

    private String[] mergeAnagrams(String[] left, String[] right) {
        String[] helper = new String[left.length + right.length];
        int leftIndex = 0;
        int rightIndex = 0;
        int helperIndex = 0;

        while (leftIndex < left.length && rightIndex < right.length) {
            if (left[leftIndex].compareTo(right[rightIndex]) < 0) {
                helper[helperIndex++] = left[leftIndex++];
            }
            else if (right[rightIndex].compareTo(left[leftIndex]) <= 0) {
                helper[helperIndex++] = right[rightIndex++];
            }
        }

        while (leftIndex < left.length) {
            helper[helperIndex++] = left[leftIndex++];
        }
        while (rightIndex < right.length) {
            helper[helperIndex++] = right[rightIndex++];
        }

        return helper;
    }
}
