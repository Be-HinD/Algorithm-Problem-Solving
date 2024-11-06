import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ_22116
public class Main {
    static class Node implements Comparable<Node> {
        int x;
        int y;
        int currentHeight;
        int maxDiff;
        public Node(int x, int y, int currentHight, int cnt) {
            this.x = x;
            this.y = y;
            this.currentHeight = currentHight;
            this.maxDiff = cnt;
        }
        @Override
        public int compareTo(Node n) {
            return this.maxDiff - n.maxDiff;
        }
    }
    static int N;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        if (N == 1) {
            System.out.println(0);
            return;
        }

        map = new int[N][N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(Dijkstra());

    }

    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};
    private static int Dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0,0,map[0][0], 0));
        int[][] dist = new int[N][N];
        for(int i=0; i<N; i++) Arrays.fill(dist[i], Integer.MAX_VALUE);
        dist[0][0] = 0;

        while(!pq.isEmpty()) {
            Node cur = pq.poll();

            if(cur.x == N-1 && cur.y == N-1) {
                return cur.maxDiff;
            }
            if(dist[cur.x][cur.y] < cur.maxDiff) continue;  //추가적인 탐색 방지

            for(int i=0; i<4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if(nx<0 || ny<0 || nx>=N || ny>=N) continue;
                int diff = Math.max(cur.maxDiff, Math.abs(map[nx][ny] - cur.currentHeight));

                if(dist[nx][ny] > diff) {
                    dist[nx][ny] = diff;
                    pq.offer(new Node(nx,ny,map[nx][ny], diff));
                }
            }
        }

        return -1;
    }
}