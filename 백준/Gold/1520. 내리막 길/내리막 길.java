import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int[] dx = new int[]{-1,1,0,0};
    static int[] dy = new int[]{0,0,1,-1};
    static int[][] map, dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //정점의 개수
        M = Integer.parseInt(st.nextToken()); //간선의 개수

        map = new int[N][M];
        for(int i=0; i<N; i++) { //맵 입력
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[N][M];
        for(int i=0; i<N; i++) { //dp배열 -1로 초기화 : 방문체크 용도
            Arrays.fill(dp[i], -1);
        }

        dfs(0,0);
        System.out.println(dp[0][0]);
    }

    private static int dfs(int x, int y) {
        if(x == N-1 && y == M-1) { //처음으로 도착할 경우
            return 1;
        }

        if (dp[x][y] != -1) { //-1이 아니라면 이미 방문했던 지점
            return dp[x][y];
        }

        //동작부
        dp[x][y] = 0; //방문체크
        for(int i=0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx<0 || ny<0 || nx>=N || ny>=M || map[nx][ny] >= map[x][y]) continue;
            dp[x][y] += dfs(nx, ny); //현재지점 기준으로 4방향의 길을 전부 탐색 후 각각의 경우의 수를 추가.
        }
        return dp[x][y]; //4방탐색이 종료되면 현재 x,y에서 갈 수 있는 경우의 수를 return.
    }
}