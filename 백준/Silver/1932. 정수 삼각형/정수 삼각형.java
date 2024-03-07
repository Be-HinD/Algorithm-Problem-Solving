import java.io.*;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

//BOJ_1932 정수 삼각형
public class Main {
    static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        int[][] dp = new int[N][N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<i+1; j++) {
                dp[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] sum = new int[N][N];

        sum[N-1] = dp[N-1].clone();
        for(int i=N-2; i>=0; i--) {

            for(int j=0; j<=i; j++) {
                int cur = Math.max(sum[i+1][j], sum[i+1][j+1]);
                sum[i][j] = dp[i][j] + cur;
            }
        }

//        for(int i=0; i<N; i++) {
//            System.out.println(Arrays.toString(sum[i]));
//        }

        System.out.println(sum[0][0]);

    }
}