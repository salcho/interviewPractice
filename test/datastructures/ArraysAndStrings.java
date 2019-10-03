package datastructures;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class ArraysAndStrings {

    @Test
    void findFirstNonRepeatedWithArray() {
        String noRepetition = "qwertyuiopasdfghjklzxcvbnm";
        assertThat(firstNonRepeatedUsingHash(noRepetition)).isEqualTo('q');
        assertThat(firstNonRepeatedUsingArray(noRepetition)).isEqualTo('q');
        String repeatsFirst = "qqwertyuiopasdfghjklzxcvbnm";    //w
        assertThat(firstNonRepeatedUsingHash(repeatsFirst)).isEqualTo('w');
        assertThat(firstNonRepeatedUsingArray(repeatsFirst)).isEqualTo('w');
        String repeatsLast= "qwertyuiopasdfghjklzxcvbnmm";      //q
        assertThat(firstNonRepeatedUsingHash(repeatsLast)).isEqualTo('q');
        assertThat(firstNonRepeatedUsingArray(repeatsLast)).isEqualTo('q');
        String repeatsMiddle = "qwertyuiopasdfqghjklzxcvbnmm";  //w
        assertThat(firstNonRepeatedUsingHash(repeatsMiddle)).isEqualTo('w');
        assertThat(firstNonRepeatedUsingArray(repeatsMiddle)).isEqualTo('w');
        String doesntRepeatSecond = "qwqqqqqqqqqqqqqqqqqqqqq";  //w
        assertThat(firstNonRepeatedUsingHash(doesntRepeatSecond)).isEqualTo('w');
        assertThat(firstNonRepeatedUsingArray(doesntRepeatSecond)).isEqualTo('w');
    }

    @Test
    void removeChars() {
        String abc = "Battle of the Vowels: Hawaii vs Grozny";
        assertThat(removeChars(abc, new char[]{'a', 'e', 'i', 'o', 'u'})).isEqualTo("Bttl f th Vwls: Hw vs Grzny");

        String battle = "Battle of the Vowels: Hawaii vs Grozny";
        assertThat(removeChars(battle, new char[]{'B', 'a', 't', 'l', 'e'})).isEqualTo("of h Vows: Hwii vs Grozny");
    }

    @Test
    void reverseWords() {
        String abc = "Battle of the Vowels: Hawaii vs Grozny";
        assertThat(reverseWords(abc)).isEqualTo("Grozny vs Hawaii Vowels: the of Battle");
    }

    @Test
    void strToInt() {
        String number = "1234";
        assertThat(stringToNumber(number)).isEqualTo(1234);

        String negative = "-1234";
        assertThat(stringToNumber(negative)).isEqualTo(-1234);

        String zero = "0";
        assertThat(stringToNumber(zero)).isEqualTo(0);
    }

    @Test
    void isUniqueTest() {
        String isUnique = "abcdefghjiklmnopqrstuwxyz";
        assertThat(isUnique(isUnique)).isTrue();
        assertThat(isUniqueBooleanArray(isUnique)).isTrue();
        assertThat(isUniqueVector(isUnique)).isTrue();

        String isNotUnique = "abcdefghjiklmnopqrstuwxyza";
        assertThat(isUnique(isNotUnique)).isFalse();
        assertThat(isUniqueBooleanArray(isNotUnique)).isFalse();
        assertThat(isUniqueVector(isNotUnique)).isFalse();
    }

    @Test
    void isPermutationTest() {
        assertThat(isPermutation("abcd", "abdc")).isTrue();
        assertThat(isPermutation("abcd", "abdv")).isFalse();
    }

    @Test
    void urlifyTest() {
        char[] input = "Mr John Smith    ".toCharArray();
        urlify(input, 0);
        assertThat(new String(input)).isEqualTo("Mr%20John%20Smith");
    }

    @Test
    void isPermutationOfPalindromeTest() {
        assertThat(isPermutationOfPalindrome("tactcoa")).isTrue();
        assertThat(isPermutationOfPalindrome("tactcoav")).isFalse();
        assertThat(isPermutationOfPalindrome("tacvtcoa")).isFalse();

        assertThat(isPermutationOfPalindromeVector("tactcoa")).isTrue();
        assertThat(isPermutationOfPalindromeVector("tactcoav")).isFalse();
        assertThat(isPermutationOfPalindromeVector("tacvtcoa")).isFalse();
    }

    @Test
    void oneAwayTest() {
        assertThat(oneAway("abcd", "abc")).isTrue();
        assertThat(oneAway("abcdfghj", "abcdfyhj")).isTrue();
        assertThat(oneAway("abcd", "abcd")).isTrue();
        assertThat(oneAway("abcd", "abcdyy")).isFalse();
    }

    @Test
    void compressionTest() {
        assertThat(compress("aaaabbbccd")).isEqualTo("a4b3c2d1");
        assertThat(compress("abcd")).isEqualTo("abcd");
        assertThat(compress("abbcccdddd")).isEqualTo("a1b2c3d4");
    }

    @Test
    void rotateMatrix90Test() {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        rotateRight(matrix);
        assertThat(matrix).isEqualTo(new int[][]{ {7, 4, 1}, {8, 5, 2}, { 9, 6, 3 } });
    }

    @Test
    void zeroOutTest() {
        int[][] matrix = {{1, 2, 3}, {0, 5, 6}, {7, 8, 9}};
        zeroOut(matrix);
        assertThat(matrix).isEqualTo(new int[][]{{0, 2, 3}, {0, 0, 0}, {0, 8, 9}});

        matrix = new int[][]{{1, 2, 3}, {4, 0, 6}, {7, 8, 9}};
        zeroOut(matrix);
        assertThat(matrix).isEqualTo(new int[][]{{1, 0, 3}, {0, 0, 0}, {7, 0, 9}});

        matrix = new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        zeroOut(matrix);
        assertThat(matrix).isEqualTo(new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}});
    }

    @Test
    void isRotationTest() {
        assertThat(isRotation("abcdef", "defabc")).isTrue();
        assertThat(isRotation("abcdef", "abcdef")).isTrue();
        assertThat(isRotation("abcdef", "bcdefa")).isTrue();
        assertThat(isRotation("abcdef", "cdefab")).isTrue();
        assertThat(isRotation("abcdef", "dexabc")).isFalse();
    }

    @Test
    void removeWhitespaceTest() {
        assertThat(removeWhiteSpace("I    live      on      earth")).isEqualTo("I live on earth");
    }

    private String removeWhiteSpace(String input) {
        StringBuilder sb = new StringBuilder();

        boolean foundSpace = false;
        for(int i = 0; i < input.length(); i++) {
            char charAt = input.charAt(i);

            if (!foundSpace && charAt == ' ') {
                    foundSpace = true;
            } else {
                if (charAt == ' ') {
                    continue;
                }

                foundSpace = false;
            }

            sb.append(charAt);
        }

        return sb.toString();
    }

    private boolean isRotation(String input, String input2) {
        if (input.length() != input2.length())	return false;

        for (int i = 0; i < input2.length(); i++) {
            if (input.charAt(0) == input2.charAt(i)) {
                int right = i;	// fixme + 1
                int original = 0;

                while (right < input2.length()) {
                    if (input.charAt(original) != input2.charAt(right++))	return false;
                    original++;
                }

                int left = 0;
                while (left < i) {
                    if (input.charAt(original) != input2.charAt(left++))	return false;
                    original++;
                }
            }
        }

        return true;
    }

    private void zeroOut(int[][] matrix) {
        if (matrix.length == 0) return;

        List<Tuple<Integer, Integer>> coords = new ArrayList<>();
        next:
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++){
                if (matrix[i][j] == 0)
                {
                    coords.add(new Tuple<>(i, j));
                    continue next;
                }
            }
        }

        System.out.println("coords.size() = " + coords.size());

        for (Tuple<Integer, Integer> coord : coords) {
            matrix[coord.left][coord.right] = 0;
            for (int i = 0; i < matrix.length; i++)
            {
                matrix[coord.left][i] = 0;
            }

            for (int i = 0; i < matrix[0].length; i++) {
                matrix[i][coord.right] = 0;
            }
        }
    }

    private void rotateRight(int[][] matrix) {
        for (int layer = 0; layer < matrix.length / 2; layer++) {
            for(int i = 0; i < matrix.length - 1; i++) {
                int bottomLeft = matrix[matrix.length - 1 - i][layer];

                //bottomLeft = bottomRight	(up)
                matrix[matrix.length - 1 - i][layer] = matrix[matrix.length - 1 - layer][matrix.length - 1 - layer - i];

                //bottomRight = topRight	(left)
                matrix[matrix.length - 1 - layer][matrix.length - 1 - layer - i] = matrix[i][matrix.length - 1 - layer];

                //topRight = topLeft		(down)
                matrix[i][matrix.length - 1 - layer] = matrix[layer][layer + i];

                //topLeft = bottomLeft		(right)
                matrix[layer][layer + i] =	bottomLeft;
            }
        }
    }


    private String compress(String input) {
        StringBuilder sb = new StringBuilder();
        char previous = input.charAt(0);
        int count = 1;
        for(int i = 1; i < input.length(); i++) {
            char current = input.charAt(i);
            if (current != previous) {
                sb.append(previous).append(count);
                previous = current;
                count = 1;
            }
            else count++;
        }

        sb.append(previous).append(count);

        String compressed = sb.toString();
        return input.length() < compressed.length() ? input : compressed;

    }

    private boolean oneAway(String input, String input2) {
        int lengthDiff = Math.abs(input.length() - input2.length());
        if (lengthDiff > 1) return false;

        int currentOne = 0;
        int currentTwo = 0;
        int[] frecuencies = new int[256];
        while (currentOne < input.length() || currentTwo < input2.length()) {
            if (currentOne < input.length())
            {
                frecuencies[input.charAt(currentOne)] += 1;
                currentOne++;
            }

            if (currentTwo < input2.length()) {
                frecuencies[input2.charAt(currentTwo)] -= 1;
                currentTwo++;
            }
        }

        int zeros = 0;
        int ones = 0;
        for(int freq : frecuencies) {
            zeros += freq == 0 ? 1 : 0;
            ones += freq == 1 ? 1 : 0;
        }

        boolean areIdentical = zeros == frecuencies.length;
        boolean addedOrRemoved = ones == 1;
        boolean replaced = lengthDiff == 0 && ones == 2;

        return areIdentical || addedOrRemoved || replaced;
    }

    private boolean isPermutationOfPalindromeVector(String input) {
        int[] frecuencies = new int[256];
        for(int i = 0; i < input.length(); i++)
        {
            char current = input.charAt(i);
            frecuencies[current] += 1;
        }

        long numberOfOdds = Arrays.stream(frecuencies).filter(i -> i !=0 && i != 2).count();
        return (numberOfOdds == 1 && input.length() %2 != 0) || numberOfOdds == 0;
    }

    private boolean isPermutationOfPalindrome(String input) {
        Map<Character, Integer> frecuencies = new HashMap<>();
        for(int i = 0; i < input.length(); i++)
        {
            char current = input.charAt(i);
            frecuencies.putIfAbsent(current, 0);
            frecuencies.put(current, frecuencies.get(current) + 1);
        }

        long numberOfOdds = frecuencies.keySet().stream().filter(k -> frecuencies.get(k) != 2).count();
        return (numberOfOdds == 1 && input.length() %2 != 0) || numberOfOdds == 0;
    }

    void urlify(char[] input, int finalLength) {
        for(int i = 0; i < input.length - 2; i++){
            if (input[i] == ' '){
                input[i] = '%';
                for(int j = input.length - 1; j >= i + 1; j--){
                    input[j] = input[j - 2];
                }
                input[i + 1] = '2';
                input[i + 2] = '0';
            }
        }
    }

    private boolean isPermutation(String one, String two) {
        if (one.length() != two.length()) {
            return false;
        }

        char[] oneChars = one.toCharArray();
        char[] twoChars = two.toCharArray();
        Arrays.sort(oneChars, 0, oneChars.length);
        Arrays.sort(twoChars, 0, twoChars.length);

        for(int i = 0; i < one.length(); i++){
            if (oneChars[i] != twoChars[i]) {
                return false;
            }
        }

        return true;
    }

    private boolean isUniqueVector(String isUnique) {
        int vector = 0;
        for (int i = 0; i < isUnique.length(); i++) {
            if ((vector & (1 << isUnique.charAt(i))) != 0) {
                return false;
            }

            vector |= 1 << isUnique.charAt(i);
        }

        return true;
    }

    private boolean isUniqueBooleanArray(String isUnique) {
        boolean[] chars = new boolean[256];
        for (int i = 0; i < isUnique.length(); i++) {
            if (chars[isUnique.charAt(i)]) {
                return false;
            }

            chars[isUnique.charAt(i)] = true;
        }
        return true;
    }

    private boolean isUnique(String isUnique) {
        HashSet<Character> letters = new HashSet<>();
        for (int i = 0; i < isUnique.length(); i++) {
            letters.add(isUnique.charAt(i));
        }

        return letters.size() == isUnique.length();
    }

    private int stringToNumber(String number) {
        boolean isNeg = number.charAt(0) == '-';
        int start = isNeg ? 1 : 0;
        int num = 0;
        for (int i = start; i < number.length(); i++) {
            char charAt = number.charAt(i);
            num *= 10;
            num += charAt - '0';
        }

        return isNeg ? -1 * num : num;
    }

    private String reverseWords(String input) {
        List<Stack<Character>> words = new ArrayList<>();
        Stack<Character> stack = new Stack<>();
        for (int i = input.length() - 1; i >= 0; i--) {
            char charAt = input.charAt(i);
            if (charAt != ' ') {
                stack.push(charAt);
            }
            else
            {
                words.add(stack);
                stack = new Stack<>();
            }
        }
        words.add(stack);

        StringBuilder sb = new StringBuilder();
        for (Stack<Character> word : words) {
            while (!word.empty()) {
                sb.append(word.pop());
            }

            sb.append(' ');
        }

        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    private String removeChars(String input, char[] toRemove) {
        boolean[] toRemoveDict = new boolean[256];
        for (char c : toRemove) {
            toRemoveDict[c] = true;
        }

        char[] removedChars = new char[input.length()];

        int writeTo = 0;
        for (int i = 0; i < input.length(); i++) {
            char charAt = input.charAt(i);
            if (!toRemoveDict[charAt]) {
                removedChars[writeTo] = charAt;
                writeTo++;
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (char c : removedChars) {
            stringBuilder.append(c);
        }
        return stringBuilder.toString().trim();
    }

    char firstNonRepeatedUsingArray(String input) {
        int[] letters = new int[256];
        for (int i = 0; i < input.length(); i++) {
            char charAt = input.charAt(i);
            letters[charAt] = letters[charAt] + 1;
        }

        for (int i = 0; i < input.length(); i++) {
            char charAt = input.charAt(i);
            if (letters[charAt] == 1) {
                return charAt;
            }
        }

        return 0;
    }

    char firstNonRepeatedUsingHash(String input) {
        Map<Character, Integer> frecuencies = new HashMap<>();
        for (int i = 0; i < input.length(); i++) {
            char charAt = input.charAt(i);
            frecuencies.merge(charAt, 1, (a, b) -> a + b);
            /*
            if (count == null) {
                frecuencies.put(charAt, 1);
            }
            else
            {
                frecuencies.put(charAt, count + 1);
            }
             */
        }

        for (int i = 0; i < input.length(); i++) {
            char charAt = input.charAt(i);
            if (frecuencies.get(charAt) == 1) {
                return charAt;
            }
        }

        return 0;
    }
}
