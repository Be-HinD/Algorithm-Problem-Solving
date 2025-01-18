import java.util.*;

class Solution {
    static class Robot implements Comparable<Robot> {
        int x1, y1, x2, y2, cnt;
        public Robot(int x1, int y1, int x2, int y2, int cnt) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.cnt = cnt;
        }
        @Override
        public int compareTo(Robot other) {
            return Integer.compare(this.cnt, other.cnt); // 최소 시간 기준 정렬
        }
    }

    static int N;
    static int[] dx = {0, 0, 1, -1}; // 이동 방향 (상, 하, 좌, 우)
    static int[] dy = {1, -1, 0, 0};

    public int solution(int[][] board) {
        N = board.length;
        return dijkstra(board);
    }

    static int dijkstra(int[][] map) {
        PriorityQueue<Robot> pq = new PriorityQueue<>();
        boolean[][][][] visited = new boolean[N][N][N][N];

        pq.offer(new Robot(0, 0, 0, 1, 0));
        visited[0][0][0][1] = true;

        while (!pq.isEmpty()) {
            Robot cur = pq.poll();

            // 목표 지점 도달
            if ((cur.x1 == N - 1 && cur.y1 == N - 1) || (cur.x2 == N - 1 && cur.y2 == N - 1)) {
                return cur.cnt;
            }

            // Step 1. 사방 탐색
            for (int i = 0; i < 4; i++) {
                int nx1 = cur.x1 + dx[i];
                int ny1 = cur.y1 + dy[i];
                int nx2 = cur.x2 + dx[i];
                int ny2 = cur.y2 + dy[i];

                if (isValid(nx1, ny1, nx2, ny2, map) || visited[nx1][ny1][nx2][ny2]) continue;

                visited[nx1][ny1][nx2][ny2] = true;
                pq.offer(new Robot(nx1, ny1, nx2, ny2, cur.cnt + 1));
            }

            // Step 2. 회전
            if (cur.x1 == cur.x2) { // 수평
                for (int d = -1; d <= 1; d += 2) {
                    if (isValid(cur.x1 + d, cur.y1, cur.x2 + d, cur.y2, map)) continue;

                    if (!visited[cur.x1][cur.y1][cur.x1 + d][cur.y1]) {
                        visited[cur.x1][cur.y1][cur.x1 + d][cur.y1] = true;
                        pq.offer(new Robot(cur.x1, cur.y1, cur.x1 + d, cur.y1, cur.cnt + 1));
                    }

                    if (!visited[cur.x2][cur.y2][cur.x2 + d][cur.y2]) {
                        visited[cur.x2][cur.y2][cur.x2 + d][cur.y2] = true;
                        pq.offer(new Robot(cur.x2, cur.y2, cur.x2 + d, cur.y2, cur.cnt + 1));
                    }
                }
            } else { // 수직
                for (int d = -1; d <= 1; d += 2) {
                    if (isValid(cur.x1, cur.y1 + d, cur.x2, cur.y2 + d, map)) continue;

                    if (!visited[cur.x1][cur.y1][cur.x1][cur.y1 + d]) {
                        visited[cur.x1][cur.y1][cur.x1][cur.y1 + d] = true;
                        pq.offer(new Robot(cur.x1, cur.y1, cur.x1, cur.y1 + d, cur.cnt + 1));
                    }

                    if (!visited[cur.x2][cur.y2][cur.x2][cur.y2 + d]) {
                        visited[cur.x2][cur.y2][cur.x2][cur.y2 + d] = true;
                        pq.offer(new Robot(cur.x2, cur.y2, cur.x2, cur.y2 + d, cur.cnt + 1));
                    }
                }
            }
        }

        return -1;
    }

    static boolean isValid(int x1, int y1, int x2, int y2, int[][] map) {
        return x1 < 0 || y1 < 0 || x2 < 0 || y2 < 0 || x1 >= N || y1 >= N || x2 >= N || y2 >= N || map[x1][y1] == 1 || map[x2][y2] == 1;
    }
}
