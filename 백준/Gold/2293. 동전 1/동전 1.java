import java.io.*;
import java.util.*;

//BOJ_2293
public class Main {
    static int n, k;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        int[] dp = new int[k+1];
        dp[0] = 1;

        int[] coins = new int[n];
        for(int i=0; i<n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        for(int i=1; i<=n; i++) {
            int coin = coins[i-1];
            for(int j=0; j<=k; j++) {
                if(j >= coin) {
                    dp[j] += dp[j-coin];
                }
            }
        }

        System.out.println(dp[k]);

    }
}