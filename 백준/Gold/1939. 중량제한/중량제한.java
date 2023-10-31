import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ_1647 도시 분할 계획
public class Main {
    static class Node {
        int v;
        int w;
        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
    static int N, M, A, B, res;
    static List<List<Node>> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //정점 개수
        M = Integer.parseInt(st.nextToken()); //간선 개수

        list = new ArrayList<>();
        for(int i=0; i<N; i++) list.add(new ArrayList<>());

        for(int i=0; i<M; i++) { //인접리스트 초기화
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());
            list.get(start).add(new Node(end, weight));
            list.get(end).add(new Node(start, weight));
        }

        st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken()) - 1;
        B = Integer.parseInt(st.nextToken()) - 1;

        res = Integer.MAX_VALUE;
        Prim(A, B);
        System.out.println(res);
    }

    private static void Prim(int start, int end) {
        //최대 가중치로 이동
        PriorityQueue<Node> q = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o2.w - o1.w;
            }
        });


        q.offer(new Node(start, Integer.MAX_VALUE)); //첫 비교를 위해 가중치 최대값으로
        boolean[] v = new boolean[N];

        while(!q.isEmpty()) {
            Node cur = q.poll();
            
            if(v[cur.v]) continue;
            
            if(cur.v == end) { //목적지 찾은 경우
                res = Math.min(res, cur.w);
                return;
            }
            
            v[cur.v] = true;
            res = Math.min(res, cur.w); //지금까지의 경로에서 최소값을 계속 갱신

            for(Node n : list.get(cur.v)) {
                if(!v[n.v]) {
                    q.offer(n);
                }
            }
        }
    }
}