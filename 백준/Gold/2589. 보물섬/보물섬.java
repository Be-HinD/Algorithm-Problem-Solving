import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ_2589 보물섬
public class Main {
    static int N, M, res;
    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for(int i=0; i<N; i++) {
            String input = br.readLine();
            for(int j=0; j<M; j++) {
                map[i][j] = input.charAt(j);
            }
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j] == 'L') bfs(i, j);
            }
        }

        System.out.println(res);
    }

    private static void bfs(int x, int y) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] v = new boolean[N][M];
        q.offer(new int[]{x,y,0});
        v[x][y] = true;

        while(!q.isEmpty()) {
            int[] idx = q.poll();

            res = Math.max(res, idx[2]);
            for(int i=0; i<4; i++) {
                int nx = idx[0] + dx[i];
                int ny = idx[1] + dy[i];
                if(nx<0 || ny<0 || nx>=N || ny>=M || v[nx][ny] || map[nx][ny] == 'W') continue;
                v[nx][ny] = true;
                q.offer(new int[]{nx,ny, idx[2]+1});
            }
        }
    }
}