import java.util.*;

class Solution {
    static class Robot { //정의 객체
        int x1, y1, x2, y2, cnt;
        public Robot(int x1, int y1, int x2, int y2, int cnt) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.cnt = cnt;
        }
    }

    static int N;
    public int solution(int[][] board) {
        N = board.length;
        
        return bfs(board);
    }

    static int[] dx = {0, 0, 1, -1}; // 이동 방향 (상, 하, 좌, 우)
    static int[] dy = {1, -1, 0, 0};
    
    static int bfs(int[][] map) {
        Queue<Robot> q = new ArrayDeque<>();
        boolean[][][] v = new boolean[N][N][2];
        // (x1,y1) + (x2,y2) + (수평/수직) -> head의 위치에 따라 별도 체크가 안됨.

        q.offer(new Robot(0, 0, 0, 1, 0));
        v[0][0][0] = true;
        v[0][1][0] = true;

        while (!q.isEmpty()) {
            Robot cur = q.poll();
            
            // 목표 지점 도달
            if ((cur.x1 == N - 1 && cur.y1 == N - 1) || (cur.x2 == N - 1 && cur.y2 == N - 1)) {
                return cur.cnt;
            }

            // Step 1. 사방탐색
            int dist = cur.x1 == cur.x2 ? 0 : 1;
            for (int i = 0; i < 4; i++) {
                int nx1 = cur.x1 + dx[i];
                int ny1 = cur.y1 + dy[i];
                int nx2 = cur.x2 + dx[i];
                int ny2 = cur.y2 + dy[i];

                if (isValid(nx1, ny1, nx2, ny2, map) || (v[nx1][ny1][dist] && v[nx2][ny2][dist])) continue;
                v[nx1][ny1][dist] = true;
                v[nx2][ny2][dist] = true;
                q.offer(new Robot(nx1, ny1, nx2, ny2, cur.cnt + 1));
                
            }

            // Step 2. 회전
            if (cur.x1 == cur.x2) { // 수평
                for (int d = -1; d <= 1; d += 2) { //-1, 1
                    if (isValid(cur.x1 + d, cur.y1, cur.x2 + d, cur.y2, map)) continue;
                    if(!v[cur.x1][cur.y1][1] || !v[cur.x1+d][cur.y1][1]) {    //둘 중 하나를 방문하지 않았다면 처음 가는 곳
                        v[cur.x1][cur.y1][1] = true;
                        v[cur.x1+d][cur.y1][1] = true;
                        q.offer(new Robot(cur.x1, cur.y1, cur.x1 + d, cur.y1, cur.cnt + 1));
                    }
                    
                    if(!v[cur.x2][cur.y2][1] || !v[cur.x2+d][cur.y2][1]) {
                        v[cur.x2][cur.y2][1] = true;
                        v[cur.x2+d][cur.y2][1] = true;
                        q.offer(new Robot(cur.x2, cur.y2, cur.x2 + d, cur.y2, cur.cnt + 1));
                    }
                    
                }
            } else { // 수직
                for (int d = -1; d <= 1; d += 2) {
                    if (isValid(cur.x1, cur.y1 + d, cur.x2, cur.y2 + d, map)) continue;
                    
                    if(!v[cur.x1][cur.y1][0] || !v[cur.x1][cur.y1+d][0]) {
                        v[cur.x1][cur.y1][0] = true;
                        v[cur.x1][cur.y1+d][0] = true;
                        q.offer(new Robot(cur.x1, cur.y1, cur.x1, cur.y1 + d, cur.cnt + 1));
                    }
                    
                    if(!v[cur.x2][cur.y2][0] || !v[cur.x2][cur.y2+d][0]) {
                        v[cur.x2][cur.y2][0] = true;
                        v[cur.x2][cur.y2+d][0] = true;
                        q.offer(new Robot(cur.x2, cur.y2, cur.x2, cur.y2 + d, cur.cnt + 1));
                    }
                    
                }
            }
        }

        return -1;
    }

    static boolean isValid(int x1, int y1, int x2, int y2, int[][] map) {
        return x1<0 || y1<0 || x2<0 || y2<0 || x1>=N || y1>=N || x2>=N || y2>=N || map[x1][y1] == 1 || map[x2][y2] == 1;
    }
}
