import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

//BOJ_17485
public class Main {
    static int N, M, res;
    static int[][] map;
    static int[][][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        /**
         * BFS 풀이의 경우 시작지점 1000개에 대해 각각 돌려야하므로 O(1000 * 1000 * 1000) = 10억으로 시간초과
         * 메모이제이션을 통해 i,j에대해 이전 행동에 대한 최소값을 갱신
         * 왼쪽위, 위, 오른쪽위
         * **/

        dp = new int[N][M][3];  //3가지 움직임에 대한 3차원 배열

        for(int i=0; i<M; i++) {    //시작점 초기화
            dp[0][i][0] = map[0][i];
            dp[0][i][1] = map[0][i];
            dp[0][i][2] = map[0][i];
        }

        for(int i=1; i<N; i++) {
            for(int j=0; j<M; j++) {

                if (j == 0) {
                    dp[i][j][0] = 10000000;
                    dp[i][j][1] = Math.min(dp[i-1][j][0] + map[i][j], dp[i-1][j][2] + map[i][j]);
                    dp[i][j][2] = Math.min(dp[i-1][j+1][0] + map[i][j], dp[i-1][j+1][1] + map[i][j]);
                }
                else if(j == M-1) {
                    dp[i][j][0] = Math.min(dp[i-1][j-1][1] + map[i][j], dp[i-1][j-1][2] + map[i][j]);
                    dp[i][j][1] = Math.min(dp[i-1][j][0] + map[i][j], dp[i-1][j][2] + map[i][j]);
                    dp[i][j][2] = 10000000;
                }
                else {
                    dp[i][j][0] = Math.min(dp[i-1][j-1][1] + map[i][j], dp[i-1][j-1][2] + map[i][j]);
                    dp[i][j][1] = Math.min(dp[i-1][j][0] + map[i][j], dp[i-1][j][2] + map[i][j]);
                    dp[i][j][2] = Math.min(dp[i-1][j+1][0] + map[i][j], dp[i-1][j+1][1] + map[i][j]);
                }

            }
        }

        res = Integer.MAX_VALUE;
        for(int i=0; i<M; i++) {
            for(int j=0; j<3; j++) {
                res = Math.min(res, dp[N-1][i][j]);
            }
        }

        System.out.println(res);

    }
}