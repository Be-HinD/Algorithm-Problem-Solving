import java.io.*;
import java.util.*;

//BOJ_11057
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(br.readLine());

        int[][] dp = new int[10][1001];

        for(int i=1; i<1001; i++) {
            dp[0][i] = 1;
        }

        for(int i=0; i<10; i++) dp[i][1] = i+1;

        for(int i=2; i<1001; i++) {
            for(int j=1; j<10; j++) {
                dp[j][i] = (dp[j-1][i] + dp[j][i-1]) % 10007;
            }
        }

        System.out.println(dp[9][n]);

    }
}