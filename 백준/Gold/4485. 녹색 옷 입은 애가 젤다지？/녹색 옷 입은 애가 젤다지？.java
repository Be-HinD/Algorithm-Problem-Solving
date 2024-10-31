import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuffer sb = new StringBuffer();
        int tc = 1;

        while(true) {
            N = Integer.parseInt(br.readLine());
            if(N == 0) break;

            map = new int[N][N];
            for (int i = 0; i < N; i++) { //맵 입력
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int res = bfs();
            sb.append("Problem " + tc++ + ": " + res + "\n"); //결과값 담기
        }
        System.out.println(sb);
    }

    private static int bfs() {
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() { //가중치 기준 우선순위 큐
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });

        queue.offer(new int[]{0,0,map[0][0]});
        int[][] v = new int[N][N]; //좌표 별 최소비용 담을 배열
        boolean[][] vv = new boolean[N][N]; //방문체크 배열
        vv[0][0] = true;
        v[0][0] = map[0][0];

        while(!queue.isEmpty()) {
            int[] idx = queue.poll();

            for(int i=0; i<4; i++) {
                int nx = idx[0] + dx[i];
                int ny = idx[1] + dy[i];
                if(nx<0 || ny<0 || nx>=N || ny>=N || vv[nx][ny]) continue;
                v[nx][ny] = idx[2]+map[nx][ny]; //최소비용 갱신
                queue.offer(new int[]{nx,ny,idx[2]+map[nx][ny]});
                vv[nx][ny] = true; //방문 체크
            }
        }
        return v[N-1][N-1]; //도착지점의 값 리턴
    }
}