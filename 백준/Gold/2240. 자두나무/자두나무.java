import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int T, W, zero, one, result;
    static int[] input;
    static int[][][] dp;

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine(), " ");
        T = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        input = new int[T + 1];
        dp = new int[W + 1][2][T + 1];
        for (int i = 1; i <= T; i++) {
            input[i] = Integer.parseInt(br.readLine()) - 1;
        } // end of input

        dp[0][1][1] = input[1] == 1 ? -1 : 0;

        for (int t = 1; t <= T; t++) {
            if (input[t] == 0) {
                zero = 1;
                one = 0;
            } else {
                zero = 0;
                one = 1;
            }

            dp[0][0][t] = dp[0][0][t - 1] + zero;

            for (int w = 1; w <= W; w++) {
                dp[w][0][t] = Math.max(dp[w][0][t - 1], dp[w - 1][1][t - 1]) + zero;
                dp[w][1][t] = Math.max(dp[w][1][t - 1], dp[w - 1][0][t - 1]) + one;
            }
        }

        for (int i = 0; i <= W; i++) {
            for (int j = 0; j < 2; j++) {
                result = Math.max(result, dp[i][j][T]);
            }
        }

        System.out.print(result);

    }
	
}