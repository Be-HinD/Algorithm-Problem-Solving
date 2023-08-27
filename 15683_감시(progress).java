import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static class Point {
        int x;
        int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int N,M,ans;
    static ArrayList<Point> list;
    static int[][] map;
    static int[] dx = {-1, 0, -1, 0}; //방향벡터   02 // 13   3  5%4 1
    static int[] dy = {0, 1, 0, -1}; //북동남서
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        map = new int[N][M];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                int idx = Integer.parseInt(st.nextToken());
                if(idx == 6) {map[i][j] = idx; continue;}
                if(idx > 0) list.add(new Point(i, j));
                map[i][j] = idx;
            }
        }

        //리스트 길이만큼 순열 {1,2,3,4}
        arr = new int[list.size()];
        ans = Integer.MAX_VALUE;
        Permutation(0);
        //순열에 맞춰 차례대로 dfs 탐색
        System.out.println(ans);

    }

    static void Permutation(int cnt) {
        if(cnt == list.size()) {
            //순열완성
            //순열에 맞춰 차례대로 탐색
            dfs();
        }
        else {
            for(int i=0; i<4; i++) {
                arr[cnt] = i;
                Permutation(cnt + 1);
            }
        }
    }

    static void dfs() {
        int[][] copymap = new int[N][M];
        for(int i=0; i<N; i++) { //깊은 복사
            copymap[i] = map[i].clone();
        }
        for(int i=0; i<list.size(); i++) {
            Point p = list.get(i);
            int x = p.x;
            int y = p.y;
            int vector = arr[i];
            int cx = x;
            int cy = y;
            switch(copymap[x][y]) {
                case 1: //1방
                    while(true) {
                        cx += dx[vector];
                        cy += dy[vector];
                        if(cx < 0 || cy < 0 || cx >=N || cy >= M) break;
                        if(copymap[cx][cy] == 6 || copymap[cx][cy] > 0) { //종료 조건
                            break;
                        }
                        copymap[cx][cy] = -1;
                    }
                case 2: //2방
                    for(int j=0; j<2; j++) {
                        while(true) { //cx = cx + dx[vector]
                            int add = (j*2) % 3;
                            cx += dx[vector + add];
                            cy += dy[vector + add];
                            System.out.println("!!" + cx + " : " + cy);
                            if (cx < 0 || cy < 0 || cx >= N || cy >= M) break;
                            if(copymap[cx][cy] == 6 || copymap[cx][cy] > 0) { //종료 조건
                                break;
                            }
                            copymap[cx][cy] = -1;
                        }
                    }
                    break;
                case 3: // 직각 탐색
                    for(int j=0; j<2; j++) {
                        while(true) {
                            cx += (dx[vector] + i) % 4;
                            cy += (dy[vector] + i) % 4;
                            if (cx < 0 || cy < 0 || cx >= N || cy >= M) break;
                            if(copymap[cx][cy] == 6 || copymap[cx][cy] > 0) { //종료 조건
                                break;
                            }
                            copymap[cx][cy] = -1;
                        }
                    }
                    break;
                case 4: //3방향 탐색 반대쪽 탐색 x
                    for(int j=0; j<3; j++) {
                        while(true) {
                            cx += (dx[vector] + i) % 4;
                            cy += (dy[vector] + i) % 4;
                            if (cx < 0 || cy < 0 || cx >= N || cy >= M) break;
                            if(copymap[cx][cy] == 6 || copymap[cx][cy] > 0) { //종료 조건
                                break;
                            }
                            copymap[cx][cy] = -1;
                        }
                    }
                    break;
                case 5: //사방탐색
                    for(int j=0; j<4; j++) {
                        while(true) {
                            cx += (dx[vector] + i) % 4;
                            cy += (dy[vector] + i) % 4;
                            if (cx < 0 || cy < 0 || cx >= N || cy >= M) break;
                            if(copymap[cx][cy] == 6 || copymap[cx][cy] > 0) { //종료 조건
                                break;
                            }
                            copymap[cx][cy] = -1;
                        }
                    }
                    break;
            }
        }
        int count = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                System.out.print(copymap[i][j] + " ");
//                if(copymap[i][j] == 0) count++;
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
        ans = Math.min(ans, count);
    }


}
