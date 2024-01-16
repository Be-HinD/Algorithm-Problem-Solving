import java.io.*;
import java.util.*;

//BOJ_16197 두 동전
public class Main {
    static int N, M, res;
    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};
    static char[][] map;
    static boolean[][][][] balls;
    static int[] ball = new int[4];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
//        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        balls = new boolean[N][M][N][M];

        map = new char[N][M];
        int p = 0;
        for(int i=0; i<N; i++) {
            String input = br.readLine();
            for(int j=0; j<M; j++) {
                char idx = input.charAt(j);
                if(idx == 'o') { //동전일 경우
                    ball[p++] = i;
                    ball[p++] = j;
                }
                map[i][j] = idx;
            }
        }

        bfs();

        System.out.println(res);


    }

    private static void bfs() {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[4] - o2[4];
            }
        });
        pq.offer(new int[]{ball[0], ball[1], ball[2], ball[3], 0});

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();

            if(cur[4] > 9) {
                res = -1;
                return;
            }
            if(balls[cur[0]][cur[1]][cur[2]][cur[3]]) continue;

            balls[cur[0]][cur[1]][cur[2]][cur[3]] = true;

            for(int i=0; i<4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                int xx = cur[2] + dx[i];
                int yy = cur[3] + dy[i];

                if((nx<0 || ny<0 || nx>=N || ny>=M) && (xx<0 || yy<0 || xx>=N || yy>=M)) continue;

                if(nx<0 || ny<0 || nx>=N || ny>=M) {
                    res = cur[4]+1;
                    return;
                }
                if(xx<0 || yy<0 || xx>=N || yy>=M) {
                    res = cur[4]+1;
                    return;
                }


                if(map[nx][ny] == '#') {
                    nx = cur[0];
                    ny = cur[1];
                }

                if(map[xx][yy] == '#') {
                    xx = cur[2];
                    yy = cur[3];
                }

                pq.offer(new int[]{nx,ny,xx,yy,cur[4]+1});
            }
        }
        res = -1;
    }
}