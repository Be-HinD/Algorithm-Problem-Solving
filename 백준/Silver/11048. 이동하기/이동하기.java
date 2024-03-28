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

        int[][] arr = new int[N+1][M+1];
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //아래, 오른쪽, 대각선
        int[][] dp = new int[N+1][M+1];

        //dp[i][j] = i,j위치에서 먹을 수 있는 최대 사탕개수
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=M; j++) {
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]) + arr[i][j];
                dp[i][j] = Math.max(dp[i][j], dp[i-1][j-1] + arr[i][j]);
            }
        }

        System.out.println(dp[N][M]);

    }
}