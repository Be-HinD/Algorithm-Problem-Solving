import java.io.*;
import java.util.*;

//BOJ_21924
public class Main {
    static class Node {
        int v;
        int w;
        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
    static int n, m, t, res;
    static List<List<Node>> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        /**
         * prim으로 현재 정점과 연결된 최소 간선들을 연결
         * 연결 시마다 연결 비용을 누적하여 추가 합산
         * **/


        n = Integer.parseInt(st.nextToken());   // 정점
        m = Integer.parseInt(st.nextToken());   // 간선
        t = Integer.parseInt(st.nextToken());   // 증가 비용

        list = new ArrayList<>();
        for(int i=0; i<=n; i++) list.add(new ArrayList<>());

        while(m-->0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list.get(x).add(new Node(y,w));
            list.get(y).add(new Node(x,w));
        }

        prim();

        System.out.println(res + t);

    }

    static void prim() {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.w - o2.w);
        pq.offer(new Node(1, 0));
        boolean[] v = new boolean[n+1];

        int cost = -t;
        while(!pq.isEmpty()) {
            Node cur = pq.poll();

            if(v[cur.v]) continue;
            res += cur.w + cost;
            cost += t;
            v[cur.v] = true;

            for(Node next : list.get(cur.v)) {
                if(v[next.v]) continue;
                pq.offer(next);
            }
        }

    }
}