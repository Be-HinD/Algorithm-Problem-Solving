import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main {
    static int N, diff, Min; //입력크기, 섬마다의 고유번호, 최소값
    static int[][] map;
    static int[] dx = new int[] {-1,1,0,0}; //방향벡터
    static int[] dy = new int[] {0,0,-1,1}; //방향벡터

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for(int i=0; i<N; i++) { //맵 입력
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                int idx = Integer.parseInt(st.nextToken());
                map[i][j] = idx;
            }
        }

        diff = 2; //섬 고유번호 2부터 시작
        Min = Integer.MAX_VALUE;

        for(int i=0; i<N; i++) { //섬마다의 고유번호를 붙이는 BFS
            for (int j = 0; j < N; j++) {
                if(map[i][j] == 1) numberingBfs(new int[]{i,j});
            }
        }

        for(int start=2; start<diff; start++) { //각각의 섬마다 다른 섬을 찾아가는 BFS
            for(int i=0; i<N; i++) {
                for (int j = 0; j < N; j++) {
                    if(map[i][j] == start) mainBfs(start, new int[]{i,j,0});
                }
            }
        }
        System.out.println(Min);
    }

    private static void mainBfs(int self, int[] it) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(it);
        boolean[][] v = new boolean[N][N];
        v[it[0]][it[1]] = true;
        while(!queue.isEmpty()) {
            int[] item = queue.poll();
            for(int j=0; j<4; j++) {
                int nx = item[0] + dx[j];
                int ny = item[1] + dy[j];

                if(nx<0 || ny<0 || nx>N-1 || ny>N-1 || v[nx][ny]) continue;
                if(map[nx][ny] != 0 && map[nx][ny] != self) { //다른섬에 도달한 경우
                    Min = Math.min(Min, item[2]);
                    return;
                }
                if(map[nx][ny] == 0) { //인접 바다영역을 큐에 추가(시간은 스택형식)
                    v[nx][ny] = true;
                    queue.offer(new int[] {nx,ny, item[2] + 1});
                }
            }
        }
    }

    private static void numberingBfs(int[] it) {
        Queue<int[]> queue = new ArrayDeque<>();

        queue.offer(it);
        map[it[0]][it[1]] = diff;
        while(!queue.isEmpty()) {
            int[] item = queue.poll();

            for(int j=0; j<4; j++) {
                int nx = item[0] + dx[j];
                int ny = item[1] + dy[j];

                if(nx<0 || ny<0 || nx>N-1 || ny>N-1 || map[nx][ny] == 0) continue;
                if(map[nx][ny] == 1) { //인접해있는 섬의 경우
                    map[nx][ny] = diff; //고유번호 부여
                    queue.offer(new int[] {nx,ny});
                }
            }
        }
        diff++; //고유번호 index증가
    }
}
