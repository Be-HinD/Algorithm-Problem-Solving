import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, ans;
    static int[][] map, v;
    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());;

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        v = new int[N][M]; //메모이제이션 배열
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j) - '0';
                v[i][j] = Integer.MAX_VALUE;
            }
        }

        ans = bfs();
        System.out.println(ans);
    }

    private static int bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0,0});
        v[0][0] = 0;

        while(!queue.isEmpty()) {
            int[] idx = queue.poll();
            int cost = v[idx[0]][idx[1]];
            for(int i=0; i<4; i++) {
                int nx = idx[0] + dx[i];
                int ny = idx[1] + dy[i];
                if(nx<0 || ny<0 || nx>=N || ny>=M) continue;
                int wall = 0;
                if(map[nx][ny] == 1) { //다음 행선지가 벽이라면
                    wall = 1;
                }
                if(v[nx][ny] <= cost + wall) continue; //기존 v배열의 값보다 크다면 큐에 추가할 필요 없음.
                queue.offer(new int[]{nx,ny});
                v[nx][ny] = cost + wall;
            }
        }
        return v[N-1][M-1];
    }
}
