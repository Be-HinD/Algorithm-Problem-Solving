import java.io.*;
import java.util.*;

//BOJ_31863
class Main {
    static int N, M, sx, sy, building, broken;
    static char[][] map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        /**
         * 본진은 상하좌우 2칸씩, 여진은 1칸씩
         * 내진설계 : 2번
         * 접근법
         * 사방탐색과 아닌 경우를 구분해서 탐색
         * **/
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for(int i=0; i<N; i++) {
            String input = br.readLine();
            for(int j=0; j<M; j++) {
                char idx = input.charAt(j);
                if(idx == '*' || idx == '#') building++;
                else if(idx == '@') {
                    sx = i;
                    sy = j;
                }
                map[i][j] = idx;
            }
        }

        bfs();

        System.out.println(broken + " " + (building-broken));
    }
    static class Wave {
        int x;
        int y;
        int cnt;
        int dist;
        public Wave(int x, int y, int cnt, int dist) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.dist = dist;
        }
    }
    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};
    static void bfs() {
        Queue<Wave> q = new ArrayDeque<>();
        q.offer(new Wave(sx,sy,2, -1));
        boolean[][] v = new boolean[N][M];

        while(!q.isEmpty()) {
            Wave cur = q.poll();

            if(cur.dist == -1) { //사방탐색
                for (int i=0; i<4; i++) {
                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];
                    if (!isRange(nx, ny) || map[nx][ny] == '|')
                        continue;

                    if (map[nx][ny] == '#') {
                        if (v[nx][ny]) {
                            q.offer(new Wave(nx, ny, 1, -1));
                            broken++;
                            map[nx][ny] = '.';
                        }
                        v[nx][ny] = true;
                    } else if (map[nx][ny] == '*') {
                        q.offer(new Wave(nx, ny, 1,-1));
                        map[nx][ny] = '.';
                        broken++;
                    }
                    q.offer(new Wave(nx, ny, cur.cnt-1, i));
                }
            }
            else { //단방향

                if(cur.cnt == 0) continue;
                int nx = cur.x + dx[cur.dist];
                int ny = cur.y + dy[cur.dist];
                if (!isRange(nx, ny) || map[nx][ny] == '|') {
                    continue;
                }

                if (map[nx][ny] == '#') {
                    if (v[nx][ny]) {
                        q.offer(new Wave(nx, ny, 1, -1));
                        broken++;
                        map[nx][ny] = '.';
                    }
                    v[nx][ny] = true;
                } else if (map[nx][ny] == '*') {
                    q.offer(new Wave(nx, ny, 1,-1));
                    map[nx][ny] = '.';
                    broken++;
                }
                q.offer(new Wave(nx, ny, cur.cnt-1, cur.dist));
            }

        }
    }
    static boolean isRange(int x, int y) {
        if(x<0 || y<0 || x>=N || y>=M) return false;
        return true;
    }
}