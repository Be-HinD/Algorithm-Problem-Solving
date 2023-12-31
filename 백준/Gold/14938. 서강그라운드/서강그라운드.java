import java.io.*;
import java.util.*;

//BOJ_14938 서강그라운드
public class Main {
    static class Node {
        int v;
        int w;

        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
    static int N, M, R, res;
    static int[] arr;
    static List<List<Node>> list;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //맵 크기
        M = Integer.parseInt(st.nextToken()); //수색 범위
        R = Integer.parseInt(st.nextToken()); //길의 개수

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {        //아이템 개수 입력
            arr[i] = Integer.parseInt(st.nextToken());
        }

        list = new ArrayList<>();
        for(int i=0; i<N; i++) list.add(new ArrayList<>());

        for(int i=0; i<R; i++) {        //리스트 입력
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken())-1;
            int end = Integer.parseInt(st.nextToken())-1;
            int weight = Integer.parseInt(st.nextToken());
            list.get(start).add(new Node(end, weight));
            list.get(end).add(new Node(start, weight));     //양방향 그래프
        }

        //접근법 : 각 정점으로부터 데이크스트라를 수행하여 최단경로를 구함.
        //데이크스트라를 통해 갱신된 정점으로부터의 아이템 개수의 합을 구한 후 결과값 비교 및 갱신
        


        for(int i=0; i<N; i++) {
            res = Math.max(res, Dijkstar(i));
        }

        System.out.println(res);
    }

    private static int Dijkstar(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.w - o2.w;
            }
        });

        int[] dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);
        pq.offer(new Node(start, 0));
        dist[start] = 0;

        int cnt = 0;
        while(!pq.isEmpty()) {
            Node cur = pq.poll();

            for(Node n : list.get(cur.v)) {
                if(cur.w + n.w > M) continue;
                if(dist[n.v] > cur.w + n.w) {
                    dist[n.v] = cur.w + n.w;
                    pq.offer(new Node(n.v, cur.w+n.w));
                }
            }
        }

        for(int i=0; i<N; i++) {
            if(dist[i] == Integer.MAX_VALUE) continue;
            cnt += arr[i];
        }

        return cnt;
    }
}