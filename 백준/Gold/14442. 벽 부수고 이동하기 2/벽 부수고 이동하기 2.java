import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, K, res;
    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};
    static int[][] map;
    static boolean[][][] v;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M]; //맵 입력
        for(int i=0; i<N; i++) {
            String input = br.readLine();
            for(int j=0; j<M; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }

        //bfs로 탐색
        //부술 때 마다 새로운 방문배열로
        //
        v = new boolean[N][M][K+1];

        bfs();

        if(res == 0) System.out.println(-1);
        else System.out.println(res);
    }
    private static void bfs() {
        Queue<int[]> pq = new ArrayDeque<>();
        pq.offer(new int[]{0,0,1,K,0}); //좌표, 시간, 벽 부순 개수, 방문배열 포인터
        v[0][0][0] = true;

        while(!pq.isEmpty()) {
            int[] idx = pq.poll();
            if(idx[0] == N-1 && idx[1] == M-1) {
                res = idx[2];
                return;
            }

            for(int i=0; i<4; i++) {
                int nx = idx[0] + dx[i];
                int ny = idx[1] + dy[i];
                if(nx<0 || ny<0 || nx>=N || ny>=M || v[nx][ny][idx[4]]) continue;
                if(map[nx][ny] == 1) { //벽이 있을 경우
                    if(idx[3] > 0) { //벽을 부술 수 있는 경우
                        v[nx][ny][idx[4]+1] = true;
                        pq.offer(new int[]{nx,ny,idx[2]+1, idx[3]-1, idx[4]+1});
                    }
                } else { //벽이 없는 경우
                    v[nx][ny][idx[4]] = true;
                    pq.offer(new int[]{nx,ny,idx[2]+1,idx[3], idx[4]});
                }
            }
        }
    }
}