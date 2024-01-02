import org.w3c.dom.Node;

import java.io.*;
import java.util.*;

//BOJ_9370 미확인 도착지
public class Main {
    static class Node {
        int v;
        int w;

        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
    static int T;
    static int[] dist;
    static List<List<Node>> list;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb;

        T = Integer.parseInt(br.readLine());

        for(int tc=0; tc<T; tc++) {     //TestCase

            sb = new StringBuilder();

            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());   //교차로 개수
            int M = Integer.parseInt(st.nextToken());   //도로 개수
            int t = Integer.parseInt(st.nextToken());   //목적지 후보 개수

            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()) - 1;   //예술가들의 출발지
            int g = Integer.parseInt(st.nextToken()) - 1;   //지나간 곳
            int h = Integer.parseInt(st.nextToken()) - 1;   //지나간 곳

            list = new ArrayList<>();
            for(int i=0; i<N; i++) list.add(new ArrayList<>());

            for(int i=0; i<M; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken()) - 1;
                int end = Integer.parseInt(st.nextToken()) - 1;
                int weight = Integer.parseInt(st.nextToken());
                list.get(start).add(new Node(end, weight));
                list.get(end).add(new Node(start, weight));     //양방향
            }


            int[] candi = new int[t];
            for(int i=0; i<t; i++) {    //목적지 후보 입력
                candi[i] = Integer.parseInt(br.readLine()) - 1;
            }

            /*
            * 결과값을 오름차순으로 담을 우선순위 큐
            * */
            PriorityQueue<Integer> res = new PriorityQueue<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 - o2;
                }
            });


            //반례 >> 최단경로가 여러개 존재할 수 있음. (그 중 g와 h를 경유하는 경로가 있을 수 있는데 모든 경로를 탐색하진 않음)
            //Start -> end의 시간과
            //Math.min(Start -> g -> h -> end, Start -> h -> h -> end)의 값과 비교하여 같으면 true, 다르면 false
            for(int idx : candi) {
                dist = new int[N];
                Arrays.fill(dist, Integer.MAX_VALUE);
                Dijkstra(s);
                int min = dist[idx];    //최단시간

                int time = 0;
                int start = 0;
                int diff = 0;
                if(dist[g] > dist[h]) {
                    start = g;
                    diff = h;
                    time = dist[h];
                }
                else {
                    start = h;
                    diff = g;
                    time = dist[g];
                }
                Arrays.fill(dist, Integer.MAX_VALUE);
                Dijkstra(start);      //최단시간 구하기

                if(min == time + dist[diff] + dist[idx]) res.offer(idx);
            }

            while(!res.isEmpty()) {
                sb.append(res.poll() + 1).append(" ");
            }

            System.out.println(sb);

        }

    }

    private static void Dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.w - o2.w;
            }
        });

        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()) {
            Node cur = pq.poll();

            for(Node n : list.get(cur.v)) {
                int distance = cur.w + n.w;
                if(dist[n.v] > distance) {
                    pq.offer(new Node(n.v, distance));
                    dist[n.v] = distance;
                }
            }
        }

    }

}