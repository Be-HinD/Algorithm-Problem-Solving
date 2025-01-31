import java.io.*;
import java.util.*;

//BOJ_1325
public class Main {
    static int N;
    static int[] arr, dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        dp = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i] = Integer.MAX_VALUE;
        }

        dp[0] = 0;
        for(int i=0; i<N; i++) {
            if(dp[i] == Integer.MAX_VALUE) continue;
            for(int j=i+1; j<=i+arr[i]; j++) {
                if(j >= N) break;
                if(dp[j] > dp[i] + 1) {
                    dp[j] = dp[i] + 1;
                }
            }
        }

        System.out.println(dp[N-1] == Integer.MAX_VALUE ? -1 : dp[N-1]);

    }
}