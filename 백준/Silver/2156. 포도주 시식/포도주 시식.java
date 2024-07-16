import java.io.*;
import java.util.*;

//BOJ_2156 포도주 시식
public class Main {
    static int N, res;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[10055];

        for(int i=1; i<=N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[10055];
        dp[1] = arr[1];
        dp[2] = dp[1] + arr[2];

        for(int i=3; i<=N; i++) {
            //2번째 이전값에서
            dp[i] = Math.max(dp[i-3] + arr[i-1] + arr[i], dp[i-2] + arr[i]);
            dp[i] = Math.max(dp[i], dp[i-1]);
        }

        System.out.println(dp[N]);

    }
}