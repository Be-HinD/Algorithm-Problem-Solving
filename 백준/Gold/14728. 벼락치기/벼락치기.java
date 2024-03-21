import java.io.*;
import java.util.*;

//BOJ_14728 벼락치기
public class Main {
    static int N, T;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   //단원 개수
        T = Integer.parseInt(st.nextToken());   //남은 시간
        int[] dp = new int[T+1];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            for(int j=dp.length-1; j>=t; j--) {
                dp[j] = Math.max(dp[j], dp[j-t] + s);
            }
        }
        System.out.println(dp[T]);
    }
}