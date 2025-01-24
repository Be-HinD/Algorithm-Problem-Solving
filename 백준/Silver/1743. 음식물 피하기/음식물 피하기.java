import java.io.*;
import java.util.*;

// BOJ_1743
public class Main {
    static int N, M, K, res;
    static boolean[][] map;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   // 세로
        M = Integer.parseInt(st.nextToken());   // 가로
        K = Integer.parseInt(st.nextToken());   // 음식물 개수

        /**
         * 연결되지 않은 두 뉴런을 연결하거나 이미 연결된 두 뉴런의 연결을 끊는다. -> 싸이클이 발생할 수 있다는 걸 암시...
         * 트리의 형태로 만들어야 한다. << 싸이클이 없고, 루트 노드는 1개만(나머지 노드들은 루트를 가리키고 있어야 함.)
         * **/

        map = new boolean[N+1][M+1];
        for(int i=0; i<K; i++) {    // 음식물 좌표
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[x][y] = true;
        }

        for(int i=0; i<=N; i++) {
            for(int j=0; j<=M; j++) {
                if(map[i][j]) bfs(i,j);
            }
        }

        System.out.println(res);

    }

    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};
    static void bfs(int x, int y) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x,y});
        map[x][y] = false;

        int cnt = 0;
        while(!q.isEmpty()) {
            int[] cur = q.poll();

            cnt++;
            res = Math.max(res, cnt);

            for(int i=0; i<4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if(nx<0 || ny<0 || nx>N || ny>M || !map[nx][ny]) continue;
                q.offer(new int[]{nx,ny});
                map[nx][ny] = false;
            }
        }
    }
}