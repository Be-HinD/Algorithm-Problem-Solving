import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ_1799 비숍
public class Main {
    static int N, wCnt, bCnt;
    static int[] dx = new int[]{-1, -1, 1, 1}; //대각선 방향벡터
    static int[] dy= new int[]{-1, 1, -1, 1};
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        for(int i=0; i<N; i++) { //맵 입력
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //체스판 위에서 비숍이 움직일 수 있는 범위는 대각선
        //0,0 ~ N,N까지 놓고, 안놓고의 분기로 가지치기를 전부하면 4% 시간초과 발생
        //방문배열로 메모이제이션 시 128mb 메모리 초과 발생
        //분할정복 느낌의 아이디어가 조금 필요함.
        //홀수좌표와 짝수좌표의 비숍은 서로 만나지 못함.
        //두 가지의 경우에 대해 두 번의 DFS를 통해 최대값을 찾고 더한 결과를 출력함으로써 풀이.


        bDfs(0,0, 0);
        wDfs(0,1,0);
        System.out.println(wCnt + bCnt);
    }

    private static void bDfs(int x, int y, int cnt) {
        if(x>=N) {
            //탐색이 끝났을 경우 비교
            wCnt = Math.max(wCnt, cnt);
            return;
        }

        //해당 자리에 놓을 수 있는지 검증
        if(map[x][y] == 1 && isValidate(x, y)) {
                //놓을 수 있는 위치라면 놓고 재귀
                map[x][y] = 2;
                if ((y+2) >= N) {
                    //행이 바뀌어야 하고, 행의 홀수 짝수에 따라 시작 y좌표가 변경
                    if(x % 2 == 0) bDfs(x + 1, 1, cnt + 1);
                    else bDfs(x + 1, 0, cnt + 1);
                }
                else bDfs(x, y + 2, cnt + 1);

                map[x][y] = 1; //백트래킹
        }

        //해당 자리에 놓지 않는 경우
        if((y+2) >= N) {
            if(x % 2 == 0) bDfs(x + 1, 1, cnt);
            else bDfs(x + 1, 0, cnt);
        }
        else bDfs(x,y+2,cnt);
    }

    private static void wDfs(int x, int y, int cnt) {
        if(x>=N) {
            //행렬을 다 돌았을 경우
            bCnt = Math.max(bCnt, cnt);
            return;
        }

        //해당 자리에 놓을 수 있는지 검증
        if(map[x][y] == 1 && isValidate(x, y)) {
            //놓을 수 있는 위치라면 놓고 재귀
            map[x][y] = 2;
            if ((y+2) >= N) {
                //행이 바뀌어야 하고, 행의 홀수 짝수에 따라 시작 y좌표가 변경
                if(x % 2 == 0) wDfs(x + 1, 0, cnt + 1);
                else wDfs(x + 1, 1, cnt + 1);
            }
            else wDfs(x, y + 2, cnt + 1);
            map[x][y] = 1; //백트래킹
        }

        //해당 자리에 놓지 않는 경우
        if((y+2) >= N) {
            if(x % 2 == 0) wDfs(x + 1, 0, cnt);
            else wDfs(x + 1, 1, cnt);
        }
        else wDfs(x,y+2,cnt);
    }

    private static boolean isValidate(int x, int y) {
        //비숍이 x,y 자리에 놓을 수 있는 곳인지 체크
        for(int i=0; i<4; i++) {
            int nx = x;
            int ny = y;
            while(true) {
                nx += dx[i];
                ny += dy[i];
                if(nx<0 || ny<0 || nx>=N || ny>=N) break;
                if(map[nx][ny] == 2) return false;
            }
        }
        return true;
    }
}