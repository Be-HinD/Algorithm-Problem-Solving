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
    static Node goal;
//    static int[] dx = new int[]{1,-1,0,0};
//    static int[] dy = new int[]{0,0,1,-1};
    static ArrayList<ArrayList<Node>> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

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
        boolean[] v = new boolean[N];
        pq.offer(new int[]{goal.v, 0});

        while(!pq.isEmpty()) {
            int[] idx = pq.poll();
            int vertex = idx[0];
            int time = idx[1];

            //기저조건
            if(vertex == goal.w) {
                res = time;
                return;
            }

            if(v[vertex]) continue;
            v[vertex] = true;

            for(Node n : list.get(vertex)) {
                if(v[n.v]) continue;
                pq.offer(new int[]{n.v, n.w + time});
            }
        }
    }
}