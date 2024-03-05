import java.io.*;
import java.util.Arrays;

//BOJ_2579 계단 오르기
public class Main {
    static int N;
    static int[] arr, dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        arr = new int[301];
        dp = new int[301];

        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        dp[0] = arr[0];
        dp[1] = Math.max(arr[0] + arr[1], arr[1]);  //두번 뛰거나 1,2 밟거나
        dp[2] = Math.max(arr[0] + arr[2], arr[1] + arr[2]);     //1,3 계단 || 2,3 계단

        for(int i=3; i<N; i++) {
            dp[i] = Math.max(dp[i-2]+arr[i], arr[i-1]+arr[i]+dp[i-3]);      //두계단 뛰었을 경우 || N-3에서 N-1, N 올랐을 경우
        }

//        System.out.println(Arrays.toString(dp));
        System.out.println(dp[N-1]);
    }
}