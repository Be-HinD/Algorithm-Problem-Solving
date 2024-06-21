import java.io.*;
import java.util.*;

//BOJ_2748 피보나치 수 2
public class Main {
    static int N;
    static long[] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        dp = new long[95];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;

        for(int i=3; i<dp.length; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }

        System.out.println(dp[N]);
    }
}