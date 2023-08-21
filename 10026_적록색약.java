import java.util.*;
import java.io.*;

public class Main {
    static int N, cnt;
    static char map[][];
    static boolean visited[][];
    static int[] dx = new int[] {-1, 1, 0, 0};
    static int[] dy = new int[] {0, 0, 1, -1};
    static String input;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        map = new char[N][N];
        for(int i=0; i<N; i++) { //map 입력
            input = br.readLine();
            for(int j=0; j<N; j++) {
                map[i][j] = input.charAt(j);
            }
        }
        //색약아닌 경우
        visited = new boolean[N][N]; //방문배열 초기화
        for(int i=0; i<N; i++) { //map[i][j]가 방문하지 않은 곳이라면 bfs
            for(int j=0; j<N; j++) {
                if(!visited[i][j]) bfs(new Point(i, j));
            }
        }
        System.out.print(cnt + " ");
        //색약인 경우
        cnt = 0; //값 초기화
        visited = new boolean[N][N]; //방문배열 초기화

        for(int i=0; i<N; i++) { //색약의 경우 G를 전부 R로 변경
            for(int j=0; j<N; j++) {
                if(map[i][j] == 'G') map[i][j] = 'R';
            }
        }
        for(int i=0; i<N; i++) { //map[i][j]가 방문하지 않은 곳이라면 bfs
            for(int j=0; j<N; j++) {
                if(!visited[i][j]) bfs(new Point(i, j));
            }
        }
        System.out.print(cnt);
    }

    private static void bfs(Point p) {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(p);
        visited[p.x][p.y] = true;
        char item = map[p.x][p.y]; //어떤색 탐색할건지
        
        while(!q.isEmpty()) {
            Point pt = q.poll();

            for(int i=0; i<4; i++) {
                int nx = pt.x + dx[i];
                int ny = pt.y + dy[i];
                if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if(!visited[nx][ny] && map[nx][ny] == item) { //방문하지않은 곳이며 색깔이 같은 곳일때
                    q.offer(new Point(nx,ny));
                    visited[nx][ny] = true; //방문
                }
            }
        }
        cnt++;
    }
}

class Point {
    int x;
    int y;
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
