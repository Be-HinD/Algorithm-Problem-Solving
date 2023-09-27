import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int R, C, ans;
    static char[][] map;
    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};
    static Queue<int[]> queue = new ArrayDeque<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        int[] sp = new int[2]; //상근이의 좌표값 저장배열
        for(int i=0; i<R; i++) { //맵 입력
            String input = br.readLine();
            for(int j=0; j<C; j++) {
                char idx = input.charAt(j);
                if(idx == 'F') queue.offer(new int[]{i,j,0,1}); //좌표, 시간, 불/상근 플래그
                if(idx == 'J') {
                    sp[0] = i;
                    sp[1] = j;
                }
                map[i][j] = idx;
            }
        }
        if (sp[0] == 0 || sp[1] == 0 || sp[0] == R - 1 || sp[1] == C - 1) { //도착
            System.out.println(1);
            return;
        }
        queue.offer(new int[]{sp[0], sp[1], 0, 0}); //불 먼저 추가 후 상근이 추가

        bfs();
        if(ans > 0) System.out.println(ans);
        else System.out.println("IMPOSSIBLE");
    }

    private static void bfs() {
        while(!queue.isEmpty()) {
            int[] idx = queue.poll();
            for(int i=0; i<4; i++) {
                int nx = idx[0] + dx[i];
                int ny = idx[1] + dy[i];
                if(nx<0 || ny<0 || nx>=R || ny>=C || map[nx][ny] == '#' || map[nx][ny] == 'F') continue; //맵밖, 벽, 불 예외처리
                //79%반례 : 이전 불(5427)의 문제를 풀고온 후 불의 입력값을 * 그대로 처리해서 생기는 반례 존재 이후 F로 변경
                if(idx[3] == 1) { //불일 경우
                    map[nx][ny] = 'F';
                    queue.offer(new int[]{nx,ny,0,1});
                } else { //상근이의 경우
                    if (nx == 0 || ny == 0 || nx == R - 1 || ny == C - 1) { //도착
                        ans = idx[2] + 2;
                        return;
                    }
                    if(map[nx][ny] != '&') { //방문체크
                        map[nx][ny] = '&';
                        queue.offer(new int[]{nx, ny, idx[2] + 1, 0});
                    }
                }
            }
        }
    }
}
//79% 반례
//3 4
//##.#
//FJ.#
//##F#

//ans : IMPOSSIBLE
