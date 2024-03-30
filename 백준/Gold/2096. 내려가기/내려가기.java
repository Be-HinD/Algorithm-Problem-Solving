import java.io.*;
import java.util.*;

//BOJ_2096 내려가기
public class Main {
    static int N, M;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N][3];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[N][3];
        int[][] minDp = new int[N][3];
        dp[0][0] = arr[0][0];
        dp[0][1] = arr[0][1];
        dp[0][2] = arr[0][2];

        minDp[0][0] = arr[0][0];
        minDp[0][1] = arr[0][1];
        minDp[0][2] = arr[0][2];


        //i열 == 0 일 떄, 0 or 1
        //i열 == 1 일 떄, 0 or 1 or 2
        //i열 == 2 일 떄, 1 or 2
        for(int i=1; i<N; i++) {
            for(int j=0; j<3; j++) {
                if(j==0) {
                    dp[i][j] = Math.max(dp[i-1][0], dp[i-1][1]) + arr[i][j];
                    minDp[i][j] = Math.min(minDp[i-1][0], minDp[i-1][1]) + arr[i][j];
                }
                else if(j==1) {
                    dp[i][j] = Math.max(dp[i-1][2], Math.max(dp[i-1][0], dp[i-1][1])) + arr[i][j];
                    minDp[i][j] = Math.min(minDp[i-1][2], Math.min(minDp[i-1][0], minDp[i-1][1])) + arr[i][j];
                }
                else {
                    dp[i][j] = Math.max(dp[i-1][1], dp[i-1][2]) + arr[i][j];
                    minDp[i][j] = Math.min(minDp[i-1][1], minDp[i-1][2]) + arr[i][j];
                }
            }
        }

        int minRes = Integer.MAX_VALUE;
        int maxRes = 0;
        for(int i=0; i<3; i++) {
            minRes = Math.min(minRes, minDp[N-1][i]);
            maxRes = Math.max(maxRes, dp[N-1][i]);
        }

        System.out.println(maxRes + " " + minRes);
    }
}