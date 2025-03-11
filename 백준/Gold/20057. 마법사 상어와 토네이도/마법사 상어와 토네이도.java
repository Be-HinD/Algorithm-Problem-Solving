import java.io.*;
import java.util.*;

//BOJ_20057
public class Main {
    static int n, res;
    static int[][] map;
    static int[] dx = new int[]{0,1,0,-1};
    static int[] dy = new int[]{-1,0,1,0};
    static int[] edgeX = new int[]{1,-1,-1,1};
    static int[] edgeY = new int[]{-1,-1,1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        /**
         * 이동하는 중심지를 기준으로 방향을 알고 있다면
         * 비율 계산은
         * **/

        n = Integer.parseInt(br.readLine());

        map = new int[n][n];

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //달팽이 회전
        int x = n/2, y = n/2;   //중심점
        int step = 1;

        while(step < n) {
            //왼쪽 회전
            for(int i=0; i<step; i++) {
                y--;
                //로직
                tornado(x,y,0);
            }

            //아래쪽 회전
            for(int i=0; i<step; i++) {
                x++;
                //로직
                tornado(x,y,1);
            }

            step++;
            //오른쪽 회전
            for(int i=0; i<step; i++) {
                y++;
                //로직
                tornado(x,y,2);
            }

            //위쪽 회전
            for(int i=0; i<step; i++) {
                x--;
                //로직
                tornado(x,y,3);
            }

            step++;
        }

        //왼쪽 회전 -> 0,0에서 멈추도록
        for(int i=0; i<step-1; i++) {
            y--;
            //로직
            tornado(x,y,0);
        }

        System.out.println(res);

    }

    static int[] rate = new int[]{7, 2};
    static int[] edgeRate = new int[]{10,10,1,1};
    static void tornado(int x, int y, int d) {
        int dust = map[x][y];  // 현재 모래의 절대 양
        int remain = map[x][y];  // a자리에 들어갈 나머지 양

        //진행 방향 기준 오른쪽 비율 계산
        int dist = (d+3) % 4;
        for(int i=1; i<=2; i++) {
            int nx = x + dx[dist] * i;
            int ny = y + dy[dist] * i;
            int cnt = (dust * rate[i-1])/100; //cnt : 이동하는 모래 값

            remain -= cnt;
            if(isRange(nx,ny)) {  //범위 밖
                res += cnt;
                continue;
            }
            map[nx][ny] += cnt;
        }

        //진행 방향 기준 왼쪽 비율 계산
        dist = (d+1) % 4;
        for(int i=1; i<=2; i++) {
            int nx = x + dx[dist] * i;
            int ny = y + dy[dist] * i;
            int cnt = (dust * rate[i-1])/100;

            remain -= cnt;
            if(isRange(nx,ny)) {
                res += cnt;
                continue;
            }
            map[nx][ny] += cnt;
        }

        //진행 방향 기준 대각선 계산
        dist = d % 2 == 0 ? d : (d+2) % 4;  //짝수 : 인덱스 그대로, 홀수 : +2

        for(int i=0; i<4; i++) {
            int nx = x + edgeX[(dist + i) % 4];
            int ny = y + edgeY[(dist + i) % 4];
            int cnt = (dust * edgeRate[i])/100;
            
            remain -= cnt;
            if(isRange(nx,ny)) {
                res += cnt;
                continue;
            }
            map[nx][ny] += cnt;
        }

        //진행 방향 기준 직진(2칸) 계산
        int nx = x + (dx[d]*2);
        int ny = y + (dy[d]*2);

        int cnt = (dust * 5)/100;

        remain -= cnt;
        if(isRange(nx,ny)) {
            res += cnt;
        }
        else {
            map[nx][ny] += cnt;
        }

        //알파 자리
        nx = x + dx[d];
        ny = y + dy[d];

        if(isRange(nx,ny)) {
            res += remain;
        }
        else {
            map[nx][ny] += remain;
        }

        map[x][y] = 0;
    }

    //범위 밖 = true
    static boolean isRange(int x, int y) {
        return x<0 || y<0 || x>=n || y>=n;
    }
}