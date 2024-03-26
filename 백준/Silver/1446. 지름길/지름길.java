import java.io.*;
import java.util.*;

//BOJ_1446 지름길
public class Main {
    static class Node {
        int v;
        int w;
        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
    static int N, M;
    static List<List<Node>> list;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   //지름길의 개수
        M = Integer.parseInt(st.nextToken());   //고속도로 길이


        list = new ArrayList<>();
        for(int i=0; i<=M; i++) list.add(new ArrayList<>());

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            if(start >= M) continue; 
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            list.get(start).add(new Node(end, weight));
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

        int[] dist = new int[M+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        pq.offer(new Node(0, 0));
        dist[0] = 0;

        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            if(cur.v == M) {
                break;
            }

            if(dist[cur.v+1] > cur.w+1) {
                pq.offer(new Node(cur.v+1, cur.w+1));
                dist[cur.v+1] = cur.w+1;
            }

            if(!list.get(cur.v).isEmpty()) {
                for(Node n : list.get(cur.v)) {
                    //현재 위치에서 지름길이 있다면
                    int rate = cur.w + n.w;
                    if(n.v > M) continue;
                    if(dist[n.v] > rate) {
                        //갱신 시점
                        dist[n.v] = rate;
                        pq.offer(new Node(n.v, rate));
                    }
                }
            }
        }
        return dist[M];
    }
}