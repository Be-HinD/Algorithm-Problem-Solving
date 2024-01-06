import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ_5972 택배 배송
public class Main {
    static class Node {
        int v;
        int w;

        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
    static int N, M, res;
    static List<List<Node>> list;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        for(int i=0; i<=N; i++) list.add(new ArrayList<>());

        for(int i=1; i<=M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            list.get(start).add(new Node(end, weight));
            list.get(end).add(new Node(start, weight));
        }

        System.out.println(Dijkstra());

    }

    private static int Dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.w - o2.w;
            }
        });
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        pq.offer(new Node(1,0));

        while(!pq.isEmpty()) {
            Node cur = pq.poll();

            for(Node n : list.get(cur.v)) {
                int distance = cur.w + n.w;
                if(dist[n.v] > distance) {
                    pq.offer(new Node(n.v, distance));
                    dist[n.v] = distance;
                }
            }
        }
        return dist[N];
    }
}