import java.io.*;
import java.util.*;

//BOJ_2211 네트워크 복구
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
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        for(int i=0; i<=N; i++) list.add(new ArrayList<>());

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            list.get(start).add(new Node(end, weight));
            list.get(end).add(new Node(start, weight));
        }


        lastPang(1);
        System.out.println(res);
        System.out.print(sb);
    }

    private static void lastPang(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.w - o2.w;
            }
        });
        pq.offer(new Node(start, 0));
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        int[] prev = new int[N+1];
        boolean[] vv = new boolean[N+1];
        vv[start] = true;
        int cnt = 1;

        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            if(!vv[cur.v] && prev[cur.v] != 0) {
                vv[cur.v] = true;
                sb.append(prev[cur.v] + " " + cur.v).append("\n");
                res++;
                cnt++;
                if(cnt == N+1) return;
            }

            for(Node n : list.get(cur.v)) {
                int totalWeight = n.w + cur.w;
                if(totalWeight < dist[n.v]) {
                    pq.offer(new Node(n.v, totalWeight));
                    dist[n.v] = totalWeight;
                    prev[n.v] = cur.v;
                }
            }
        }
    }
}