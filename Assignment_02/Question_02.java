//Input for Q2:
//Text: Insertion sort typically has a smaller constant factor than merge sort
//Pattern: sort
import java.util.*;

public class Main {

    static int[] buildBadChar(String pattern) {
        int[] badChar = new int[256];
        Arrays.fill(badChar, -1);

        for (int i = 0; i < pattern.length(); i++)
            badChar[pattern.charAt(i)] = i;

        return badChar;
    }

    static int[] buildGoodSuffix(String pattern) {
        int m = pattern.length();
        int[] shift = new int[m + 1];
        int[] border = new int[m + 1];

        Arrays.fill(shift, 0);

        int i = m, j = m + 1;
        border[i] = j;

        while (i > 0) {
            while (j <= m && pattern.charAt(i - 1) != pattern.charAt(j - 1)) {
                if (shift[j] == 0) shift[j] = j - i;
                j = border[j];
            }
            i--; j--;
            border[i] = j;
        }

        j = border[0];
        for (i = 0; i <= m; i++) {
            if (shift[i] == 0) shift[i] = j;
            if (i == j) j = border[j];
        }

        return shift;
    }

    static void boyerMoore(String text, String pattern) {
        int[] badChar = buildBadChar(pattern);
        int[] goodSuffix = buildGoodSuffix(pattern);

        int n = text.length();
        int m = pattern.length();

        int s = 0;
        boolean found = false;

        while (s <= n - m) {
            int j = m - 1;

            while (j >= 0 && pattern.charAt(j) == text.charAt(s + j))
                j--;

            if (j < 0) {
                System.out.println("Pattern found at index: " + s);
                found = true;
                s += goodSuffix[0];
            } else {
                int bc = j - badChar[text.charAt(s + j)];
                int gs = goodSuffix[j + 1];
                s += Math.max(bc, gs);
            }
        }

        if (!found)
            System.out.println("Pattern not found.");
    }

    public static void main(String[] args) {

        String text = "Insertion sort typically has a smaller constant factor than merge sort";
        String pattern = "sort";

        System.out.println("Boyer-Moore String Matching\n");

        System.out.println("Boyer-Moore String Matching\n");
        System.out.println("Text   : " + text);
        
        boyerMoore(text, pattern);
    }
}
