import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ_10282 해킹
public class Main {
    static int N, D, C;
    static long res;
    static List<List<int[]>> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuffer sb = new StringBuffer();

        int tc = Integer.parseInt(br.readLine());

        for(int t=0; t<tc; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            list = new ArrayList<>();
            for(int i=0; i<N+1; i++) list.add(new ArrayList<>());

            for(int i=0; i<D; i++) { //의존성 입력
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());
                list.get(b).add(new int[]{a,s}); //양방향
            }

            res = 0;
            int cnt = Dijkstra(C);
            sb.append(cnt + " " + res).append("\n");
        }
        System.out.println(sb);
    }

    private static int Dijkstra(int start) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        int dist[] = new int[N+1]; //해당 정점이 감염될 때까지의 최단 시간
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        pq.offer(new int[]{start,0}); //정점, 걸린 시간

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();

            if(dist[cur[0]] < cur[1]) continue;
            
            for(int[] idx : list.get(cur[0])) { //cur[0]번 정점과 인접해있는 모든 정점들에 대해서
                if(dist[idx[0]] > cur[1] + idx[1]) { //idx정점으로 갈 때 시간이 더 적게 걸리는 경우에 대해서만
                    pq.offer(new int[]{idx[0], cur[1] + idx[1]});
                    dist[idx[0]] = cur[1] + idx[1]; //최단 감염시간 갱신
                }
            }
        }
        
        int cnt = 0; //감염된 컴퓨터 개수
        for(int i=1; i<dist.length; i++) {
            if(dist[i] != Integer.MAX_VALUE) {
                res = Math.max(res, dist[i]);
                cnt++;
            }
        }
        return cnt;
    }
}