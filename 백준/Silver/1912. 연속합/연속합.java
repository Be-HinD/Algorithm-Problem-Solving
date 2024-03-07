import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

//BOJ_1912 연속합
public class Main {
    static int N;
    static long[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new long[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }


        long res = -1000055;
        long[] dp = new long[100055];
        dp[0] = arr[0];
        for(int i=1; i<N; i++) {
            dp[i] = Math.max(arr[i], dp[i-1] + arr[i]);
        }

        for(int i=0; i<N; i++) {
            res = Math.max(res, dp[i]);
        }
        System.out.println(res);

    }
}