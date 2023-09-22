import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] map;
    static int[] dx = new int[]{1,-1,0,0,-1,-1,1,1}; //방향벡터
    static int[] dy = new int[]{0,0,1,-1,-1,1,1,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true) { //TestCase
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); //행
            M = Integer.parseInt(st.nextToken()); //열
            if(M == 0 && N ==0) return;
            map = new int[M][N]; //맵 초기화

            for(int i=0; i<M; i++) { //맵 입력
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            int ans = 0; //결과값 초기화
            for(int i=0; i<M; i++) { //좌표를 섬이 있는 지점부터 BFS 방문체크 및 ans++
                for(int j=0; j<N; j++) {
                    if(map[i][j] == 1) {
                        ans++;
                        bfs(i,j);
                    }
                }
            }
            System.out.println(ans);
        }
    }
    private static void bfs(int x, int y) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x,y});

        while(!queue.isEmpty()) {
            int idx[] = queue.poll();

            for(int i=0; i<4; i++) {
                int nx = idx[0] + dx[i];
                int ny = idx[1] + dy[i];
                if(nx<0 || ny<0 || nx>=M || ny>=N || map[nx][ny] == 0) continue; //예외처리
                queue.offer(new int[]{nx,ny});
                map[nx][ny] = 0; //방문체크
            }
            for(int i=4; i<dx.length; i++) { //대각선 체크
                int nx = idx[0] + dx[i];
                int ny = idx[1] + dy[i];
                if(nx<0 || ny<0 || nx>=M || ny>=N || map[nx][ny] == 0) continue; //예외처리
                queue.offer(new int[]{nx,ny});
                map[nx][ny] = 0; //방문체크
            }
        }
    }
}
