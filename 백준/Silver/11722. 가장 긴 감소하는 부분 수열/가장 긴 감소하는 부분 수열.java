import java.io.*;
import java.util.*;

//BOJ_11722
public class Main {
    static int n, res;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) arr[i] = Integer.parseInt(st.nextToken());

        int[] dp = new int[n];

        for(int i=0; i<n; i++) {
            dp[i] = 1;
            for(int j=0; j<i; j++) {
                if(arr[j] > arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        
        for(int idx : dp) res = Math.max(res, idx);

        System.out.println(res);
    }
}
