import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ_1799 비숍
public class Main {
    static int N, wCnt, bCnt, res;
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

        //1은 비숍이 놓일 수 없는 곳.
        //0이 있는 위치들에 대해서 비숍을 넣을 수 있는지 검증하면서 놓아야함.
        //dfs를 통해 0,0부터 시작해서 놓을수있으면 놓고, 안놓는 경우도 체크하면서 최대값 산출


        bDfs(0,0, 0);
        wDfs(0,1,0);
        System.out.println(wCnt + bCnt);
    }

    private static void bDfs(int x, int y, int cnt) {
        if(x>=N) {
            //행렬을 다 돌았을 경우
            wCnt = Math.max(wCnt, cnt);
            return;
        }

        //해당 자리에 놓을 수 있는지 검증
        if(map[x][y] == 1 && isValidate(x, y)) {
                //놓을 수 있는 위치라면 놓고 재귀
                map[x][y] = 2;
                if ((y+2) >= N) {
                    if(x % 2 == 0) bDfs(x + 1, 1, cnt + 1);
                    else bDfs(x + 1, 0, cnt + 1);
                }
                else bDfs(x, y + 2, cnt + 1);

                map[x][y] = 1; //백트래킹
        }
        if((y+2) >= N) {
            if(x % 2 == 0) bDfs(x + 1, 1, cnt);
            else bDfs(x + 1, 0, cnt);
        }
        else bDfs(x,y+2,cnt);

        //해당 자리에 놓았을 때
        //해당 자리에 놓지 않았을 때
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
                if(x % 2 == 0) wDfs(x + 1, 0, cnt + 1);
                else wDfs(x + 1, 1, cnt + 1);
            }
            else wDfs(x, y + 2, cnt + 1);

            map[x][y] = 1; //백트래킹
        }
        if((y+2) >= N) {
            if(x % 2 == 0) wDfs(x + 1, 0, cnt);
            else wDfs(x + 1, 1, cnt);
        }
        else wDfs(x,y+2,cnt);

        //해당 자리에 놓았을 때
        //해당 자리에 놓지 않았을 때
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