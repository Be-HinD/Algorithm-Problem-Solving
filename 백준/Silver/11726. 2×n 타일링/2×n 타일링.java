import java.io.*;

//BOJ_11726 2*n 타일링
public class Main {
    static int N;
    static int[] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        dp = new int[N+1];

        if(N == 1) {    //99% 1 예외처리
            System.out.println(1);
            return;
        }

        dp[1] = 1;
        dp[2] = 2;

        for(int i=3; i<=N; i++) {
            dp[i] = (dp[i-1] + dp[i-2]) % 10007;
        }

        System.out.println(dp[N]);
    }
}