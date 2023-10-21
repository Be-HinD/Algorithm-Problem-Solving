import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, X, res;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //학생 및 마을 개수
        M = Integer.parseInt(st.nextToken()); //단방향 도로의 개수
        X = Integer.parseInt(st.nextToken()); //경유 마을 번호

        map = new int[N+1][N+1]; //시작 번호 1번
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken()); //가중치
            map[x][y] = weight; //단방향 그래프
        }

        for(int i=1; i<=N; i++) {
            int sum = 0;
            //i번 학생이 i번째 마을에서 출발해서 x마을까지 도착하는데 걸리는 최소비용 구하기
            sum = bfs(i, X);
            //i번 학생이 x마을에서 출발해서 i번째 마을까지 도착하는데 걸리는 최소비용 구하기
            sum += bfs(X, i);
            //
            res = Math.max(res, sum);
        }

        System.out.println(res);
    }

    private static int bfs(int start, int dest) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        int[] dist = new int[N+1]; //start에서 N번째 마을로 가는 최소비용
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        pq.offer(new int[]{start, 0}); //출발지, 소요시간

        while(!pq.isEmpty()) {
            int[] idx = pq.poll(); //최소 소요시간부터 꺼내서
            int cur = idx[0]; //현재 마을

            if(cur == dest) { //목표 마을에 도착할 경우
                return idx[1]; //소요시간 리턴
            }

            for(int i=0; i<N+1; i++) {
                if(map[cur][i] > dist[i]) continue;
                if(cur == i) continue; //같은 정점이거나 방문했던 도로일 경우
                if(map[cur][i] == 0) continue;
                dist[i] = map[cur][i];
                pq.offer(new int[]{i,idx[1]+map[cur][i]});
            }
        }
        return -1;
    }
}