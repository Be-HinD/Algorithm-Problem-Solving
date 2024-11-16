import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ_2234
public class Main {
    static int N, M, roomRes, res;
    static int[][] map, sec;
    static Map<Integer, Integer> cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        /**
         * 키워드
         *  이 성에 있는 방의 개수
         *  가장 넓은 방의 넓이
         *  하나의 벽을 제거하여 얻을 수 있는 가장 넓은 방의 크기
         *  접근법
         *  N,M < 50 -> O(N^2)까지 가능
         *  bfs 영역나누기 -> 사방탐색 대신 비트연산
         *  영역별 벽뿌 최대값 갱신
         * **/

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        sec = new int[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        cnt = new HashMap<>();
        int idx = 1;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(sec[i][j] == 0) {
                    sec[i][j] = idx;
                    bfs(i,j,idx);
                    idx++;
                }
            }
        }

        //인접 영역 체크
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                for(int dir=0; dir<4; dir++) {
                    int nx = i + dx[dir];
                    int ny = j + dy[dir];
                    if(nx<0 || ny<0 || nx>=N || ny>=M) continue;
                    if(sec[i][j] != sec[nx][ny]) {
                        res = Math.max(res, cnt.get(sec[i][j]) + cnt.get(sec[nx][ny]));
                    }
                }
            }
        }

        System.out.println(idx-1);
        System.out.println(roomRes);
        System.out.println(res);

    }

    static int[] dx = new int[]{0,-1,0,1};
    static int[] dy = new int[]{-1,0,1,0};
    static int[] wall = new int[]{1,2,4,8};
    private static void bfs(int x, int y, int idx) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x,y});
        boolean[][] v = new boolean[N][M];
        v[x][y] = true;

        int res = 0;
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            res++;
            sec[cur[0]][cur[1]] = idx;
            int info = map[cur[0]][cur[1]];

            //현재 위치에서 비트연산 사방탐색
            for(int i=0; i<4; i++) {
                if((info & wall[i]) != 0) continue;
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if(nx<0 || ny<0 || nx>=N || ny>=M || v[nx][ny]) continue;
                q.offer(new int[]{nx,ny});
                v[nx][ny] = true;
            }
        }
        roomRes = Math.max(roomRes, res);
        cnt.put(idx, res);
    }
}