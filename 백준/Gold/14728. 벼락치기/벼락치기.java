import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ_14728
public class Main {
    static int N, T;
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        //반례부터 만들지마, 어떻게 풀지 접근법부터 생각해.
        //대충 생각하고 구현하지마, 충분한 검증부터 하고 코드작성해.

        arr = new int[N+1][2];
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[10055][N+1];

        //0-1 냅색
        for(int i=1; i<=N; i++) {
            for(int j=1; j<10055; j++) {
                if(j - arr[i][0] >= 0) { //i아이템을 넣을 수 있음.
                    dp[j][i] = Math.max(dp[j][i-1], dp[j-arr[i][0]][i-1] + arr[i][1]);
                }
                else dp[j][i] = dp[j][i-1];
            }
        }

        System.out.println(dp[T][N]);

    }
}