import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ_21940 가운데에서 만나기
public class Main {
    static class Node {
        int v;
        int w;

        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
    static int N, M, K;
    static List<List<Node>> list;
    static int[] arr;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();

        N = Integer.parseInt(st.nextToken()); //도시의 개수
        M = Integer.parseInt(st.nextToken()); //도로의 개수

        list = new ArrayList<>();
        for(int i=0; i<=N; i++) list.add(new ArrayList<>());
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            list.get(start).add(new Node(end, weight));
        }

        K = Integer.parseInt(br.readLine()); //총 인원
        st = new StringTokenizer(br.readLine());
        arr = new int[K];
        for(int i=0; i<K; i++) {
            arr[i] = Integer.parseInt(st.nextToken()); //출발 정점번호
        }

        dp = new int[N+1][N+1];
        for(int i=0; i<=N; i++) Arrays.fill(dp[i], Integer.MAX_VALUE);
        //모든 정점으로부터 각 정점까지의 최단경로 구함
        for(int i=1; i<=N; i++) {
            dijkstra(i);
        }

        PriorityQueue<Integer> res = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        //각 정점을 모임장소로 잡고 친구별로 해당 지점까지의 왕복시간을 구한 후
        int distance = Integer.MAX_VALUE;
        for(int i=1; i<=N; i++) {
            int place = i;
            int max = 0;
            for(int friend : arr) {
                int sum = dp[friend][place] + dp[place][friend]; //왕복시간
                max = Math.max(max, sum); //최대 왕복시간
            }

            if(max < distance) { //최대 왕복시간이 현재 최소시간보다 낮을 경우 정답 갱신
                res.clear();
                res.offer(i);
                distance = max;
            }
            else if(max == distance) {
                res.offer(i);
            }
        }

        //인접리스트로 담고
        //dp배열은 2차원으로 해서
        //각 정점으로부터 다른정점들까지의 거리를 구하는데 최대값만 담고
        //마지막에 i->j 로가는거 j->i 로가는거 더해서 최소값 찾고, 값 같은 애들 동시에 다 출력
        while(!res.isEmpty()) {
            System.out.print(res.poll() + " ");
        }
    }

    private static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.w - o2.w;
            }
        });
        pq.offer(new Node(start, 0));
        dp[start][start] = 0;

        while(!pq.isEmpty()) {
            Node cur = pq.poll();

            if(dp[start][cur.v] < cur.w) continue;

            for(Node n : list.get(cur.v)) {
                int dist = cur.w + n.w;
                if(dp[start][n.v] > dist) {
                    dp[start][n.v] = dist;
                    pq.offer(new Node(n.v, dist));
                }
            }
        }
    }
}