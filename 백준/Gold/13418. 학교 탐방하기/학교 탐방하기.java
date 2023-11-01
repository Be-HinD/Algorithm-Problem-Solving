import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ_13418 학교 탐방하기
public class Main {
    static class Node {
        int v;
        int flag;
        public Node(int v, int flag) {
            this.v = v;
            this.flag = flag;
        }
    }
    static int N, M, worstRes, bestRes;
    static ArrayList<ArrayList<Node>> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //정점 개수
        M = Integer.parseInt(st.nextToken()); //간선 개수

        list = new ArrayList<>();
        for(int i=0; i<=N; i++) list.add(new ArrayList<>());

        for(int i=0; i<=M; i++) { //연결리스트 입력
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int flag = Integer.parseInt(st.nextToken());
            //단방향처럼 문제 설명했는데 양방향임.
            list.get(start).add(new Node(end, flag));
            list.get(end).add(new Node(start, flag));
        }

        minPrim();
        maxPrim();

        //두 가지 값 모두 제곱처리 해야함.
        System.out.println((int)Math.pow(worstRes,2) - (int)Math.pow(bestRes,2));

    }

    private static void minPrim() {
        //최소 스패닝 트리
        //플래그값 기준으로 내림차순 -> 내리막길 우선순위
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];
            }
        });

        pq.offer(new int[]{0, 1}); //정점, 플래그
        boolean[] v = new boolean[N+1];

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int vertex = cur[0];
            int flag = cur[1];

            if (v[vertex]) continue;

            v[vertex] = true;
            if(flag == 0) bestRes++;


            for (Node n : list.get(vertex)) {
                if (!v[n.v]) {
                    pq.offer(new int[]{n.v, n.flag});
                }
            }
        }
    }

    private static void maxPrim() {
        //최대 스패닝 트리
        //플래그값 기준으로 오름차순 -> 오르막길 우선순위
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        pq.offer(new int[]{0, 1}); //정점, 플래그, 오르막길 개수
        boolean[] v = new boolean[N+1];

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int vertex = cur[0];
            int flag = cur[1];

            if(v[vertex]) continue;

            v[vertex] = true;
            if(flag == 0) worstRes++;

            for(Node n : list.get(vertex)) {
                if(!v[n.v]) {
                    pq.offer(new int[]{n.v, n.flag});
                }
            }
        }
    }
}