import org.w3c.dom.ranges.Range;

import java.io.*;
import java.util.*;

//BOJ_6087 레이저 통신
public class Main {
    static class POINT {
        int x;
        int y;

        public POINT(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int N, M, res;
    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};
    static char[][] map;
    static POINT start, end;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        int cnt = 0;
        for(int i=0; i<N; i++) {
            String input = br.readLine();
            for(int j=0; j<M; j++) {
                char idx = input.charAt(j);
                map[i][j] = idx;
                if(idx == 'C' && cnt == 0) {    //시작위치
                    start = new POINT(i,j);
                    cnt++;
                }
                else if(idx == 'C' && cnt == 1) {   //상대쪽 위치
                    end = new POINT(i,j);
                }
            }
        }

        Dijkstra();
        System.out.println(res);


    }

    private static void Dijkstra() {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });

        boolean[][][] v = new boolean[4][N][M];

        for(int i=0; i<4; i++) {
            int nx = start.x + dx[i];
            int ny = start.y + dy[i];
            if(!RangeValidation(nx,ny)) continue;
            if(map[nx][ny] == '.') {
                pq.offer(new int[]{nx, ny, 0, i});     //거울 설치 개수, 진행좌표
            }
        }

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();

            if(cur[0] == end.x && cur[1] == end.y) {
                res = cur[2];
                return;
            }

            if(v[cur[3]][cur[0]][cur[1]]) continue;     //방문체크

            v[cur[3]][cur[0]][cur[1]] = true;

            if(cur[3] == 0 || cur[3] == 1) {    //상하에 대해서
                int nx = cur[0] + dx[2];
                int ny = cur[1] + dy[2];
                if(RangeValidation(nx, ny)) {
                    if (map[nx][ny] != '*' && !v[2][nx][ny]) {
                        pq.offer(new int[]{nx, ny, cur[2] + 1, 2});
                    }
                }

                nx = cur[0] + dx[3];
                ny = cur[1] + dy[3];
                if(RangeValidation(nx, ny)) {
                    if (map[nx][ny] != '*' && !v[3][nx][ny]) {
                        pq.offer(new int[]{nx, ny, cur[2] + 1, 3});
                    }
                }

            }
            else {
                int nx = cur[0] + dx[0];
                int ny = cur[1] + dy[0];
                if(RangeValidation(nx, ny)) {
                    if (map[nx][ny] != '*' && !v[0][nx][ny]) {
                        pq.offer(new int[]{nx, ny, cur[2] + 1, 0});
                    }
                }

                nx = cur[0] + dx[1];
                ny = cur[1] + dy[1];
                if(RangeValidation(nx, ny)) {
                    if (map[nx][ny] != '*' && !v[1][nx][ny]) {
                        pq.offer(new int[]{nx, ny, cur[2] + 1, 1});
                    }
                }
            }

            //그냥 직진하는 경우
            int nx = cur[0] + dx[cur[3]];
            int ny = cur[1] + dy[cur[3]];
            if(!RangeValidation(nx, ny)) continue;
            if(map[nx][ny] != '*' && !v[cur[3]][nx][ny]) {
                pq.offer(new int[]{nx,ny,cur[2], cur[3]});
            }
        }
    }

    private static boolean RangeValidation(int x, int y) {
        if(x<0 || y<0 || x>=N || y>=M) return false;
        return true;
    }
}