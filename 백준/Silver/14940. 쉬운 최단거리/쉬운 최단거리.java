import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ_14728
public class Main {
    static int N, M, sx, sy;
    static int[][] map, res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        res = new int[N][M];
        for(int i=0; i<N; i++) Arrays.fill(res[i], -1);
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2) {
                    sx = i;
                    sy = j;
                }
                else if(map[i][j] == 0) {
                    res[i][j] = 0;
                }
            }
        }

        bfs();

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                sb.append(res[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);

    }
    static int[] dx = new int[]{0,0,1,-1};
    static int[] dy = new int[]{1,-1,0,0};
    private static void bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{sx,sy,0});
        boolean[][] v = new boolean[N][M];
        v[sx][sy] = true;
        res[sx][sy] = 0;

        while(!q.isEmpty()) {
            int[] cur = q.poll();

            for(int i=0; i<4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if(nx<0 || ny<0 || nx>=N || ny>=M || v[nx][ny] || map[nx][ny] == 0) continue;
                q.offer(new int[]{nx,ny,cur[2]+1});
                v[nx][ny] = true;
                res[nx][ny] = cur[2]+1;
            }
        }
    }
}