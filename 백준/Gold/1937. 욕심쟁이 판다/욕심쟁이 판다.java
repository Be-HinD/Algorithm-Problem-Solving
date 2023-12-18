import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ_1937 욕심쟁이 판다
public class Main {
    static int N, res;
    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};
    static int[][] map, dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dp = new int[N][N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                dp[i][j] = dfs(i,j);
                res = Math.max(res, dp[i][j]);
            }
        }

        System.out.println(res);
    }

    private static int dfs(int r, int c) {
        //동작
        for(int i=0; i<4; i++) {
            int nx = r + dx[i];
            int ny = c + dy[i];
            if(nx<0 || ny<0 || nx>=N || ny>=N) continue;

            if(map[r][c] < map[nx][ny]) { //다음 진행방향으로 나아갈 수 있는 경우
                if(dp[nx][ny] == 0) { //다음 진행방향이 탐색되지 않은 좌표라면
                    dp[r][c] = Math.max(dp[r][c], dfs(nx, ny)+1);
                }
                else { //이미 탐색된 좌표일 경우 해당 좌표의 dp값+1
                    dp[r][c] = Math.max(dp[r][c], dp[nx][ny]+1);
                }
            }
        }
        if(dp[r][c] == 0) return 1; //아무곳도 갈 수 없었던 경우
        return dp[r][c];
    }
}