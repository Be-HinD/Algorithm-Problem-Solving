import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ_5014 스타트링크
public class Main {
    static int F, S, G, U, D;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        F = Integer.parseInt(st.nextToken()); //총 층 수
        S = Integer.parseInt(st.nextToken()); //강호가 위치한 곳(시작점)
        G = Integer.parseInt(st.nextToken()); //스타트링크 위치(목적지)
        U = Integer.parseInt(st.nextToken()); //위로 U층
        D = Integer.parseInt(st.nextToken()); //아래로 D층


        int res = bfs();
        if(res == -1) System.out.println("use the stairs");
        else System.out.println(res);
    }

    private static int bfs() {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

//        boolean[] v = new boolean[F+1];
        pq.offer(new int[]{S,0}); //현재 위치, 움직인 횟수
        int[] dist = new int[F+1]; //거리 배열 사용..
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[S] = 0;
        dist[0] = 0; //0층을 방문처리 해줘야하는 이유?? 안하면 81% 틀림.
//        v[S] = true;

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            //도착한 경우
            if(cur[0] == G) {
                return cur[1];
            }

            int down = cur[0] - D;
            //범위를 초과하지 않고, 거리배열에 있는 값보다 낮을 시 방문
            if(down >= 0 && dist[down] > cur[1]) {
                pq.offer(new int[]{down,cur[1] + 1});
                dist[down] = cur[1];
            }

            int up = cur[0] + U;
            //범위를 초과하지 않고, 거리배열에 있는 값보다 낮을 시 방문
            if(up <=F && dist[up] > cur[1]) {
                pq.offer(new int[]{up, cur[1] + 1});
                dist[up] = cur[1];
            }
//            //내려가는 경우
//            int down = cur[0] - D;
//            if(down < 0 || v[down]) continue;
//            pq.offer(new int[]{down,cur[1] + 1});
//            v[down]  =true;
//
//
//            //올라가는 경우
//            int up = cur[0] + U;
//            if(up > F || v[up]) continue;
//            pq.offer(new int[]{up, cur[1] + 1});
//            v[up] = true;

        }
        return -1;
    }
}