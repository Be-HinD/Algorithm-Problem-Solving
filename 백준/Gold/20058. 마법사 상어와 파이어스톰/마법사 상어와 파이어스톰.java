import java.io.*;
import java.util.*;

//BOJ_20058
public class Main {
    static int n, q;
    static int[][] map;
    static boolean[][] v;
    static int[] dx = new int[]{0,0,1,-1};
    static int[] dy = new int[]{1,-1,0,0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        /**
         * 조건
         * L을 기준으로 격자 형성 -> 90도 회전 (핵심 로직)
         * 각 칸을 순회하면서 인접한 칸중 얼음칸이 2개 이하라면 -1
         * 2번 덩어리의 경우 각 격자 위치에서 BFS 사방탐색
         * **/

        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        n = (int) Math.pow(2, n);

        map = new int[n][n];

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<q; i++) {
            //순차적 실행
            int l = Integer.parseInt(st.nextToken());
            logic(l);
        }

        int res = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                res += map[i][j];
            }
        }

        v = new boolean[n][n];
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(v[i][j] || map[i][j] < 1) continue;
                v[i][j] = true;
                bfs(i,j);
            }
        }

        System.out.println(res);
        System.out.println(maxIce);

    }

    static void logic(int L) {
        // 2^L 격자 분리 + 90도 회전(달팽이)
        //TODO : 격자 좌측 상단 지점 + 우측 하단 지점 구하기
        int roop = (int) Math.pow(2, L-1);
        L = (int) Math.pow(2, L);   // 2, 4, 8 ...
        for(int i=0; i<n; i+=L) {
            for(int j=0; j<n; j+=L) {
                lotate(i,j, i+L, j+L, roop, L);
            }
        }

        // TODO : 인접 얼음칸 계산 및 양 감소 (주의 : 동시에 감소를 해야함. 감소된 값 활용되면 오답)
        List<int[]> list = new ArrayList<>();
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                int cnt = 0;
                if(map[i][j] == 0) continue;
                for(int d=0; d<4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];
                    if(nx<0 || ny<0 || nx>=n || ny>=n) continue;
                    if(map[nx][ny] > 0) cnt++;
                }
                if(cnt < 3)  list.add(new int[]{i,j});
            }
        }

        for(int[] o : list) {
            map[o[0]][o[1]]--;
        }

    }
    static void lotate(int x, int y, int r, int c, int roop, int L) {
        //x,y -> 시작 r,c -> 끝, roop = 돌려야하는 테두리 수, L = 2, 4, 8, (열 개수)
//        System.out.println(x + ", " + y + " -> " + r + ", " + c);
        r--; c--;
        for (int p=0; p<roop; p++) { //roop = 1,2,4,8 ...
            int nx = x + (p * 1);
            int ny = y + (p * 1);
            int nr = r - (p * 1);
            int nc = c - (p * 1);
            if(p != 0) L -= 2;

            int[] copy = new int[L];
            int nnx = nx; int nny = ny;
            for(int i=0; i<L; i++) {
                copy[i] = map[nnx][nny++];  //상단 첫줄 복사
            }

            //상단 변경
            nnx = nx;
            nny = ny;
            for(int i=nc; i>=ny; i--) {
                map[nx][i] = map[nnx++][nny];
            }

            //좌측 변경
            nnx = nr;
            nny = ny;
            for(int i=nx; i<=nr; i++) {
                map[i][ny] = map[nnx][nny++];
            }

            //하단 변경
            nnx = nr;
            for(int i=ny; i<=nc; i++) {
                map[nr][i] = map[nnx--][nc];
            }

            //우측 변경 (기존 복사해놓았던 상단 값 재활용)
            int idx = L-1;
            for(int i=nr; i>=nx; i--) {
                map[i][nc] = copy[idx--];
            }
        }
    }

    static int maxIce;
    static void bfs(int x, int y) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x, y});

        int cnt = 0;
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            cnt++;

            for(int d=0; d<4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];
                if(nx<0 || ny<0 || nx>=n || ny>=n || v[nx][ny]) continue;
                if(map[nx][ny] > 0) {
                    q.offer(new int[]{nx,ny});
                    v[nx][ny] = true;
                }
            }
        }

        maxIce = Math.max(maxIce, cnt);

    }
}