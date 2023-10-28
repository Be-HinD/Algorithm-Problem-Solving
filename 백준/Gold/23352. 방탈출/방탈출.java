import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static int N, M, res, diff;
    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};
    static int[][] map;
    static int[] dis;
    static boolean[][] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //행
        M = Integer.parseInt(st.nextToken()); //행

        map = new int[N][M];
        for(int i=0; i<N; i++) { //맵 입력
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //매 좌표마다 0이 아니라면 bfs 수행
        //이전에 기록된 거리 diff보다 이번 bfs 결과값이 같거나 크다면 결과값 갱신
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j] != 0) {
                    bfs(i,j);
                }
            }
        }

        System.out.println(res);
    }

    private static void bfs(int x, int y) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] v = new boolean[N][M];

        q.offer(new int[]{x,y,1});
        v[x][y] = true;


        while(!q.isEmpty()) {
            int[] idx = q.poll();

            //매번 탐색마다 최종거리 수가 기존 최대값 보다 클 경우
            //거리값 diff 갱신
            //비밀번호 res 갱신
            if(diff < idx[2]) { //현재거리가 더 클 경우 res 바로 갱신
                diff = idx[2];
                res = map[x][y] + map[idx[0]][idx[1]];
            }
            else if(diff == idx[2]) { //같은 거리일 경우 기존 res값과 비교 후 갱신
                res = Math.max(res, map[x][y] + map[idx[0]][idx[1]]);
            }
            for(int i=0; i<4; i++) {
                int nx = idx[0] + dx[i];
                int ny = idx[1] + dy[i];
                if(nx<0 || ny<0 || nx>=N || ny>=M || v[nx][ny]) continue;
                if(map[nx][ny] != 0) {
                    q.offer(new int[]{nx, ny, idx[2] + 1});
                    v[nx][ny] = true;
                }
            }
        }
    }
}