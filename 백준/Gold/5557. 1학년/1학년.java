import java.io.*;
import java.util.*;

//5557
public class Main {
    static int N;
    static long res;
    static int[] arr;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        /**
         * 접근법
         * 등식의 개수
         * N-1번째 수는 정답
         * 완전탐색 == 2^100
         * 결과값이 20까지밖에 안나옴.
         * i는 i-1을 더한값과, 뺀값
         * **/

        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        long[][] dp = new long[N][21];

        dp[0][arr[0]] = 1;

        int plus, minus;
        for(int i=1; i<N-1; i++) {
            for(int j=0; j<=20; j++) {
                plus = j + arr[i];
                minus = j - arr[i];

                if(0<=plus && plus<21) {
                    dp[i][plus] += dp[i-1][j];
                }
                if(0<=minus && minus<21) {
                    dp[i][minus] += dp[i-1][j];
                }
            }
        }

        System.out.println(dp[N-2][arr[N-1]]);

    }
}