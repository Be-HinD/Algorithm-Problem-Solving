import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ_17835 면접보는 승범이네
public class Main {
    static class Node {
        int v;
        long w;

        public Node(int v, long w) {
            this.v = v;
            this.w = w;
        }
    }
    static int N, M, K, resV;
    static long resTime;
    static long INF = 100_000_000_000L;
    static long[] dp;
    static PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
        @Override
        public int compare(Node o1, Node o2) {
            return (int) (o1.w - o2.w);
        }
    });
    static List<List<Node>> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //도시의 수 최대 100,000
        M = Integer.parseInt(st.nextToken()); //도로의 수 최대 500,000
        K = Integer.parseInt(st.nextToken()); //면접장 수 최대 N(100,000)


        list = new ArrayList<>();
        for(int i=0; i<=N; i++) list.add(new ArrayList<>());

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int end = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            list.get(start).add(new Node(end,weight)); //단방향
        }

        dp = new long[N+1];
        Arrays.fill(dp, INF);

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<K; i++) {
            int idx = Integer.parseInt(st.nextToken());
            pq.offer(new Node(idx, 0));
            dp[idx]= 0;
        }

        //출력은 정점번호(여러 곳이라면 제일 적은 번호), 면접장까지의 거리

        //첫번째 접근 : 그리디하게 각 면접장마다 i번째 면접장소를 end로 잡고 각 정점으로부터 i번째 면접장까지의 최단거리를 구함
        //두번째 접근 : 최적화 ->

        //문제 요약 -> 면접장이 아닌 장소로부터 면접장소까지의 최단경로 구하는 문제


        //시간복잡도 : 데이크스트라 100,000번, 데이크스트라 1번당 100,000번 탐색 (백트래킹 존재 : 어떤 도시에서든 적어도 하나의 면접장까지 갈 수 있는 경로가 항상 존재하기에)

        dijkstra();


        for(int i=1; i<=N; i++) {
            if(dp[i] > resTime) {
                resTime = dp[i];
                resV = i;
            }
        }

//        System.out.println(Arrays.toString(dp));

        System.out.println(resV);
        System.out.println(resTime);

    }

    private static void dijkstra() {

        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            int v = cur.v;
            long time = cur.w;

            if(dp[v] < time) continue; // 81% 시간초과

            for(Node n : list.get(v)) {
                if(dp[n.v] > time+n.w) {
                    pq.offer(new Node(n.v, time+n.w));
                    dp[n.v] = time + n.w;
                }
            }
        }
    }
}