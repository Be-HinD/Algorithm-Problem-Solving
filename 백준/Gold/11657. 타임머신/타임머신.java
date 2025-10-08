import java.io.*;
import java.util.*;

//BOJ_11657 (Graph)
public class Main {
    static class Node {
        int start;
        int end;
        int time;
        public Node(int start, int end, int time) {
            this.start = start;
            this.end = end;
            this.time = time;
        }
    }
    static int n, m;
    static List<Node> list;
    static long[] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        /**
         * A : 시작도시, B : 도착도시, C : 걸리는 시간
         * C < 0 경우 시간 롤백
         * 노선 입력에 대해 연결리스트로 구성 (노드)
         * i번 시작에서 각 도시까지의 최단경로 = Dijkstra
         * 방문한 지점에 대해 더 낮은 값으로 방문 가능한 경우 -> 무한히 돌릴 수 있는 경우
         * 40% 반례
         * 해당 문제는 음수 싸이클을 판별하는 그래프 알고리즘을 활용해야 함.
         * 그러나 Dijkstra는 음수 싸이클을 판단하지 못함.
         * 벨만포드
         * **/

        n = Integer.parseInt(st.nextToken());   //마을 수
        m = Integer.parseInt(st.nextToken());   //노선 수

        list = new ArrayList<>();

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int delay = Integer.parseInt(st.nextToken());
            list.add(new Node(start, end, delay));
        }

        if(!BellmanFord()) {
            System.out.println(-1);
            return;
        }

        for(int i=2; i<=n; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                System.out.println(-1);
                continue;
            }
            System.out.println(dist[i]);
        }

    }

    static boolean BellmanFord() {
        //방문체크 대신 dist배열 활용 전부 무한대로 초기화
        dist = new long[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;

        for(int i=1; i<=n; i++) {
            for(int j=0; j<m; j++) {
                Node cur = list.get(j); //j번째 간선

                if(dist[cur.start] != Integer.MAX_VALUE && dist[cur.end] > dist[cur.start] + cur.time) {
                    dist[cur.end] = dist[cur.start] + cur.time;
                }
            }
        }

        for(int i=0; i<m; i++) {
            Node cur = list.get(i);

            if(dist[cur.start] != Integer.MAX_VALUE && dist[cur.end] > dist[cur.start] + cur.time) {
                return false;
            }
        }

        return true;
    }
}