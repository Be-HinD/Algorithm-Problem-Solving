import java.io.*;
import java.util.*;

public class Main {
    static int N, M; //행, 열
    static int map[][]; //입력 맵
    static int[] dx = {-1, 1, 0, 0}; //방향벡터
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M]; //맵 입력받기
        for(int i=0; i<N; i++) {
            String input = br.readLine();
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(String.valueOf(input.charAt(j)));
            }
        }

        bfs(new int[] {0, 0, 1}); // x, y, dist
    }

    private static void bfs(int[] p) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(p);
        boolean[][] v = new boolean[N][M]; //방문체크 배열
        v[p[0]][p[1]] = true; //시작지점 방문체크
        int ans = 0; //결과값
        while(!queue.isEmpty()) {
            int[] idx = queue.poll();
            if(idx[0] == N-1 && idx[1] == M-1) { //도착지점에 도달했을 경우
                ans = idx[2]; //결과값 저장
            }
            for(int i=0; i<4; i++) { //4방탐색
                int nx = idx[0] + dx[i];
                int ny = idx[1] + dy[i];
                if(nx>-1 && ny>-1 && nx<N && ny<M && !v[nx][ny] && map[nx][ny] != 0) { //널처리 및 방문체크, 막다른 길 체크
                    queue.offer(new int[]{nx, ny, idx[2] + 1});
                    v[nx][ny] = true;
                }
            }
        }
        System.out.println(ans); //결과출력
    }
}
