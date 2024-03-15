import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

//BOJ_7570 줄 세우기
public class Main {
    static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());


        /*
         * 접근법
         * 어린이 수 : 최대 1,000,000
         * 최장 증가 수열을 찾으라고?
         * */

        int[] dp = new int[N+1];

        st = new StringTokenizer(br.readLine());

        for(int i=0; i<N; i++) {
            int idx = Integer.parseInt(st.nextToken());
            dp[idx] = dp[idx-1] + 1;
        }

        int max = 0;
        for(int i=0; i<=N; i++) {
            max = Math.max(max, dp[i]);
        }
//        System.out.println(Arrays.toString(dp));

        System.out.println(N - max);

    }
}