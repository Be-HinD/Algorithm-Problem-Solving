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
    static int N, M, res, maxWeight;
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

        Prim();

        //두 개의 마을로 분리하는게 목적이기 때문에
        //MST 값에서
        //가장 큰 가중치를 가진 도로 하나를 제거한 값을 출력
        System.out.println(res - maxWeight);

    }

    private static void Prim() {
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.w - o2.w;
            }
        });

        pq.offer(new Node(0, 0));
        boolean[] v = new boolean[N]; //프림은 방문배열 하나로 관리

        maxWeight = 0; //MST 중 가장 큰 가중치의 값

        while(!pq.isEmpty()) {
            Node cur = pq.poll();

            if(v[cur.v]) continue;
            v[cur.v] = true;
            res += cur.w;

            maxWeight = Math.max(maxWeight, cur.w); //MST 중 가장 큰 가중치의 값 갱신

            for(Node n : list.get(cur.v)) {
                //방문하지 않았다면 큐에 추가
                if(!v[n.v]) {
                    pq.offer(n);
                }
            }
        }
    }
}