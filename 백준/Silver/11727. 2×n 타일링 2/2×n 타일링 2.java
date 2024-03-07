import java.io.*;
import java.util.StringTokenizer;

//BOJ_11727 2*n 타일링 2
public class Main {
    static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        int[] dp = new int[1001];

        dp[1] = 1;
        dp[2] = 3;
        for(int i=3; i<=N; i++) {
            dp[i] = (dp[i-1] + (dp[i-2]*2)) % 10007;
        }

        System.out.println(dp[N]);

    }
}