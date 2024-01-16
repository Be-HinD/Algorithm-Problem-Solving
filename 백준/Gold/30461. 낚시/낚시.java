import java.io.*;
import java.util.*;

//BOJ_30461 낚시
public class Main {
    static int N, M, Q;
    static int[][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken()) + 1;
        M = Integer.parseInt(st.nextToken()) + 1;
        Q = Integer.parseInt(st.nextToken());

        //누적합으로 1,1부터 전처리
        dp = new int[N][M];
        for(int i=1; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<M; j++) {
                dp[i][j] = dp[i-1][j-1] + dp[i-1][j] + Integer.parseInt(st.nextToken()); //대각선 + 바로 위 누적합 + 현재 입력값
                if(i>1) {
                    dp[i][j] -= dp[i-2][j-1];
                }
            }
        }

        for(int i=0; i<Q; i++) {
            st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken());
            int P = Integer.parseInt(st.nextToken());
            sb.append(dp[W][P]).append("\n");
        }
        
//        for(int i=0; i<N; i++) System.out.println(Arrays.toString(dp[i]));
        System.out.println(sb);
    }
}