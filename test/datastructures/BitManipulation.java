package datastructures;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BitManipulation {
    @Test
    void insertMInNTest() {
        int N = 0b10000000000;
        int M = 0b10011;
        int i = 2;
        int j = 6;

        assertThat(insertMInN(N, M, i, j)).isEqualTo(0b10001001100);
    }

    private int insertMInN(int n, int m, int i, int j) {
        int mask = (2 ^ (j + 1 - i + 1)) << i;
        int maskedN = n & ~mask;
        int shiftedM = m << i;
        return maskedN | shiftedM;
    }

    @Test
    void flipToWinTest() {
        assertThat(flipToWin(1775)).isEqualTo(8);
    }

    private int flipToWin(int input) {
        int count = 0;
        boolean flipped = false;
        for (int i = 0; i < 32; i++) {
            int mask = 1 << i;
            if ((input | mask) == input) {
                count++;
            } else {
                if (!flipped) {
                    flipped = true;
                    count++;
                }
                else
                {
                    break;
                }
            }
        }

        return count;
    }
}
