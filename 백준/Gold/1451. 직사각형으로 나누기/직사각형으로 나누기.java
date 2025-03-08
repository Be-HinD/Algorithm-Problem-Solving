import java.io.*;
import java.util.*;

//BOJ_1451
public class Main {
    static int n, m;
    static long res;
    static int[][] map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        /**
         * TODO: 3개의 작은 직사각형의 합의 차이가 최소가 되어야 최대곱
         * **/

        map = new int[n+1][m+1];
        for(int i=1; i<=n; i++) {
            String input = br.readLine();
            for(int j=1; j<=m; j++) {
                map[i][j] = (input.charAt(j-1) - '0') + map[i-1][j] + map[i][j-1] - map[i-1][j-1];
            }
        }

        //
        calcCol();
        calcRaw();
        calcColBar();
        calcRawBar();

        System.out.println(res);

    }

    static int sum(int a, int b, int c, int d) {
        return map[c][d] - map[a-1][d] - map[c][b-1] + map[a-1][b-1];
    }

    static void calcRaw() {
        if(m < 3) return;
        for(int i=1; i<=m; i++) {
            for(int j=i+1; j<=m; j++) {
                long first = sum(1, 1, n, i);
                long second = sum(1,i+1,n,j);
                long third = sum(1, j+1, n, m);
                res = Math.max(first * second * third, res);
            }
        }
    }

    static void calcCol() {
        if(n < 3) return;
        for(int i=1; i<=n; i++) {
            for(int j=i+1; j<=n; j++) {
                long first = sum(1, 1, i, m);
                long second = sum(i+1,1, j, m);
                long third = sum(j+1, 1, n, m);
                res = Math.max(first * second * third, res);
            }
        }
    }

    static void calcColBar() {
        // 세로 막대 1, 가로 막대 2 ㅏ ㅓ 모양
        if(n<2 || m<2) return;

        for(int i=1; i<=m; i++) {   //[1,1] ~ [1,i]
            for(int j=1; j<=n; j++) {
                //ㅏ 모양
                long colBar = sum(1,1,n,i);
                long first = sum(1,i+1, j,m);
                long second = sum(j+1,i+1, n,m);
                res = Math.max(first * second * colBar, res);
                //ㅓ 모양
                colBar = sum(1, i+1, n, m);
                first = sum(1,1, j, i);
                second = sum(j+1, 1, n, i);
                res = Math.max(first * second * colBar, res);
            }
        }
    }

    static void calcRawBar() {
        // 세로 막대 2, 가로 막대 1 ㅜ ㅗ 모양
        if(n<2 || m<2) return;

        for(int i=1; i<=n; i++) {
            for(int j=1; j<=m; j++) {
                //ㅜ 모양
                long RawBar = sum(1,1, i, m);
                long first = sum(i+1,1, n, j);
                long second = sum(i+1, j+1, n,m);
                res = Math.max(first * second * RawBar, res);

                //ㅗ 모양
                RawBar = sum(i+1,1, n,m);
                first = sum(1,1,i,j);
                second = sum(1,j+1,i,m);
                res = Math.max(first * second * RawBar, res);
            }
        }
    }
}