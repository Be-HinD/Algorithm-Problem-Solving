import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

//BOJ_11055 가장 큰 증가하는 부분 수열
public class Main {
    static int res;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        int[] dp = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i] = arr[i];
        }

        //그리디 풀이 : 배열 N만큼 반복하면서 비교
        //DP 풀이 : dp[i]는 수열의 i번째 원소를 마지막으로 하는 가장 큰 증가 부분 수열의 합

        for(int i=1; i<N; i++) {
            for(int j=0; j<i; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + arr[i]);
                }
            }
        }
        
        for(int i=0; i<N; i++) {
            res = Math.max(res, dp[i]);
        }

        System.out.println(res);

    }
}