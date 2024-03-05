import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

//BOJ_9095 1,2,3 더하기
public class Main {
    static int N;
    static int[] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        dp = new int[12];

        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        for(int i=4; i<12; i++) {
            dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
        }


        for(int t=0; t<N; t++) {
            int target = Integer.parseInt(br.readLine());
            sb.append(dp[target]).append("\n");

        }

        System.out.println(sb);

    }
}