import java.io.*;
import java.util.*;

//BOJ_17951
public class Main {
    static int[][] damages = new int[][]{{9,3,1}, {9,1,3}, {3,1,9}, {3,9,1}, {1,3,9}, {1,9,3}};
    static int[] lisk = new int[3];
    static int[][][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) lisk[i] = Integer.parseInt(st.nextToken());

        dp = new int[61][61][61];
        dp[lisk[0]][lisk[1]][lisk[2]] = 1;

        System.out.println(attackScv());

    }

    static int attackScv() {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{lisk[0],lisk[1],lisk[2],0});

        int res = 0;
        while(!q.isEmpty()) {
            int[] cur = q.poll();

            if(cur[0] == 0 && cur[1] == 0 && cur[2] == 0) {
                return cur[3];
            }

            for(int[] damage : damages) {
                int a = Math.max(0, cur[0] - damage[0]);
                int b = Math.max(0, cur[1] - damage[1]);
                int c = Math.max(0, cur[2] - damage[2]);

                if(dp[a][b][c] != 0) continue;
                dp[a][b][c] = cur[3] + 1;
                q.offer(new int[]{a,b,c,cur[3]+1});
            }
        }

        return -1;
    }
}