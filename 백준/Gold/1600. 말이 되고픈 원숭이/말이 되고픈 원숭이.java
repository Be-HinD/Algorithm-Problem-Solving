import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int K, N, M, res;
    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};
    static int[] ndx = new int[]{-2,-2,-1,-1,1,1,2,2};
    static int[] ndy = new int[]{-1,1,-2,2,-2,2,-1,1};
    static int[][] map;
    static boolean[][][] v;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        K = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][M]; //입력 맵
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        if(N == 1 && M == 1) {
            System.out.println(0);
            return;
        }
        v = new boolean[K+1][N][M];

        bfs();

        if(res == 0) System.out.println(-1);
        else System.out.println(res);
    }

    private static void bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0,0,0,K}); //좌표, 소요시간, 말 행동횟수
        v[K][0][0] = true;
        boolean flag;

        while(!q.isEmpty()) {
            int[] idx = q.poll();

            if(idx[0] == N-1 && idx[1] == M-1) { //도착
                res = idx[2];
                return;
            }

            if(idx[3] > 0) flag = true;
            else flag = false;

            if(flag) { //말처럼 행동할 수 있을 경우
                for(int i=0; i<8; i++) { //말처럼 처럼 행동할 수도 있고
                    int nx = idx[0] + ndx[i];
                    int ny = idx[1] + ndy[i];
                    if (nx < 0 || ny < 0 || nx >= N || ny >= M || v[idx[3]-1][nx][ny] || map[nx][ny] == 1) continue;
                    v[idx[3]-1][nx][ny] = true;
                    q.offer(new int[]{nx,ny,idx[2]+1,idx[3]-1});
                }
            }
            //기존에 원숭이 처럼 인접 칸으로 행동할 경우
            for (int i = 0; i < 4; i++) {
                int nx = idx[0] + dx[i];
                int ny = idx[1] + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M || v[idx[3]][nx][ny] || map[nx][ny] == 1) continue;
                v[idx[3]][nx][ny] = true;
                q.offer(new int[]{nx,ny,idx[2]+1,idx[3]});
            }
        }
    }
}