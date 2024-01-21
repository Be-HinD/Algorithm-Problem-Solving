import java.io.*;
import java.util.*;

//BOJ_21278 호석이 두 마리 치킨
public class Main {
    static int N, K, ans;
    static int[] arr, dist, res;
    static List<List<Integer>> list;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 각 건물이 지어질 수 있는 모든 조합 5000개에 대해서
        // Dijkstra를 2번 진행. 1번째 치킨집으로부터 N개, 2번째 치킨집으로부터 N개
        // 거리배열은 한개로 최소값으로 계속 갱신
        //

        list = new ArrayList<>();
        for(int i=0; i<=N; i++) list.add(new ArrayList<>());

        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            list.get(start).add(end);
            list.get(end).add(start);   //양방향
        }

        arr = new int[2];
        res = new int[3];
        res[2] = Integer.MAX_VALUE;
        Comb(1, 0);

        for(int i=0; i<3; i++) System.out.print(res[i] + " ");
    }

    private static void Comb(int start, int cnt) {
        if(cnt > 1) {
            dist = new int[N+1];
            Arrays.fill(dist, Integer.MAX_VALUE);

            Dijkstra(arr[0]);
            Dijkstra(arr[1]);
            int sum = 0;
            for(int i=1; i<=N; i++) sum += (dist[i]*2);

            if(res[2] > sum) {
                res[0] = arr[0];
                res[1] = arr[1];
                res[2] = sum;
            }
            return;
        }
        for(int i=start; i<=N; i++) {

            arr[cnt] = i;
            Comb(i+1, cnt+1);
        }
    }

    private static void Dijkstra(int start) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        pq.offer(new int[]{start, 0});
        dist[start] = 0;

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();

            for(int idx : list.get(cur[0])) {
                if(dist[idx] > cur[1] + 1) {
                    pq.offer(new int[]{idx, cur[1]+1});
                    dist[idx] = cur[1]+1;
                }
            }
        }
    }
}