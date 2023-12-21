import java.io.*;
import java.util.*;

//BOJ_1535 안녕
public class Main {
    static int N;
    static int[] hp, happy;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine()); //사람 수

        hp = new int[N+1];
        happy = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            hp[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            happy[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[101][N+1];

        for(int i=1; i<101; i++) { //체력에 대해서
            for(int j=1; j<=N; j++) { //각 사람들을 탐색
                if(i > hp[j]) { //인사 가능할 때
                    dp[i][j] = Math.max(dp[i][j-1] , dp[i-hp[j]][j-1] + happy[j]);
                }
                else {
                    dp[i][j] = dp[i][j-1];
                }
            }
        }

        System.out.println(dp[100][N]);

    }
}