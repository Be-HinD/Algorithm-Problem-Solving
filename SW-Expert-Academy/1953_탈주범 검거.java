import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static class Point { // map 좌표 객체
        int x;
        int y;
        int block;
        int time;
        Point(int x, int y, int block, int time) {
            this.x = x;
            this.y = y;
            this.block = block;
            this.time = time;
        }
    }
    static int N, M, R, C, L, ans;
    static int[] dx = new int[] {1,-1,0,0}; //방향벡터
    static int[] dy = new int[] {0,0,-1,1}; //남,북,서,동
    static boolean[][] visited;
    static Point[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int tc = Integer.parseInt(st.nextToken());
        for(int t=0; t<tc; t++) { //TestCase
            ans = 0;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); //map
            M = Integer.parseInt(st.nextToken()); //map
            R = Integer.parseInt(st.nextToken()); //탈주범 위치
            C = Integer.parseInt(st.nextToken()); //탈주범 위치
            L = Integer.parseInt(st.nextToken()); //탈주 소요시간

            map = new Point[N][M];
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<M; j++) {
                    int block = Integer.parseInt(st.nextToken());
                    Point p = new Point(i, j, block, 0);
                    map[i][j] = p;
                }
            }
            visited = new boolean[N][M];
            bfs(map[R][C]);
            System.out.println("#" + (t+1) + " " + ans);
        }
    }

    private static void bfs(Point p) {
        Queue<Point> queue = new ArrayDeque<>();
        queue.offer(new Point(p.x, p.y, p.block, p.time + 1));
        visited[p.x][p.y] = true; //방문체크
        while(!queue.isEmpty()) {
            Point item = queue.poll();
            ans++;
            if(item.time == L) {
                continue;
            }
            int idx = item.block;
            int nx= item.x;
            int ny= item.y;
            switch(idx) {
                case 1:
                    for(int i=0; i<4; i++) {
                        int qx = nx + dx[i];
                        int qy = ny + dy[i];
                        if(qx < 0 || qy < 0 || qx >= N || qy >=M) continue;
                        if(map[qx][qy].block == 0) continue;
                        if(!visited[qx][qy]) {
                            if(!CheckMethod(map[qx][qy], 1, i)) continue;
                            queue.offer(new Point(qx, qy, map[qx][qy].block, item.time + 1));
                            visited[qx][qy] = true;
                        }
                    }
                    break;
                case 2:
                    for(int i=0; i<2; i++) {
                        int qx = nx + dx[i];
                        int qy = ny + dy[i];

                        if(qx < 0 || qy < 0 || qx >= N || qy >=M) continue;
                        if(map[qx][qy].block == 0) continue;
                        if(!visited[qx][qy]) {
                            if(!CheckMethod(map[qx][qy], 2, i)) continue;
                            queue.offer(new Point(qx, qy, map[qx][qy].block, item.time + 1));
                            visited[qx][qy] = true;
                        }
                    }
                    break;
                case 3:
                    for(int i=2; i<4; i++) {
                        int qx = nx + dx[i];
                        int qy = ny + dy[i];
                        if(qx < 0 || qy < 0 || qx >= N || qy >=M) continue;
                        if(map[qx][qy].block == 0) continue;
                        if(!visited[qx][qy]) {
                            if(!CheckMethod(map[qx][qy], 3, i)) continue;
                            queue.offer(new Point(qx, qy, map[qx][qy].block, item.time + 1));
                            visited[qx][qy] = true;
                        }
                    }
                    break;
                case 4:
                    int qx = nx + dx[1];
                    int qy = ny + dy[1];
                    if(qx >= 0 && qy >= 0 && qx < N && qy < M && map[qx][qy].block != 0) {
                        if (!visited[qx][qy]) {
                            if (CheckMethod(map[qx][qy], 4, 1)) {
                                queue.offer(new Point(qx, qy, map[qx][qy].block, item.time + 1));
                                visited[qx][qy] = true;
                            }
                        }
                    }

                    qx = nx + dx[3];
                    qy = ny + dy[3];
                    if(qx >= 0 && qy >= 0 && qx < N && qy < M && map[qx][qy].block != 0) {
                        if (!visited[qx][qy]) {
                            if(!CheckMethod(map[qx][qy], 4, 3)) continue;
                            queue.offer(new Point(qx, qy, map[qx][qy].block, item.time + 1));
                            visited[qx][qy] = true;
                        }
                    }
                    break;
                case 5:
                    qx = nx + dx[0];
                    qy = ny + dy[0];
                    if(qx >= 0 && qy >= 0 && qx < N && qy < M && map[qx][qy].block != 0) {
                        if (!visited[qx][qy]) {
                            if (CheckMethod(map[qx][qy], 5, 0)) {
                                queue.offer(new Point(qx, qy, map[qx][qy].block, item.time + 1));
                                visited[qx][qy] = true;
                            }
                        }
                    }

                    qx = nx + dx[3];
                    qy = ny + dy[3];
                    if(qx >= 0 && qy >= 0 && qx < N && qy < M && map[qx][qy].block != 0) {
                        if (!visited[qx][qy]) {
                            if(!CheckMethod(map[qx][qy], 5, 3)) continue;
                            queue.offer(new Point(qx, qy, map[qx][qy].block, item.time + 1));
                            visited[qx][qy] = true;
                        }
                    }
                    break;
                case 6:
                    qx = nx + dx[0];
                    qy = ny + dy[0];
                    if(qx >= 0 && qy >= 0 && qx < N && qy < M && map[qx][qy].block != 0) {
                        if (!visited[qx][qy]) {
                            if(CheckMethod(map[qx][qy], 6, 0)) {
                                queue.offer(new Point(qx, qy, map[qx][qy].block, item.time + 1));
                                visited[qx][qy] = true;
                            }
                        }
                    }

                    qx = nx + dx[2];
                    qy = ny + dy[2];
                    if(qx >= 0 && qy >= 0 && qx < N && qy < M && map[qx][qy].block != 0) {
                        if (!visited[qx][qy]) {
                            if (CheckMethod(map[qx][qy], 6, 2)) {
                                queue.offer(new Point(qx, qy, map[qx][qy].block, item.time + 1));
                                visited[qx][qy] = true;
                            }
                        }
                    }
                    break;
                case 7:
                    qx = nx + dx[1];
                    qy = ny + dy[1];
                    if(qx >= 0 && qy >= 0 && qx < N && qy < M && map[qx][qy].block != 0) {
                        if (!visited[qx][qy]) {
                            if (CheckMethod(map[qx][qy], 7, 1)) {
                                queue.offer(new Point(qx, qy, map[qx][qy].block, item.time + 1));
                                visited[qx][qy] = true;
                            }
                        }
                    }

                    qx = nx + dx[2];
                    qy = ny + dy[2];
                    if(qx >= 0 && qy >= 0 && qx < N && qy < M && map[qx][qy].block != 0) {
                        if (!visited[qx][qy]) { //방문하지않은 곳이라면
                            if(!CheckMethod(map[qx][qy], 7, 2)) continue;
                            queue.offer(new Point(qx, qy, map[qx][qy].block, item.time + 1));
                            visited[qx][qy] = true;
                        }
                    }
                    break;
            }
        }
    }
    //현재 위치에서 다음 살펴보는 블록이 갈 수 있는 블록인 확인하는 메서드
    private static boolean CheckMethod(Point idx, int block, int vector) {
        if(block == 1 && vector == 0) {
            if("1247".contains(Integer.toString(idx.block))) {
                return true;
            }
            return false;
        }
        if(block == 1 && vector == 1) {
            if("1256".contains(Integer.toString(idx.block))) {
                return true;
            }
            return false;
        }
        if(block == 1 && vector == 2) {
            if("1345".contains(Integer.toString(idx.block))) {
                return true;
            }
            return false;
        }
        if(block == 1 && vector == 3) {
            if("1367".contains(Integer.toString(idx.block))) {
                return true;
            }
            return false;
        }
        if(block == 2 && vector == 0) {
            if("1247".contains(Integer.toString(idx.block))) {
                return true;
            }
            return false;
        }
        if(block == 2 && vector == 1) {
            if("1256".contains(Integer.toString(idx.block))) {
                return true;
            }
            return false;
        }
        if(block == 3 && vector == 2) {
            if("1345".contains(Integer.toString(idx.block))) {
                return true;
            }
            return false;
        }
        if(block == 3 && vector == 3) {
            if("1367".contains(Integer.toString(idx.block))) {
                return true;
            }
            return false;
        }
        if(block == 4 && vector == 1) {
            if("1256".contains(Integer.toString(idx.block))) {
                return true;
            }
            return false;
        }
        if(block == 4 && vector == 3) {
            if("1367".contains(Integer.toString(idx.block))) {
                return true;
            }
            return false;
        }
        if(block == 5 && vector == 3) {
            if("1367".contains(Integer.toString(idx.block))) {
                return true;
            }
            return false;
        }
        if(block == 5 && vector == 0) {
            if("1247".contains(Integer.toString(idx.block))) {
                return true;
            }
            return false;
        }
        if(block == 6 && vector == 2) {
            if("1345".contains(Integer.toString(idx.block))) {
                return true;
            }
            return false;
        }
        if(block == 6 && vector == 0) {
            if("1247".contains(Integer.toString(idx.block))) {
                return true;
            }
            return false;
        }
        if(block == 7 && vector == 1) {
            if("1256".contains(Integer.toString(idx.block))) {
                return true;
            }
            return false;
        }
        if(block == 7 && vector == 2) {
            if("1345".contains(Integer.toString(idx.block))) {
                return true;
            }
            return false;
        }
        return false;
    }
}
