import java.io.*;
import java.util.*;

//BOJ_14284
public class Main {
    static class Node {
        int v;
        int w;
        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
    static int N, M, start, end, res;
    static List<List<Node>> list;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        /**
         * 연결이 되는 시점에 간선 추가 멈춘다 -> x
         * 모든 간선 연결 후 s - t 사이의 가중치의 최소합
         * **/

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();

        for(int i=0; i<=N; i++) list.add(new ArrayList<>());

        while(M-->0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list.get(x).add(new Node(y,w));
            list.get(y).add(new Node(x,w)); //무방향
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        System.out.println(Dijkstra());
        
    }

    static int Dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1,o2) -> o1.w - o2.w);
        pq.offer(new Node(start, 0));
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        while(!pq.isEmpty()) {
            Node cur = pq.poll();

            if(dist[cur.v] < cur.w) continue;
            if(cur.v == end) return cur.w;

            for(Node next : list.get(cur.v)) {
                int distance = cur.w + next.w;
                if(dist[next.v] > distance) {
                    pq.offer(new Node(next.v, distance));
                    dist[next.v] = distance;
                }
            }
        }
        return -1;
    }
}