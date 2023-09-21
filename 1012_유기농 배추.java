import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int T, N, M, K;
    static int[][] map;
    static int[] dx = new int[]{1,-1,0,0}; //방향벡터
    static int[] dy = new int[]{0,0,1,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        for(int tc=0; tc<T; tc++) { //TestCase
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken()); //행
            N = Integer.parseInt(st.nextToken()); //열
            K = Integer.parseInt(st.nextToken()); //입력개수
            map = new int[N][M]; //맵 초기화

            for(int i=0; i<K; i++) { //배추 입력
                st = new StringTokenizer(br.readLine());
                int y = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                map[x][y] = 1;
            }
            int ans = 0; //결과값 초기화
            for(int i=0; i<N; i++) { //좌표를 순회하면서 배추가 있는 지점부터 bfs 및 결과값 증가
                for(int j=0; j<M; j++) {
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
                if(nx<0 || ny<0 || nx>=N || ny>=M || map[nx][ny] == 0) continue; //예외처리
                queue.offer(new int[]{nx,ny});
                map[nx][ny] = 0; //방문체크
            }
        }
    }
}
