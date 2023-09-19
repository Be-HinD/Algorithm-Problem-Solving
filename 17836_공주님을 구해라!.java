import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, T, ans;
    static int[][] map;
    static int[] dx = new int[]{1,-1,0,0}; //방향벡터
    static int[] dy = new int[]{0,0,1,-1};
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for(int i=0; i<N; i++) { //맵 입력
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        ans = Integer.MAX_VALUE; //최소값 비교를 위한 초기화
        bfs();
        if(ans > T) System.out.println("Fail");
        else System.out.println(ans);
    }

    private static void bfs() {
        Queue<Point> queue = new ArrayDeque<>();
        queue.offer(new Point(0, 0, false, 0));
        boolean[][] v = new boolean[N][M];
        boolean[][] newv = new boolean[N][M]; //명검을 먹은 순간부터의 방문배열
        v[0][0] = true;

        while(!queue.isEmpty()) {
            Point idx = queue.poll();
            if(idx.x == N-1 && idx.y == M-1) { //공주님에게 도달한 경우
                ans = idx.time;
                return;
            }
            for(int i=0; i<4; i++) {
                int nx = idx.x + dx[i];
                int ny = idx.y + dy[i];
                if(!idx.flag) { //명검유무 체크
                    if (nx < 0 || ny < 0 || nx >= N || ny >= M || v[nx][ny]) continue; //예외처리
                    if(map[nx][ny] == 2) { //명검을 먹은 시점
                        newv = new boolean[N][M]; //명검전용 방문배열 초기화
                        newv[nx][ny] = true;
                        queue.offer(new Point(nx, ny, true, idx.time+1));
                        continue;
                    }
                    if(map[nx][ny] == 0) { //명검이 없을 땐 0인 곳만
                        v[nx][ny] = true;
                        queue.offer(new Point(nx, ny, false, idx.time+1));
                    }
                } else { //명검 있을 때
                    if (nx < 0 || ny < 0 || nx >= N || ny >= M || newv[nx][ny]) continue;
                    newv[nx][ny] = true;
                    queue.offer(new Point(nx, ny, true, idx.time+1));
                }
            }
        }
    }
}

class Point { //bfs를 위한 클래스(좌표, 명검유무, 시간)
    int x;
    int y;
    boolean flag;
    int time;
    Point(int x, int y, boolean flag, int time) {
        this.x = x;
        this.y = y;
        this.flag = flag;
        this.time = time;
    }
}
