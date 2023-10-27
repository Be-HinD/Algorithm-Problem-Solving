import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, sx, sy, ex, ey, res;
    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};
    static boolean[][][] v;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        sx = Integer.parseInt(st.nextToken())-1;
        sy = Integer.parseInt(st.nextToken())-1;

        st = new StringTokenizer(br.readLine());
        ex = Integer.parseInt(st.nextToken())-1;
        ey = Integer.parseInt(st.nextToken())-1;

        map = new int[N][M];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        v = new boolean[N][M][2];

        System.out.println(bfs());

    }

    private static int bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{sx,sy,0,1});

        while(!q.isEmpty()) {
            int[] idx = q.poll();
            if(idx[0] == ex && idx[1] == ey) {
                return idx[2];
            }

            for(int i=0; i<4; i++) {
                int nx = idx[0] + dx[i];
                int ny = idx[1] + dy[i];
                if(nx<0 || ny<0 || nx>=N || ny>=M || v[nx][ny][idx[3]]) continue;
                if(map[nx][ny] == 1) { //벽
                    if(idx[3] == 1) { //지팡이 있을 경우
                        v[idx[0]][idx[1]][idx[3]] = true;
                        q.offer(new int[]{nx,ny,idx[2]+1,0});
                    }
                } else { //빈 칸
                    v[nx][ny][idx[3]] = true;
                    q.offer(new int[]{nx, ny, idx[2] + 1, idx[3]});
                }
            }
        }
        return -1;
    }
}