import java.io.*;
import java.util.*;

//BOJ_1325
public class Main {
    static int N;
    static int[] arr, dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        arr = new int[N];
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        bfs();

        System.out.println(dp[N-1] == Integer.MAX_VALUE ? -1 : dp[N-1]);

    }

    static void bfs() {
        dp = new int[N];
        Arrays.fill(dp, Integer.MAX_VALUE);
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0,0});
        dp[0] = 0;

        while(!q.isEmpty()) {
            int[] cur = q.poll();

            for(int i=1; i<=arr[cur[0]]; i++) {
                if(cur[0] + i >= N) continue;
                if(dp[cur[0]+i] > cur[1]+1) {   //낮은 경우에만 탐색 진행
                    q.offer(new int[]{cur[0]+i, cur[1]+1});
                    dp[cur[0]+i] = cur[1] + 1;
                }
            }
        }
    }
}