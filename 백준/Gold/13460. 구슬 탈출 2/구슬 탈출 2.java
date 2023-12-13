import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ_13460 구슬 탈출 2
public class Main {
    static class BALL { //각 게임 결과에 대한 정보를 담는 클래스
        int rx;
        int ry;
        int bx;
        int by;
        int count;

        public BALL(int rx, int ry, int bx, int by, int count) {
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
            this.count = count;
        }
        public BALL() {}
    }
    static int N, M, res;
    static BALL start = new BALL();
    static char[][] map;
    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,-1,1};
    static boolean[][][][] v = new boolean[10][10][10][10];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for(int i=0; i<N; i++) {
            String input = br.readLine();
            for(int j=0; j<M; j++) {
                char idx = input.charAt(j);
                map[i][j] = idx;
                if(idx == 'R') {
                    start.rx = i;
                    start.ry = j;
                }
                else if(idx == 'B') {
                    start.bx = i;
                    start.by = j;
                }
                else if(idx == 'O') {
                    v[i][j][i][j] = true;
                }
            }
        }

        start.count = 0; //초기화
        res = -1; //초기화

        bfs();
        if(res != -1) System.out.println(res);
        else System.out.println(-1);

    }

    private static void bfs() {
        Queue<BALL> q = new ArrayDeque<>();
        q.offer(start); //시작지점 큐에 추가
        v[start.rx][start.ry][start.bx][start.by] = true;
        
        while(!q.isEmpty()) {
            BALL ball = q.poll();

            if(ball.count > 10) break; //게임횟수가 10번 이상되면 종료.
            if(map[ball.rx][ball.ry] == 'O' && map[ball.bx][ball.by] != 'O') { //빨간 공만 구멍에 넣을 경우
                res = ball.count;
                break;
            }

            for(int i=0; i<4; i++) { //현 좌표에 대해 4방향 탐색
                int nextRx = ball.rx;
                int nextRy = ball.ry;
                int nextBx = ball.bx;
                int nextBy = ball.by;

                while(true) { //빨간 공에 대한 시뮬레이션
                    if (map[nextRx][nextRy] != '#' && map[nextRx][nextRy] != 'O') { //진행 가능한 경우(즉. 다음지점이 . 이거나 다른 공이 있을 경우에만)
                        nextRx += dx[i];
                        nextRy += dy[i];
                    } else { //진행 불가능한 경우
                        if (map[nextRx][nextRy] == '#') { //벽이라면 이전 칸으로 대치
                            nextRx -= dx[i];
                            nextRy -= dy[i];
                        }
                        break;
                    }
                }
                while(true) { //파란 공에 대한 시뮬레이션
                    if (map[nextBx][nextBy] != '#' && map[nextBx][nextBy] != 'O') { //진행 가능한 경우
                        nextBx += dx[i];
                        nextBy += dy[i];
                    } else { //진행 불가능한 경우
                        if (map[nextBx][nextBy] == '#') { //벽이라면 이전 칸으로 대치
                            nextBx -= dx[i];
                            nextBy -= dy[i];
                        }
                        break;
                    }
                }

                if(nextRx == nextBx && nextRy == nextBy) { //구해진 두 개의 공의 좌표가 겹칠 경우
                    if(map[nextRx][nextRy] != 'O') { //빨간공이 목표지점일 경우에는
                        int redDist = Math.abs(nextRx - ball.rx) + Math.abs(nextRy - ball.ry);
                        int blueDist = Math.abs(nextBx - ball.bx) + Math.abs(nextBy - ball.by);
                        if(redDist > blueDist) { //파란공이 앞에 있어야 할 경우
                            nextRx -= dx[i];
                            nextRy -= dy[i];
                        }
                        else {
                            nextBx -= dx[i];
                            nextBy -= dy[i];
                        }
                    }
                }
                //만약 결과좌표가 탐색했었던 좌표라면 큐에 추가하지 않음.
                if(!v[nextRx][nextRy][nextBx][nextBy]) {
                    v[nextRx][nextRy][nextBx][nextBy] = true;
                    BALL b = new BALL(nextRx, nextRy, nextBx, nextBy, ball.count+1);
                    q.offer(b);
                }
            }
        }
    }
}