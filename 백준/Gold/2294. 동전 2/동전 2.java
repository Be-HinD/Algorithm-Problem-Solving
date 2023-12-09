import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ_2294 동전 2
public class Main {
    static int N, K;
    static int INF = 1000000000;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //동전 종류
        K = Integer.parseInt(st.nextToken()); //가치의 합

        arr = new int[N];
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[K+1];
        Arrays.fill(dp, INF);
        dp[0] = 0;

        for(int i=0; i<N; i++) {
            //각각의 동전의 경우에
            int coin = arr[i];
            for(int j=1;j<=K; j++) {
                if(j >= coin) { //동전의 가치보다 j가 클 경우
                    dp[j] = Math.min(dp[j], dp[j-coin]+1); //dp[j]는 현재 값과 현재 동전의 가치를 뺀 부분 해 둘 중 최소값으로 갱신
                }
            }
        }
        
        if(dp[K] >= INF) System.out.println(-1); //만들 수 없는 경우
        else System.out.println(dp[K]);

    }
}