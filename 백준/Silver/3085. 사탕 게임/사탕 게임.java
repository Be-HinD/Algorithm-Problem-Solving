import java.io.*;
import java.util.*;

//BOJ_3085 사탕 게임
public class Main {
    static int N, res;
    static int[] dx = new int[]{0,0,1,-1};
    static int[] dy = new int[]{1,-1,0,0};
    static char[][] map;
    static int[] candi;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(br.readLine());

        map = new char[N][N];
        for(int i=0; i<N; i++) {
            String input = br.readLine();
            for(int j=0; j<N; j++) {
                char idx = input.charAt(j);
                map[i][j] = idx;
            }
        }

        //원본 맵에서 카운팅하면 틀림
        //사탕 먹을 수 있는거 연속부분임 ㅋ


        dfs(0,0);

        System.out.println(res);


    }

    /*
    *현재 (x,y)좌표를 기준으로 상하좌우에 다른 사탕이 있다면 자리교체 후 개수 탐색
    *
    * */

    private static void dfs(int x, int y) {
        if(x >= N) return;
        for(int i=0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(!rangeValidation(nx, ny)) continue;
            if(map[x][y] != map[nx][ny]) {
                swap(x,y,nx,ny);
                res = Math.max(res, checkCount());
                swap(x,y,nx,ny);
            }
        }

        if(y+1 >= N) {
            dfs(x+1, 0);
        }
        else dfs(x, y+1);
    }

    private static int checkCount() {
        int cnt = 0;
        //행 탐색
        for(int i=0; i<N; i++) {
            int maxCol = 0;
            int maxRow = 0;
            int col = 1;     //열 탐색
            int row = 1;   //행 탐색

            for(int j=1; j<N; j++) {
                if(map[i][j] == map[i][j-1]) col++;
                else {
                    maxCol = Math.max(maxCol, col);
                    col = 1;
                }

                if(map[j][i] == map[j-1][i]) row++;
                else {
                    maxRow = Math.max(maxRow, row);
                    row = 1;
                }
            }
            maxCol = Math.max(maxCol, col);
            maxRow = Math.max(maxRow, row);

            cnt = Math.max(cnt, Math.max(maxCol, maxRow));
        }



        return cnt;
    }

    private static boolean rangeValidation(int x, int y) {
        if(x<0 || y<0 || x>=N || y>=N) return false;
        return true;
    }

    private static void swap(int x, int y, int nx, int ny) {
        char idx = map[x][y];
        map[x][y] = map[nx][ny];
        map[nx][ny] = idx;
    }
}