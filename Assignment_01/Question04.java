public class LCS {
    public static void main(String[] args) {

        String S = "MUHAMMADHUZAIFA";
        String T = "ETAOINSHR";

        int n = S.length();
        int m = T.length();

        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (S.charAt(i - 1) == T.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        System.out.println("\nLCS Dynamic Programming Table");
        System.out.print("     ");
        for (char c : T.toCharArray()) System.out.printf("%3c", c);
        System.out.println();

        for (int i = 0; i <= n; i++) {
            if (i == 0) System.out.print("  ");
            else System.out.printf("%c ", S.charAt(i - 1));
            for (int j = 0; j <= m; j++) {
                System.out.printf("%3d", dp[i][j]);
            }
            System.out.println();
        }

        StringBuilder lcs = new StringBuilder();
        int i = n, j = m;

        while (i > 0 && j > 0) {
            if (S.charAt(i - 1) == T.charAt(j - 1)) {
                lcs.append(S.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        lcs.reverse();

        System.out.println("\nResults");
        System.out.println("String 1 : " + S);
        System.out.println("String 2 : " + T);
        System.out.println("LCS Length : " + dp[n][m]);
        System.out.println("LCS : " + lcs.toString());
    }
}
