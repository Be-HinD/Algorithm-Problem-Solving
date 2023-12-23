import java.io.*;
import java.util.*;

//BOJ_20160 야쿠르트 아줌마 야쿠르트 주세요
public class Main {
    static class Node {
        int v;
        long w;

        public Node(int v, long w) {
            this.v = v;
            this.w = w;
        }
    }
    static int V, E, start, res;
    static int[] arr, m;
    static long[] mishi, me;
    static List<List<Node>> list;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken()); //정점 개수
        E = Integer.parseInt(st.nextToken()); //간선 개수

        list = new ArrayList<>();
        for(int i=0; i<=V; i++) list.add(new ArrayList<>());

        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            list.get(start).add(new Node(end, weight));
            list.get(end).add(new Node(start, weight));
        }


        mishi = new long[V+1];
        me = new long[V+1];
        Arrays.fill(mishi, Integer.MAX_VALUE);
        Arrays.fill(me, Integer.MAX_VALUE);

        arr = new int[10];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<10; i++) arr[i] = Integer.parseInt(st.nextToken());

        start = Integer.parseInt(br.readLine());


        Node n = new Node(arr[0], 0);
        mishi[n.v] = 0;
        for(int i=1; i<10; i++) {
            int end = arr[i];
            long pointW = DijkstraY(n.v, end, n.w);
            if(pointW != -1) {
                n.v = end;
                n.w = pointW;
                mishi[end] = pointW;
            }
        }
        Dijkstra(start, me); //내 최단경로 구하기


        for(int i=1; i<=V; i++) {
            if(mishi[i] >= me[i] && mishi[i] != Integer.MAX_VALUE && me[i] != Integer.MAX_VALUE) {
                res = i;
                break;
            }
        }

        if(res != 0) System.out.println(res);
        else System.out.println(-1);
    }

    private static long DijkstraY(int start, int end, long weight) {
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return (int) (o1.w - o2.w);
            }
        });
        long[] dist = new long[V+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        pq.offer(new Node(start, weight));
        dist[start] = weight;

        while(!pq.isEmpty()) {
            Node cur = pq.poll();

            if(cur.v == end) return cur.w;

            if(dist[cur.v] < cur.w) continue;

            for(Node n : list.get(cur.v)) {
                long d = n.w + cur.w;
                if(dist[n.v] > d) {
                    pq.offer(new Node(n.v, d));
                    dist[n.v] = d;
                }
            }
        }
        return -1;
    }

    private static void Dijkstra(int start, long[] arr) {
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return (int) (o1.w - o2.w);
            }
        });
        pq.offer(new Node(start, 0));
        arr[start] = 0;

        while(!pq.isEmpty()) {
            Node cur = pq.poll();

            if(arr[cur.v] < cur.w) continue;

            for(Node n : list.get(cur.v)) {
                long dist = n.w + cur.w;
                if(arr[n.v] >= dist) {
                    pq.offer(new Node(n.v, dist));
                    arr[n.v] = dist;
                }
            }
        }
    }
}