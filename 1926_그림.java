import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,M, ans;
    static int[][] map;
    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M =  Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i=0; i<N; i++) { //맵 입력
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int imageCnt = 0; //그림의 개수
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j] == 1) { //그림을 발견한 경우 BFS로직 수행
                    bfs(i, j);
                    imageCnt++;
                }
            }
        }
        System.out.println(imageCnt);
        System.out.println(ans);
    }

    private static void bfs(int x, int y) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x,y});
        map[x][y] = 0; //방문체크
        int imageSize = 1; //그림의 사이즈
        
        while(!queue.isEmpty()) {
            int[] idx = queue.poll();
            for(int i=0; i<4; i++) {
                int nx = idx[0] + dx[i];
                int ny = idx[1] + dy[i];
                if(nx<0 || ny<0 || nx>=N || ny>=M || map[nx][ny] == 0) continue; //예외처리
                queue.offer(new int[]{nx,ny});
                map[nx][ny] = 0; //방문체크
                imageSize++;
            }
        }
        ans = Math.max(ans, imageSize); //최대값 비교 및 갱신
    }
}
