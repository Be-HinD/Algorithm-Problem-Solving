import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, ans;
    static int[][] map;
    static boolean[][] v;
    static int[] dx = new int[]{-1,1,0,0}; //방향벡터
    static int[] dy = new int[]{0,0,1,-1};
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i=0; i<N; i++) { //맵 입력
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        v = new boolean[N][M]; //탐색 전 방문배열 초기화
        for(int i=0; i<N; i++) { //전 좌표에서 dfs
            for (int j = 0; j<M; j++) {
                v[i][j] = true;
                dfs(i, j, 1, map[i][j]);
                v[i][j] = false;
            }
        }
        System.out.println(ans);
    }
    private static void dfs(int x, int y, int cnt, int sum) {
        if(cnt == 4) {
            //블록 완성
            ans = Math.max(ans, sum);
        } else {
            for(int i=0; i<4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx<0 || ny<0 || nx>=N || ny>=M || v[nx][ny]) continue;
                v[nx][ny] = true;
                //마지막 테트로미노 탐색을 위해 2개 블록을 선택할 때 현재좌표에서 재탐색 할 수 있도록
                if(cnt == 2) {
                    dfs(x, y, cnt+1, sum+map[nx][ny]);
                    v[nx][ny] = false; //백트래킹
                }
                dfs(nx, ny, cnt+1, sum+map[nx][ny]);
                v[nx][ny] = false;
            }
        }
    }
}
