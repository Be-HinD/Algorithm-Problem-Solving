import java.io.*;
import java.util.*;

//1890
public class Main {
    static int N, res;
    static int[][] map;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        /**
         * 접근법
         * 경로의 개수 -> DFS -> 시간초과
         * 오른쪽 or 아래 -> 무한루프 x
         * DP
         * **/

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        long[][] dp = new long[N][N];
        dp[0][0] = 1; //초기화

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(map[i][j] == 0) continue;
                if(i + map[i][j] < N) {
                    dp[i+map[i][j]][j] += dp[i][j];
                }
                if(j + map[i][j] < N) {
                    dp[i][j+map[i][j]] += dp[i][j];
                }
            }
        }

        System.out.println(dp[N-1][N-1]);

    }
}