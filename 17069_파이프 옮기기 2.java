import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] map;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        map = new int[N][N]; //맵 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        long[][][] dp = new long[N][N][3];
        dp[0][1][0] = 1; // initialize


        //접근법 : 각 자리 별 올 수 있는 경우의 수가 존재(가로일 경우 : 이전이 가로 또는 대각일 때만 올 수 있음)
        //각 자리 별로 올 수 있는 경우의 수를 탐색해가면서 메모이제이션
        // DP (0,0 0,1)은 고정값이니 0,2부터 메모이제이션 시작
        for (int i = 0; i < N; i++) {
            for (int j = 2; j < N; j++) {
                if(map[i][j]==1) continue; //벽을 만난 경우

                // 가로 (0) : i,j로 가로로 진입하려면 i,j-1의 좌표에서 가로거나 대각선이였어야 함.
                dp[i][j][0] = dp[i][j - 1][0] + dp[i][j - 1][2];

                if(i==0) continue; // 맨 윗줄이면 continue

                // 세로 (1)
                dp[i][j][1] = dp[i - 1][j][1] + dp[i - 1][j][2];

                if(map[i-1][j]==1 || map[i][j-1]==1) continue; //대각선으로 오려면 인접 4칸이 비어있어야 진입가능 하기에 벽이있다면 패스

                // 대각선 (2)
                dp[i][j][2] = dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2];
            }
        }

        for(int k=0; k<3; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    System.out.print(dp[i][j][k] + " ");
                }
                System.out.println();
            }
            System.out.println();
            System.out.println(k+1);
        }
        System.out.println(dp[N-1][N-1][0]+dp[N-1][N-1][1]+dp[N-1][N-1][2]);
    }
}
