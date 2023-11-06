import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ_5014 스타트링크
//PQ대신 QUEUE 사용한 풀이.
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
        Queue<int[]> q = new ArrayDeque<>();

        q.offer(new int[]{S,0}); //현재 위치, 움직인 횟수
        int[] dist = new int[F+1]; //거리 배열 사용..
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[S] = 0;
        dist[0] = 0; //0층을 방문처리 해줘야하는 이유?? 안하면 81% 틀림. => 이 부분 0층은 없는데 0층 찍고 더 빨리 가는길이 생길 수 있음 ㅋㅋ.

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            //도착한 경우
            if(cur[0] == G) {
                return cur[1];
            }

            int down = cur[0] - D;
            //범위를 초과하지 않고, 거리배열에 있는 값보다 낮을 시 방문
            if(down >= 0 && dist[down] > cur[1]) {
                q.offer(new int[]{down,cur[1] + 1});
                dist[down] = cur[1];
            }

            int up = cur[0] + U;
            //범위를 초과하지 않고, 거리배열에 있는 값보다 낮을 시 방문
            if(up <=F && dist[up] > cur[1]) {
                q.offer(new int[]{up, cur[1] + 1});
                dist[up] = cur[1];
            }
        }
        return -1;
    }
}