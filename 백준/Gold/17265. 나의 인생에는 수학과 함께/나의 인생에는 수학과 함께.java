import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ_17265
public class Main {
    static int N, minDist, minRes, maxRes;
    static char[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        /**
         * 키워드
         *  최댓값과 최솟값
         *  집 (1, 1)에서 학교 (N, N)까지 최단거리로 이동
         *  오른쪽과 아래쪽으로만 이동
         *  접근법
         *  임의의 최단거리 S(2N -1)와 동일한 모든 경로에서 최대값 최소값을 갱신
         *  모든 경로 탐색 == DFS
         * **/

        minRes = Integer.MAX_VALUE;
        maxRes = Integer.MIN_VALUE;

        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = st.nextToken().charAt(0);
            }
        }

        minDist = N*2 - 1;

        dfs(0,0,'+',0, 0);

        System.out.println(maxRes + " " + minRes);
    }
    private static void dfs(int x, int y, char prevOper, int sum, int cnt) {
        if(cnt >= minDist) return;  //최단거리 백트래킹

        if((x%2==0&&y%2==0) || (x%2!=0&&y%2!=0)) {
            //숫자인 경우
            if(prevOper == '+') {
                sum += map[x][y] - '0';
            }
            else if(prevOper == '-') {
                sum -= map[x][y] - '0';
            }
            else {
                sum *= map[x][y] -'0';
            }
        }
        else {
            //연산자인 경우
            prevOper = map[x][y];
        }

        if(x==N-1 && y==N-1) {
            maxRes = Math.max(maxRes, sum);
            minRes = Math.min(minRes, sum);
            return;
        }

        //오른쪽, 아래
        if(x+1<N) dfs(x+1, y, prevOper, sum, cnt+1);
        if(y+1<N) dfs(x, y+1, prevOper, sum, cnt+1);

    }
}