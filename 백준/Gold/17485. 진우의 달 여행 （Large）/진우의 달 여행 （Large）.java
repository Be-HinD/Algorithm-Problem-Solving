import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] arr;
    static int[][][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        dp = new int[N][M][3];

        for(int i = 0 ; i < N ; i ++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M ; j ++){
                arr[i][j] = Integer.parseInt(st.nextToken());

                Arrays.fill(dp[i][j], 1_000_100);
            }
        }

        for(int i = 0 ; i < M ; i ++){
            for(int j = 0 ; j < 3 ; j ++){
                dp[0][i][j] = arr[0][i]; // dp 초기값 저장
            }
        }

        // 왼쪽 0, 가운데 1, 오른쪽 2
        for(int i = 1 ; i < N ; i ++){ // 행
            for(int j = 0 ; j < M ; j ++){ // 열
                if(j == 0){ // 열이 0이라면 => 가운데, 오른쪽 에서 올 수 있음
                    dp[i][j][1] = dp[i-1][j][2] + arr[i][j];
                    dp[i][j][2] = Math.min(dp[i-1][j+1][1], dp[i-1][j+1][0]) + arr[i][j];
                }
                else if(j < M-1){ // 왼쪽, 가운데, 오른쪽에서 올 수 있음
                    dp[i][j][2] = Math.min(dp[i - 1][j + 1][1], dp[i - 1][j + 1][0]) + arr[i][j];
                    dp[i][j][1] = Math.min(dp[i - 1][j][0], dp[i - 1][j][2]) + arr[i][j];
                    dp[i][j][0] = Math.min(dp[i - 1][j - 1][2], dp[i - 1][j - 1][1]) + arr[i][j];
                }
                else{
                    dp[i][j][0] = Math.min(dp[i - 1][j - 1][1], dp[i - 1][j - 1][2]) + arr[i][j];
                    dp[i][j][1] = dp[i-1][j][0] + arr[i][j];
                }
            }
        }

        int ans = 1 << 30;
        for (int i = 0; i < M; i ++) {
            for (int j = 0; j < 3; j ++)
                ans = Math.min(ans, dp[N - 1][i][j]);
        }

        System.out.println(ans);
    }
}