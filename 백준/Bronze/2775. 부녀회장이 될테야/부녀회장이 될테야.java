import java.io.*;
import java.util.*;

//BOJ_2775 부녀회장이 될테야
public class Main {
    static int T, tc, k, n;
    static int[][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        while(tc++ < T) {
            k = Integer.parseInt(br.readLine());    //층
            n = Integer.parseInt(br.readLine());    //호

            dp = new int[k+1][n+1];

            // 0층은 호의 수만큼 사람 수 고정 == 초기화
            for(int i=0; i<=n; i++) {
                dp[0][i] = i;
            }

            for(int i=1; i<=k; i++) {
                for(int j=1; j<=n; j++) {
                    for(int o=1; o<=j; o++) {
                        dp[i][j] += dp[i-1][o];
                    }
                }
            }

            sb.append(dp[k][n]).append("\n");

        }

        System.out.println(sb);

    }
}