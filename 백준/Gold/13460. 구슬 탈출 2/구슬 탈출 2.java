import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ_13459 구슬 탈출
public class Main {
    static int N, M, point, res;
    static char[][][] map;
    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,-1,1};
    static int[] hole = new int[2];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());


        map = new char[1000000][N][M];
        for(int i=0; i<N; i++) {
            String input = br.readLine();
            for(int j=0; j<M; j++) {
                char idx = input.charAt(j);
                map[0][i][j] = idx;
                if(idx == 'O') {
                    hole[0] = i;
                    hole[1] = j;
                }
            }
        }

        //상하좌우로 기울일 수 있고,
        //기울였다는 가정하에 해당방향으로 구슬이 이동
        //이동이 끝나는게 기울이는 동작이 끝났다는 것.
        point = 1;

        if(bfs()) System.out.println(res);
        else System.out.println(-1);

    }

    private static boolean bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0,0,-1}); //게임 횟수, 맵 인덱스, 어느방향으로 밀었는지

        while(!q.isEmpty()) {
            int[] cur = q.poll();

            if(cur[0] == 10) continue; //종료조건

            //해당 게임횟수 및 맵에 관해 총 4번(상하좌우)의 게임 로직을 수행하고, 결과를 서로다른 인덱스에 저장 및 큐에 추가.
            for(int d=0; d<4; d++) {
                if(cur[2] == d) continue;
                boolean redFlag = false;
                boolean blueFlag = false;
                for (int i = 0; i < N; i++) {
                    map[point][i] = map[cur[1]][i].clone();
                }
                Queue<int[]> ball = new ArrayDeque<>();
                for (int i = 0; i < N; i++) { //큐에 공 담는 과정
                    for (int j = 0; j < M; j++) {
                        if (map[cur[1]][i][j] == 'R') ball.offer(new int[]{i,j,1});
                        else if (map[cur[1]][i][j] == 'B') ball.offer(new int[]{i,j,2});
                    }
                }

                while(!ball.isEmpty()) {
                    int[] curBall = ball.poll();

                    int nx = curBall[0] + dx[d];
                    int ny = curBall[1] + dy[d];
                    if(nx<0 || ny<0 || nx>=N || ny>=M) continue;

                    if(map[point][nx][ny] == '.') { //이동 가능
                        ball.offer(new int[]{nx,ny,curBall[2]});
                        map[point][nx][ny] = map[point][curBall[0]][curBall[1]];
                        map[point][curBall[0]][curBall[1]] = '.'; //기존자리 비우기
                    }
                    else if(map[point][nx][ny] == 'B' || map[point][nx][ny] == 'R') {
                        nx += dx[d];
                        ny += dy[d];
                        if(nx<0 || ny<0 || nx>=N || ny>=M) continue;
                        if(map[point][nx][ny] == '.' || map[point][nx][ny] == 'O') { //빈자리일 때만 추가하면 87% 틀렸습니다. 그 다음자리가 hole일 경우에도 추가해줘야함!.. 두개가 같이 빠질 수 있도록.
                            ball.offer(new int[]{curBall[0],curBall[1],curBall[2]}); //기존자리 한번 더 추가
                        }
                    }
                    else if(map[point][nx][ny] == 'O') {
                        if(curBall[2] == 1) redFlag = true;
                        else blueFlag = true;
                        map[point][curBall[0]][curBall[1]] = '.'; //기존자리 비우기
                    }
                }
                if(redFlag && !blueFlag) {
                    res = cur[0] + 1;
                    return true;
                }
                else if(!redFlag && !blueFlag) { //두 개의 공이 들어가지 않을 경우에만 탐색 진행.
                    q.offer(new int[]{cur[0] + 1, point++, d});
                }
            }
        }
        return false;
    }
}