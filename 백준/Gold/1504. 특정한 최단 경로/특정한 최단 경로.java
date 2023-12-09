import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ_1504 특정한 최단 경로
public class Main {
    static class Node {
        int v;
        int w;

        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
    static int N, E, v1, v2;
    static ArrayList<ArrayList<Node>> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //정점
        E = Integer.parseInt(st.nextToken()); //간선

        list = new ArrayList<>();
        for(int i=0; i<=N; i++) list.add(new ArrayList<>());

        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list.get(x).add(new Node(y,w));
            list.get(y).add(new Node(x,w)); //무방향
        }

        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());

        //1번 정점 -> N번 정점까지 v1,v2를 방문하고 가는 최단 경로
        //방문체크 x
        //총 3번의 데이크스트라를 통해
        //1 -> v1 -> v2 -> N
        //1 -> v2 -> v1 -> N
        //두 가지 경로의 비용을 구한 후 최소값 도출.

        long distA = dijkstra(1,v1);
        distA += dijkstra(v1,v2);
        distA += dijkstra(v2,N);
        long distB = dijkstra(1,v2);
        distB += dijkstra(v2,v1);
        distB += dijkstra(v1,N);

        long res = Math.min(distA, distB);

        if(res >= 100000000) System.out.println(-1);
        else System.out.println(res);
    }

    private static int dijkstra(int start, int end) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        int[] dist = new int[list.size()]; //거리 저장 배열
        Arrays.fill(dist, Integer.MAX_VALUE);

        pq.offer(new int[]{start,0});

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();

            if(cur[0] == end) return cur[1];

            for(Node n : list.get(cur[0])) {
                if(dist[n.v] > cur[1]+n.w) {
                    pq.offer(new int[]{n.v, cur[1] + n.w});
                    dist[n.v] = cur[1]+n.w;
                }
            }
        }
        return 100000000;
    }
}