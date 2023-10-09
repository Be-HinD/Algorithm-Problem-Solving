import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int tc, N, ex, ey;
    static int[] dx = new int[]{-2,-2,-1,-1,1,1,2,2}; //나이트가 갈 수 있는 모든 경우의 방향벡터
    static int[] dy = new int[]{-1,1,-2,2,-2,2,-1,1};
    static StringBuffer sb = new StringBuffer();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        tc = Integer.parseInt(br.readLine());

        for(int i=1; i<=tc; i++) {
            N = Integer.parseInt(br.readLine());
            //사실 상 2차원 배열 입력값으로 다룰 내용이 없으니 선언 안해도되는 문제!
            st = new StringTokenizer(br.readLine()); //시작지점 좌표
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine()); //목적지 좌표
            ex = Integer.parseInt(st.nextToken());
            ey = Integer.parseInt(st.nextToken());

            bfs(x, y);
        }
        System.out.println(sb);
    }

    private static void bfs(int x, int y) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] v = new boolean[N][N];
        v[x][y] = true; //시작지점 방문체크
        q.offer(new int[]{x, y, 0});

        while(!q.isEmpty()) {
            int[] idx = q.poll();
            if(idx[0] == ex && idx[1] == ey) { //목적지 비교
                sb.append(idx[2] + "\n");
                return;
            }

            for(int i=0; i<8; i++) {
                int nx = idx[0] + dx[i];
                int ny = idx[1] + dy[i];
                if(nx<0 || ny<0 || nx>=N || ny>=N || v[nx][ny]) continue;
                v[nx][ny] = true;
                q.offer(new int[]{nx,ny,idx[2]+1});
            }
        }
    }
}
