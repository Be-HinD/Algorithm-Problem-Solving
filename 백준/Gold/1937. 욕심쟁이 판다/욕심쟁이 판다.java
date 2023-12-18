import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ_1937 욕심쟁이 판다
public class Main {
    static int N, res;
    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};
    static int[][] map, dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dp = new int[N][N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                dp[i][j] = dfs(i,j);
                res = Math.max(res, dp[i][j]);
            }
        }

//        for(int i=0; i<N; i++) System.out.println(Arrays.toString(dp[i]));

        System.out.println(res);
    }

//    private static int bfs(int r, int c) {
//        //기저조건
//        Queue<int[]> q = new ArrayDeque<>();
//        q.offer(new int[]{r,c,1});
//        int max = 1;
//
//        while(!q.isEmpty()) {
//            int[] cur = q.poll();
//
//            max = Math.max(max, cur[2]);
//
//            for(int i=0; i<4; i++) {
//                int nx = cur[0] + dx[i];
//                int ny = cur[1] + dy[i];
//                if(nx<0 || ny<0 || nx>=N || ny>=N) continue;
//
//                if(map[cur[0]][cur[1]] < map[nx][ny]) {
//                    if(dp[nx][ny] != Integer.MAX_VALUE) {
//                        max = Math.max(max, cur[2] + dp[nx][ny]);
//                    }
//                    else q.offer(new int[]{nx,ny,cur[2]+1});
//                }
//            }
//        }
//        return max;
//    }

    private static int dfs(int r, int c) {
        //기저
        //동작
        for(int i=0; i<4; i++) {
            int nx = r + dx[i];
            int ny = c + dy[i];
            if(nx<0 || ny<0 || nx>=N || ny>=N) continue;

            if(map[r][c] < map[nx][ny]) {
                if(dp[nx][ny] == 0) {
                    dp[r][c] = Math.max(dp[r][c], dfs(nx, ny)+1);
                }
                else {
                    dp[r][c] = Math.max(dp[r][c], dp[nx][ny]+1);
                }
            }
        }
        if(dp[r][c] == 0) return 1;
        return dp[r][c];
    }
}