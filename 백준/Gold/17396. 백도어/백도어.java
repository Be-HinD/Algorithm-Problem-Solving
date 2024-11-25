import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ_17396
public class Main {
    static class Node {
        int v;
        int weight;
        public Node(int v, int weight) {
            this.v = v;
            this.weight = weight;
        }
    }
    static class Move implements Comparable<Move> {
        int cur;
        long sum;
        public Move(int cur, long sum) {
            this.cur = cur;
            this.sum = sum;
        }
        @Override
        public int compareTo(Move o) {
            return Long.compare(this.sum, o.sum);
        }
    }
    static int N, M;
    static long res;
    static List<List<Node>> list;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) arr[i] = Integer.parseInt(st.nextToken());

        arr[N-1] = 0;
        // 트리형태로 분기점 간 간선이 주어짐
        // 사이클 없음.

        list = new ArrayList<>();
        for(int i=0; i<N; i++) list.add(new ArrayList<>());

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            if(arr[a] == 1 || arr[b] == 1) continue;
            list.get(a).add(new Node(b, t));
            list.get(b).add(new Node(a, t));
        }

        res = bfs();

        System.out.println(res);

    }

    private static long bfs() {
        PriorityQueue<Move> pq = new PriorityQueue<>();
        pq.offer(new Move(0,0));
        long[] dist = new long[N];
        Arrays.fill(dist, Long.MAX_VALUE);

        while(!pq.isEmpty()) {
            Move cur = pq.poll();

            if(cur.cur == N-1) return cur.sum;

            if(dist[cur.cur] <= cur.sum) continue;
            dist[cur.cur] = cur.sum;

            for(Node n : list.get(cur.cur)) {
                if(arr[n.v] == 1 && n.v != N-1) continue;
                pq.offer(new Move(n.v, cur.sum + n.weight));
            }
        }

        return -1;
    }
}