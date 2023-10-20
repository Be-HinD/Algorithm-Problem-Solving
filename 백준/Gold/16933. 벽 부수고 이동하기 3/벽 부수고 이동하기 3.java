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

        v = new boolean[N][M][K+1]; //부술 수 있는 개수만큼

        if(N == 1 && M == 1) { //시작 전처리
            System.out.println(1);
            return;
        }

        bfs();

        if(res == 0) System.out.println(-1);
        else System.out.println(res);
    }
    private static void bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0,0,1,K,0}); //좌표, 시간, 벽 부순 개수, 낮과 밤 플래그
        v[0][0][K] = true;

        while(!q.isEmpty()) {
            int[] idx = q.poll();

            int t = idx[2]; //경과시간
            int vi = idx[3]; //벽 부순 개수
            int day = idx[4]; //낮밤 플래그

            if(idx[0] == N-1 && idx[1] == M-1) { //기저조건
                res = idx[2];
                return;
            }
            for(int i=0; i<4; i++) {
                int nx = idx[0] + dx[i];
                int ny = idx[1] + dy[i];
                if(nx<0 || ny<0 || nx>=N || ny>=M || v[nx][ny][vi]) continue;
                if(day == 0) { //낮일 때
                    if(map[nx][ny] == 1 && idx[3] > 0) { //벽이 있고 && 부술 수 있는 경우
                        v[nx][ny][vi] = true; //부술 때 현재기준 방문체크! (다음 방문배열 기준 체크할 경우 메모리초과 발생)
                        q.offer(new int[]{nx,ny,t+1, vi-1, 1}); //부술 때 마다 새로운 방문배열로
                    } else if(map[nx][ny] == 0){ //벽이 없을 경우
                        v[nx][ny][vi] = true;
                        q.offer(new int[]{nx,ny,t+1, vi, 1});
                    }
                } else { //밤일 때
                    if(map[nx][ny] == 0) { //벽이 없을 때
                        v[nx][ny][vi] = true;
                        q.offer(new int[]{nx,ny,t+1, vi, 0});
                    } else if(map[nx][ny] == 1 && idx[3] > 0){ //벽이 있고, 부술 수 있지만 밤일 때
                        q.offer(new int[]{idx[0], idx[1], idx[2]+1, idx[3], 0}); //제자리에서 기다리는 경우의 수 추가
                    }
                }
            }
        }
    }
}