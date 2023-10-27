import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

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
    static boolean[] v;
    static ArrayList<ArrayList<Node>> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        list = new ArrayList<>();
        for(int i=0; i<N; i++) list.add(new ArrayList<>());

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());
            list.get(start).add(new Node(end, weight));
            list.get(end).add(new Node(start, weight));
        }

        v = new boolean[N];
        Prim();
        System.out.println(res);
    }

    private static void Prim() {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        pq.offer(new int[]{0, 0});

        while(!pq.isEmpty()) {
            int[] idx = pq.poll();
            int curV = idx[0];
            int curT = idx[1];

            if(v[curV]) continue;
            res += curT;
            v[curV] = true;

            for(Node n : list.get(curV)) {
                if(v[n.v]) continue;
                pq.offer(new int[]{n.v, n.w});
            }
        }
    }
}