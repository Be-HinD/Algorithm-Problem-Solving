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
    static int N, M, sum, res;
    static ArrayList<ArrayList<Node>> list;
    static boolean[] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true) { //TestCase
            st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            
            if(N == 0 && M == 0) break; //기저조건

            list = new ArrayList<>();
            for (int i = 0; i < N; i++) list.add(new ArrayList<>()); //인접리스트 생성

            sum = 0; //전체 가중치의 합
            for (int i = 0; i < M; i++) { //입력
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                //양방향 그래프
                list.get(x).add(new Node(y,weight));
                list.get(y).add(new Node(x,weight));
                sum += weight;
            }

            v = new boolean[N];
            res = 0;

            Prim();

            System.out.println(sum - res);
        }
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
            int[] cur = pq.poll();
            int vertex = cur[0];

            if(v[vertex]) continue;

            v[vertex] = true;
            res += cur[1]; //최소경로의 가중치 값을 누적

            for(Node n : list.get(vertex)) {
                pq.offer(new int[]{n.v, n.w});
            }
        }
    }
}