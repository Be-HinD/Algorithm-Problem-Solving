import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

//BOJ_22115
public class Main {
    static int N, K, res;
    static int[] caffeine;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        /**
         * N = 100, O(N^2)10,000 -> O(N^3)1,000,000 -> O(N^4)100,000,000
         * 햄버거 다이어트 문제랑 비슷한것같음. == N이 20이였음...
         * 반례는 1 2 3  100 150 200 -> K = 300 이 경우는 구간합이 아니게됨.
         * dp로 푼다면 0-1 냅색 최소값 구하기
         * **/

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        caffeine = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            caffeine[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[K+1][N+1];

        for(int i=1; i<=K; i++) {
            Arrays.fill(dp[i], 1000);
        }
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=K; j++) {
                if(j - caffeine[i] >= 0) {
                    dp[j][i] = Math.min(dp[j-caffeine[i]][i-1] + 1, dp[j][i-1]);
                }
                dp[j][i] = Math.min(dp[j][i], dp[j][i-1]);
            }
        }

        System.out.println(dp[K][N] == 1000 ? -1 : dp[K][N]);

    }
}