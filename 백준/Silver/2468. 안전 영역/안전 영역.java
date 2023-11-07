import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ_2468 안전영역
public class Main {
    static int min = Integer.MAX_VALUE;
    static int N, max, res;
    static int[][] map;
    static boolean[][] v;
    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                int idx = Integer.parseInt(st.nextToken());
                min = Math.min(min, idx); //최소 높이 지역 탐색
                max = Math.max(max, idx); //최대 높이 지역 탐색
                map[i][j] = idx;
            }
        }


        for(int i=0; i<=max; i++) { //비가 내리는 양을 0부터 최대높이까지

//            //깊은복사도 할 필요 없음..
//            int[][] copy = new int[N][N];
//            for(int j=0; j<N; j++) {
//                copy[j] = map[j].clone();
//            }

//            //장마 지역 구별할 필요가 없음..
//            for(int k=0; k<N; k++) {
//                for(int l=0; l<N; l++) {
//                    if (copy[k][l] <= i) copy[k][l] = -1; //장마로 잠긴 지역을 -1로 갱신
//                }
//            }

            int cnt = 0;
            //메모리가 작기 때문에 copy맵을 생성하지 않고 전역 방문배열을 통해 영역 나누기
            v = new boolean[N][N];
            for(int k=0; k<N; k++) {
                for(int l=0; l<N; l++) {
                    if(map[k][l] > i && !v[k][l]) {
                        bfs(k,l,i);
                        cnt++;
                    }
                }
            }

            res = Math.max(res, cnt);
        }

        System.out.println(res);

    }

    private static void bfs(int x, int y, int boundery) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x,y});
        v[x][y] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();

            for(int i=0; i<4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if(nx<0 || ny<0 || nx>=N || ny>=N || v[nx][ny]) continue;
                if(map[nx][ny] > boundery) { //물에 잠기지 않는 영역에 대해
                    q.offer(new int[]{nx,ny});
                    v[nx][ny] = true;
                }
            }
        }
    }
}