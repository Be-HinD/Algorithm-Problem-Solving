import java.io.*;
import java.util.*;

//BOJ_9465
public class Main {
    static int T, N, res;
    static int[][] dp;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        /**
         * dp가 될 수도 있는데 ->  i,j 스티커에 대해서 (i, j-2), (i+1, j-1)
         * **/

        T = Integer.parseInt(br.readLine());

        for(int tc=0; tc<T; tc++) {

            res = 0;
            N = Integer.parseInt(br.readLine());
            dp = new int[2][N+1];
            int[][] arr = new int[2][N+1];
            for(int i=0; i<2; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=1; j<=N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for(int i=1; i<=N; i++) {
                for(int j=0; j<2; j++) {
                    if(j==0) {
                        dp[j][i] = Math.max(dp[1][i-1] + arr[j][i], dp[j][i-1]);
                    }
                    else {
                        dp[j][i] = Math.max(dp[0][i-1] + arr[j][i], dp[j][i-1]);
                    }
                }
            }

            sb.append(Math.max(dp[0][N], dp[1][N])).append("\n");
        }

        System.out.println(sb);

    }
}