import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Node {
        int end;
        int weight;
        Node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }
    }
    static int V, E, K;
    static int[] res;
    static ArrayList<ArrayList<Node>> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer(); //출력개수 == 정점 개수

        V = Integer.parseInt(st.nextToken()); //정점의 개수
        E = Integer.parseInt(st.nextToken()); //간선의 개수
        K = Integer.parseInt(br.readLine()) - 1; //시작 정점의 번호

        list = new ArrayList<>();
        for(int i=0; i<V; i++) list.add(new ArrayList<>());
        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;
            int w = Integer.parseInt(st.nextToken());
            list.get(x).add(new Node(y,w)); //인접리스트 입력
        }
        res = new int[V]; //경로값 배열
        Arrays.fill(res, Integer.MAX_VALUE);

        Dijkstra();

        for(int i=0; i<res.length; i++) {
            if(res[i] == Integer.MAX_VALUE) { //경로가 없을 경우
                sb.append("INF\n");
                continue;
            }
            sb.append(res[i] + "\n");
        }
        System.out.println(sb);
    }

    private static void Dijkstra() {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        pq.offer(new int[]{K, 0});
        res[K] = 0; //시작정점 초기화

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();

            for(int i=0; i<list.get(cur[0]).size(); i++) { //각 인접리스트에 대해
                Node n = list.get(cur[0]).get(i);
                if((cur[1] + n.weight) < res[n.end]) { //기존의 경로값보다 낮을 경우에만
                    pq.offer(new int[]{n.end, cur[1]+n.weight});
                    res[n.end] = cur[1]+n.weight; //경로값 갱신 (index : end 정점번호)
                }
            }
        }
    }
}