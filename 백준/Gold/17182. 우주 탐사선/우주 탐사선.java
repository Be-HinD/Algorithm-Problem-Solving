import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ_17182
public class Main {
    static int N, K, res;
    static int[][] map;
    static int[][] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        res = Integer.MAX_VALUE;

        dist = new int[N][N];
        for(int i=0; i<N; i++) Arrays.fill(dist[i], Integer.MAX_VALUE);

        for(int i=0; i<N; i++) Dijk(i);

        v = new boolean[N];
        v[K] = true;
        dfs(K, 0, 1);

        System.out.println(res);

    }
    static boolean[] v;
    static void dfs(int cur, int cost, int cnt) {
        if(cnt == N) {
            res = Math.min(res, cost);
            return;
        }

        for(int i=0; i<N; i++) {
            if(v[i]) continue;
            //방문하지 않은곳에 대해 최단 거리로 갈 수 있는 정점으로 탐색
            v[i] = true;
            dfs(i, cost + dist[cur][i], cnt+1);
            v[i] = false;
        }
    }

    static void Dijk(int start) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(((o1, o2) -> o1[1] - o2[1]));
        pq.offer(new int[]{start, 0});

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();

            if(dist[start][cur[0]] <= cur[1]) continue;
            dist[start][cur[0]] = cur[1];

            for(int i=0; i<N; i++) {
                if(cur[0] == i) continue;
                pq.offer(new int[]{i, cur[1] + map[cur[0]][i]});
            }
        }
    }
}