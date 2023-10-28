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
    static int N, M;
    static int[] dist; //Dijkstra 배열
    static int[] adj; //경로 배열
    static Node goal; //시작, 도착 정점 노드
    static ArrayList<ArrayList<Node>> list = new ArrayList<>(); //인접리스트

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

        //시작 및 도착지점 입력
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken()) - 1;
        int y = Integer.parseInt(st.nextToken()) - 1;
        goal = new Node(x, y);

        Dijkstra();

        System.out.println(dist[goal.w]); //최단시간 출력


        ArrayList<Integer> answerAdj = new ArrayList<>(); //경로 역추적을 위한 리스트
        int p = goal.w; //배열 참조 포인터
        answerAdj.add(goal.w+1); //도착지점 추가
        while(true) {
            answerAdj.add(adj[p] + 1);
            p = adj[p];
            if(p == goal.v) break;
        }

        System.out.println(answerAdj.size()); //지나온 경로 수 출력

        for(int i = answerAdj.size()-1; i>=0; i--) { //경로 출력
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

        adj = new int[N];
        dist = new int[N];
        //배열 초기화
        Arrays.fill(dist, Integer.MAX_VALUE);
        adj[goal.v] = 0;
        dist[goal.v] = 0;

        pq.offer(new int[]{goal.v, 0}); //시작정점, 지나온 시간

        while(!pq.isEmpty()) {
            int[] idx = pq.poll();
            int curV = idx[0];
            int time = idx[1];

            if(dist[curV] < time) continue; //기록된 현재 정점까지의 시간보다 지금 큐의 시간이 더 크다면 비교 X

            for(Node n : list.get(curV)) {
                //인접 정점을 한개씩 꺼내서 비교
                if(dist[n.v] > time + n.w) { //n.v까지 가기위한 시간이 이 전에 갱신했던 값보다 작다면
                    adj[n.v] = curV;
                    dist[n.v] = time + n.w; //거리값 업데이트
                    pq.offer(new int[]{n.v, dist[n.v]});
                }
            }
        }
    }
}