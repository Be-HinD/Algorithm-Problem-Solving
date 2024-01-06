import org.w3c.dom.Node;

import java.io.*;
import java.util.*;

//BOJ_2151 거울 설치
public class Main {
    static int N, res;
    static int[] dx = new int[]{0,0,1,-1};      //
    static int[] dy = new int[]{1,-1,0,0};
    static char[][] map;
    static int[] start = new int[2];
    static int[] dest = new int[2];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb;

        N = Integer.parseInt(br.readLine());

        map = new char[N][N];
        boolean flag = true;
        for(int i=0; i<N; i++) {    //맵 입력
            String input = br.readLine();
            for(int j=0; j<N; j++) {
                char idx = input.charAt(j);
                map[i][j] = idx;
                //임의의 시작위치와 도착위치를 설정
                if(idx == '#') {
                    if(flag) {
                        start[0] = i;
                        start[1] = j;
                        map[i][j] = '@';
                        flag = false;
                    }
                    else {
                        dest[0] = i;
                        dest[1] = j;
                        map[i][j] = '$';
                    }
                }
            }
        }

        res = Integer.MAX_VALUE;
        Dijkstra();

        System.out.println(res);
    }

    private static void Dijkstra() {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];   //거울 설치 개수를 기준
            }
        });

        boolean[][][] v = new boolean[4][N][N];
        for(int i=0; i<4; i++) {
            v[i][start[0]][start[1]] = true;
        }

        //시작위치에서 갈 수 있는 곳들에 대해 큐 추가
        for(int i=0; i<4; i++) {
            int nx = start[0] + dx[i];
            int ny = start[1] + dy[i];
            if(nx<0 || ny<0 || nx>=N || ny>=N || map[nx][ny] == '*') continue;
            pq.offer(new int[]{nx, ny, 0, i});
        }


        while(!pq.isEmpty()) {
            int[] cur = pq.poll();

            int x = cur[0];
            int y = cur[1];

            v[cur[3]][x][y] = true;

            if(map[x][y] == '$') {  //도착했을 경우
                res = cur[2];
                return;
            }

            if(map[x][y] == '!') {      //현재 위치가 거울 설치 가능한 곳일 경우
                if(cur[3] == 2 || cur[3] == 3) {    //좌우
                    for(int i=0; i<2; i++) {
                        int nx = x + dx[i];
                        int ny = y + dy[i];
                        if (nx < 0 || ny < 0 || nx >= N || ny >= N || v[i][nx][ny] || map[nx][ny] == '*') continue;
                        pq.offer(new int[]{nx, ny, cur[2] + 1, i});
                    }
                }
                else {
                    for(int i=2; i<4; i++) {
                        int nx = x + dx[i];
                        int ny = y + dy[i];
                        if (nx < 0 || ny < 0 || nx >= N || ny >= N || v[i][nx][ny] || map[nx][ny] == '*') continue;
                        pq.offer(new int[]{nx, ny, cur[2] + 1, i});
                    }
                }
            }

            //직진 Case
            int nx = x + dx[cur[3]];
            int ny = y + dy[cur[3]];
            if(nx<0 || ny<0 || nx>=N || ny>=N || v[cur[3]][nx][ny] || map[nx][ny] == '*') continue;
            pq.offer(new int[]{nx, ny, cur[2], cur[3]});
        }
    }
}