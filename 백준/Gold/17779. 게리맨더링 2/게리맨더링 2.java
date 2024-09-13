import java.io.*;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

//BOJ_17779 게리멘더링 2
public class Main {
    static int N, res;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());    //맵 크기


        map = new int[N+1][N+1];
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        res = Integer.MAX_VALUE;

        for(int i=1; i<N; i++) {
            for(int j=1; j<N; j++) {
                logic(i,j);
            }
        }

//        divideSection(2,3,1,2);
        System.out.println(res);

    }

    private static void logic(int x, int y) {
        /**
         * 매개변수 : 기준점 (x,y)
         * d1, d2 ≥ 1
         * 1 ≤ x < x+d1+d2 ≤ N == x가 N이 될 수는 없음.
         * 1 ≤ y-d1 < y < y+d2 ≤ N ==
         * **/

        for(int i=1; i<N; i++) {
            for(int j=1; j<N; j++) {
                if(x + i + j > N) continue;
                if(x >= x+i+j) continue;
                if(y - i < 1) continue;
                if(y >= y+i) continue;
                if(y >= y+j) continue;
                if(y+j > N) continue;
                divideSection(x,y,i,j);
            }
        }
    }

    private static void divideSection(int x, int y, int d1, int d2) {
        /**
         * 다음 칸은 경계선이다.
         * (x, y), (x+1, y-1), ..., (x+d1, y-d1)
         * (x, y), (x+1, y+1), ..., (x+d2, y+d2)
         * (x+d1, y-d1), (x+d1+1, y-d1+1), ... (x+d1+d2, y-d1+d2)
         * (x+d2, y+d2), (x+d2+1, y+d2-1), ..., (x+d2+d1, y+d2-d1)
         * **/
        /**
         * 2,4,1,2
         * 4,6 -> 5, 5
         * **/
        int[][] mask = new int[N+1][N+1];

        int sec5 = 0;
        int rr = x-1;
        int cc = y+1;
        while(rr!=x+d1 && cc!=y-d1) {
            rr++;
            cc--;
            if(!isRange(rr,cc) || mask[rr][cc] != 0) continue;
            mask[rr][cc] = 5;
            sec5 += map[rr][cc];
        }

        rr = x-1;
        cc = y-1;
        while(rr!=x+d2 && cc!=y+d2) {
            rr++;
            cc++;
            if(!isRange(rr,cc) || mask[rr][cc] != 0) continue;
            mask[rr][cc] = 5;
            sec5 += map[rr][cc];
        }

        rr = x+d1-1;
        cc = y-d1-1;
        while(rr!=x+d1+d2 && cc!=y-d1+d2) {
            rr++;
            cc++;
            if(!isRange(rr,cc) || mask[rr][cc] != 0) continue;
            mask[rr][cc] = 5;
            sec5 += map[rr][cc];
        }

        rr = x+d2-1;
        cc = y+d2+1;
        while(rr!=x+d2+d1 && cc!=y+d2-d1) {
            rr++;
            cc--;
            if(!isRange(rr,cc) || mask[rr][cc] != 0) continue;
            mask[rr][cc] = 5;
            sec5 += map[rr][cc];
        }

//        for(int i=1; i<=N; i++) {
//            for(int j=1; j<=N; j++) {
//                System.out.print(mask[i][j] + " ");
//            }
//            System.out.println();
//        }
        bfs(mask);

        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
                if(mask[i][j] == 0) {
                    mask[i][j] = 5;
                    sec5 += map[i][j];
                }
            }
        }

        // 경계선과 경계선의 안에 포함되어있는 곳은 5번 선거구이다.


        /**
         * 5번 선거구에 포함되지 않은 구역 (r, c)의 선거구 번호는 다음 기준을 따른다.
         * 1번 선거구: 1 ≤ r < x+d1, 1 ≤ c ≤ y
         * 2번 선거구: 1 ≤ r ≤ x+d2, y < c ≤ N
         * 3번 선거구: x+d1 ≤ r ≤ N, 1 ≤ c < y-d1+d2
         * 4번 선거구: x+d2 < r ≤ N, y-d1+d2 ≤ c ≤ N
         * **/

        int sec1 = 0, sec2 = 0, sec3 = 0, sec4 = 0;

        for(int r=1; r<x+d1; r++) {
            for(int c=1; c<=y; c++) {
                if(!isRange(r,c) || mask[r][c] != -1) continue;
                sec1 += map[r][c];
                mask[r][c] = 1;
            }
        }

        for(int r=1; r<=x+d2; r++) {
            for(int c=y+1; c<=N; c++) {
                if(!isRange(r,c) || mask[r][c] != -1) continue;
                sec2 += map[r][c];
                mask[r][c] = 2;
            }
        }

        for(int r=x+d1; r<=N; r++) {
            for(int c=1; c<y-d1+d2; c++) {
                if(!isRange(r,c) || mask[r][c] != -1) continue;
                sec3 += map[r][c];
                mask[r][c] = 3;
            }
        }

        for(int r=x+d2+1; r<=N; r++) {
            for(int c=y-d1+d2; c<=N; c++) {
                if(!isRange(r,c) || mask[r][c] != -1) continue;
                sec4 += map[r][c];
                mask[r][c] = 4;
            }
        }
//
//        System.out.println("sec1 -> " + sec1);
//        System.out.println("sec2 -> " + sec2);
//        System.out.println("sec3 -> " + sec3);
//        System.out.println("sec4 -> " + sec4);
//        System.out.println("sec5 -> " + sec5);

        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        pq.offer(sec1);
        pq.offer(sec2);
        pq.offer(sec3);
        pq.offer(sec4);
        pq.offer(sec5);

        int max = pq.poll();
        int min = 0;
        while(!pq.isEmpty()) {
            min = pq.poll();
        }

        res = Math.min(res, max - min);

//        if (max - min == 15) {
//            System.out.println(x + " : " + y + " : " + d1 + " : " + d2);
//        }
//        for(int i=1; i<=N; i++) {
//            for(int j=1 ;j<=N; j++) {
//                System.out.print(mask[i][j] + " ");
//            }
//            System.out.println();
//        }


    }

    private static boolean isRange(int x, int y) {
        if(x<1 || y<1 || x>N || y>N) return false;
        return true;
    }

    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};
    private static void bfs(int[][] vv) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0,0});
        boolean[][] v = new boolean[N+1][N+1];
        v[0][0] = true;
        vv[0][0] = -1;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            for(int i=0; i<4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if(nx<0 || ny<0 || nx>N || ny>N || v[nx][ny]) continue;
                if(vv[nx][ny] == 5) continue;
                q.offer(new int[]{nx,ny});
                vv[nx][ny] = -1;
                v[nx][ny] = true;
            }
        }
    }
}