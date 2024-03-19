import java.io.*;
import java.util.*;

//BOJ_2293 동전 1
public class Main {
    static int N, K;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[] coins = new int[N+1];
        for(int i=1; i<=N; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[K+1];
        dp[0] = 1;
        for(int i=1; i<=N; i++) {
            int coin = coins[i];
            for(int j=coin; j<=K; j++) {
                dp[j] += dp[j-coin];
            }
        }

        System.out.println(dp[K]);
    }
}