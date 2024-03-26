import java.io.*;
import java.util.*;

//BOJ_18352 특정 거리의 도시 찾기
public class Main {
    static List<List<Integer>> list;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   //도시 개수
        int M = Integer.parseInt(st.nextToken());   //도로 개수
        int K = Integer.parseInt(st.nextToken());   //거리 정보
        int X = Integer.parseInt(st.nextToken());   //출발 도시 번호
        
        list = new ArrayList<>();
        for(int i=0; i<=N; i++) list.add(new ArrayList<>());
        
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            
            list.get(start).add(end);
        }
        
        int[] res = Dijkstra(X, N, K);
        Queue<Integer> q = new ArrayDeque<>();
        for(int i=0; i<res.length; i++) {
            if(res[i] == K) q.offer(i);
        }

        if(q.isEmpty()) {
            System.out.println(-1);
            return;
        }
        StringBuilder sb = new StringBuilder();
        while(!q.isEmpty()) {
            sb.append(q.poll()).append("\n");
        }

        System.out.println(sb);
    }
    
    private static int[] Dijkstra(int start, int N, int K) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        pq.offer(new int[]{start, 0});
        int[] v = new int[N+1];
        Arrays.fill(v, Integer.MAX_VALUE);
        v[start] = 0;

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();

            if(cur[1] > K) continue;    //1500ms애서 한 줄 추가해봄.
            
            for(int idx : list.get(cur[0])) {
                if(v[idx] > cur[1] + 1) {
                    v[idx] = cur[1] + 1;
                    pq.offer(new int[]{idx, cur[1] + 1});
                }
            }
        }

        return v;
    }
}