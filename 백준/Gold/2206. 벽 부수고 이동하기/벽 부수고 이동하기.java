import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, ans;
    static int[][] map;
    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i=0; i<N; i++) { //맵 입력
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                int idx = input.charAt(j) - '0';
                map[i][j] = idx;
            }
        }

        if(N==1 && M==1) { //출발 및 도착지점이 동일한 경우 전처리
            System.out.println(1);
            return;
        }

        bfs();

        if(ans == 0) System.out.println(-1);
        else System.out.println(ans);
    }

    private static void bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0,0,0,1,0}); //x, y, time, 벽 flag, 방문배열 참조 인덱스
        boolean[][][] v = new boolean[100][N][M]; //달차가처럼 분기쳐야하는건가?
        v[0][0][0] = true;

        while(!queue.isEmpty()) {
            int[] idx = queue.poll();
            if(idx[0] == N-1 && idx[1] == M-1) {
                ans = idx[2] + 1; //1초 시작이기 때문에 +1
                return;
            }
            for(int i=0; i<4; i++) {
                int nx = idx[0] + dx[i];
                int ny = idx[1] + dy[i];
                if(nx<0 || ny<0 || nx>=N || ny>=M || v[idx[4]][nx][ny]) continue;

                if(map[nx][ny] == 0) { //벽이 아닐 때
                    if(idx[3] == 1) { //flag따라
                        queue.offer(new int[]{nx, ny, idx[2] + 1, 1, 0});
                        v[0][nx][ny] = true;
                    } else {
                        queue.offer(new int[]{nx, ny, idx[2] + 1, 0, idx[4]});
                        v[idx[4]][nx][ny] = true;
                    }
                } else { //벽일 때
                    if(idx[3] == 1) { //부술 수 있을 때
                        queue.offer(new int[]{nx, ny, idx[2] + 1, 0, idx[4] + 1}); //flag 갱신 및 방문배열 참조인덱스 갱신
                        v[idx[4]+1][idx[0]][idx[1]] = true; //현재좌표 또한 방문체크 (없을 시 메모리 초과)
                        v[idx[4] + 1][nx][ny] = true;
                    }
                }
            }
        }
    }
}