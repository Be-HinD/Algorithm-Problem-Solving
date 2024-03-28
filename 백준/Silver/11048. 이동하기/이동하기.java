import java.io.*;
import java.util.*;

//BOJ_11048 이동하기
public class Main {
    static int N, M;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); //배낭 무게
        int M = Integer.parseInt(st.nextToken()); //귀금속 종류


        int[][] dp = new int[N+1][M+1];
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=M; j++) {
                int idx = Integer.parseInt(st.nextToken());
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]) + idx;
                dp[i][j] = Math.max(dp[i][j], dp[i-1][j-1] + idx);
            }
        }

        System.out.println(dp[N][M]);

    }
}