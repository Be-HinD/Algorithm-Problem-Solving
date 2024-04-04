import java.io.*;
import java.util.*;

//BOJ_1719 택배
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
    static int[][] res;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        list = new ArrayList<>();

        N = Integer.parseInt(st.nextToken());   //집하장 개수
        M = Integer.parseInt(st.nextToken());   //간선 개수

        for(int i=0; i<=N; i++) list.add(new ArrayList<>());

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            list.get(start).add(new Node(end, weight));
            list.get(end).add(new Node(start, weight)); //양방향 그래프
        }

        res = new int[N][N];

        for(int i=1; i<=N; i++) {
            Dijkstra(i);
        }
        
        //결과 출력
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(i==j) {
                    sb.append("-").append(" ");
                }
                else {
                    sb.append(res[i][j]).append(" ");
                }
            }
            sb.append("\n");
        }


        System.out.println(sb);

    }

    private static void Dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.w - o2.w;
            }
        });
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        int[] result = new int[N+1];

        dist[start] = 0;

        pq.offer(new Node(start, 0));
        while(!pq.isEmpty()) {
            Node cur = pq.poll();



            for(Node next : list.get(cur.v)) {
                int totalDist = cur.w + next.w;
                if(dist[next.v] > totalDist) {
                    dist[next.v] = totalDist;
                    pq.offer(new Node(next.v, totalDist));
                    result[next.v] = cur.v;   //이전에 방문했던 지점을 기록
                }
            }
        }

        for(int i=0; i<N; i++) {
            res[i][start-1] = result[i+1];
        }
        
    }
}