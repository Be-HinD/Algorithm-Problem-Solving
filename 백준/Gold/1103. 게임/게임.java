import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

//BOJ_1103 게임
public class Main {
    static int N, M, res;
    static char[][] arr;
    static int[][] dp;
    static boolean[][] v;
    static boolean flag;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new char[N][M];
        for(int i=0; i<N; i++) {
            String input = br.readLine();
            for(int j=0; j<M; j++) {
                arr[i][j] = input.charAt(j);
            }
        }

        dp = new int[N][M];
        v = new boolean[N][M];
        dfs(0,0,0);

//        for(int i=0; i<N; i++) {
//            System.out.println(Arrays.toString(dp[i]));
//        }


        if(flag) System.out.println(-1);
        else System.out.println(dp[0][0]);
        br.close();
    }

    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};
    private static int dfs(int x, int y, int cnt) {

        //기저조건
        int step = Integer.parseInt(String.valueOf(arr[x][y]));

        //동작부
        for(int i=0; i<4; i++) {
            int nx = x + (dx[i] * step);
            int ny = y + (dy[i] * step);
            if(nx<0 || ny<0 || nx>=N || ny>=M || arr[nx][ny] == 'H') continue;

            if(v[nx][ny]) {
                //Cycle 형성
                flag = true;
                return -1;
            }
            v[nx][ny] = true;
            if(dp[nx][ny] != 0) {
                dp[x][y] = Math.max(dp[x][y], dp[nx][ny] + 1);
            }
            else {
                dp[x][y] = Math.max(dp[x][y], dfs(nx, ny, cnt+1)+1);
            }
            v[nx][ny] = false;
        }

        if(dp[x][y] == 0) {
            dp[x][y] = 1;
            return 1;
        }
        return dp[x][y];
    }
}