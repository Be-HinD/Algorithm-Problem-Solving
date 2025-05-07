import java.io.*;
import java.util.*;

//BOJ_5913
public class Main {
    static int k, res;
    static boolean[][] v;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        /**
         * 문제 난독 존나 옴
         * 낮아지는 지점에 대해서 카운팅
         * **/

        k = Integer.parseInt(br.readLine());

        v = new boolean[5][5];

        for(int i=0; i<k; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            v[x-1][y-1] = true;
        }
        v[0][0] = true;
        v[4][4] = true;
        dfs(25-k-2, 0, 0, 4, 4);

        System.out.println(res);
    }

    static int[] dx = new int[]{0,0,1,-1};
    static int[] dy = new int[]{1,-1,0,0};
    static void dfs(int apple, int jx, int jy, int hx, int hy) {
        //기저조건 : 사과를 모두 수확 + 같은 위치라면 counting
        if(jx == hx && jy == hy) {
            if(apple != 0) return;
            res++;
            return;
        }

        //준규부터 이동
        for(int i=0; i<4; i++) {
            int nx = jx + dx[i];
            int ny = jy + dy[i];
            if(isRangeOut(nx, ny) || v[nx][ny]) continue;

            for(int j=0; j<4; j++) {
                int nnx = hx + dx[j];
                int nny = hy + dy[j];
                if(isRangeOut(nnx, nny)|| v[nnx][nny]) continue;
                //서로 사과나무로 이동할 수 있는 경우의 수
                v[nx][ny] = true;
                v[nnx][nny] = true;
                dfs((nx == nnx && ny == nny) ? apple-1 : apple-2, nx, ny, nnx, nny);
                v[nnx][nny] = false;
            }
            v[nx][ny] = false;
        }
    }

    static boolean isRangeOut(int x, int y) {
        return x<0 || y<0 || x>4 || y>4;
    }
}