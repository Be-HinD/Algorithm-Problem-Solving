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
    static int N, M, res, cnt;
    static int[] dist;
    static int[] adj;
    static Node goal;
    static ArrayList<ArrayList<Node>> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuffer sb = new StringBuffer();

        N = Integer.parseInt(br.readLine()); //정점 개수
        M = Integer.parseInt(br.readLine()); //간선 개수

        for(int i=0; i<N; i++) list.add(new ArrayList<>());

        for(int i=0; i<M; i++) { //인접리스트 초기화
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1; //시작정점 수정
            int end = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());
            //단방향 그래프
            list.get(start).add(new Node(end, weight));
        }

        //도착지점 입력
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken()) - 1;
        int y = Integer.parseInt(st.nextToken()) - 1;
        goal = new Node(x, y);

        Dijkstra();
//
//        System.out.println(Arrays.toString(dist));
//        System.out.println(Arrays.toString(adj));
        System.out.println(dist[goal.w]);


        ArrayList<Integer> answerAdj = new ArrayList<>();
        int p = goal.w;
        answerAdj.add(goal.w+1);
        while(true) {
            answerAdj.add(adj[p] + 1);
            p = adj[p];
            if(p == goal.v) break;
        }

        System.out.println(answerAdj.size());
        for(int i = answerAdj.size()-1; i>=0; i--) {
            System.out.print(answerAdj.get(i) + " ");
        }

    }

    private static void Dijkstra() {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        adj = new int[N]; //지나온 경로 저장 배열
        dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);

        adj[goal.v] = 0;
        dist[goal.v] = 0;

        boolean[] v = new boolean[N];
        pq.offer(new int[]{goal.v, 0}); //시작정점, 지나온 시간

        while(!pq.isEmpty()) {
            int[] idx = pq.poll();
            int curV = idx[0];
            int time = idx[1];

            if(v[curV]) continue;

            v[curV] = true;


            for(Node n : list.get(curV)) {
                //인접 정점을 한개씩 꺼내서 비교
                if(dist[n.v] <= time + n.w) continue;
                adj[n.v] = curV;
                dist[n.v] = time + n.w;
                pq.offer(new int[]{n.v, dist[n.v]});
            }
        }
    }
}