import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ_9084 동전
public class Main {
    static int N, T, target;
    static int[] coins, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuffer sb = new StringBuffer();

        //주 문제 : N개의 동전으로 target금액을 만들 수 있는 경우의 수
        //부분 문제 : i번째의 동전으로 j금액을 만들 수 있는가.
        T = Integer.parseInt(br.readLine());
        for(int tc=0; tc<T; tc++) {
            N = Integer.parseInt(br.readLine()); //동전의 개수
            coins = new int[N+1];

            //동전 금액 입력
            st = new StringTokenizer(br.readLine());
            for(int i=1; i<=N; i++) coins[i] = Integer.parseInt(st.nextToken());

            target = Integer.parseInt(br.readLine()); //목표 금액

            dp = new int[target+1]; //금액 별 만들 수 있는 경우의 수 누적합을 위한 배열 선언

            for(int i=1; i<=N; i++) {
                int coin = coins[i];
                for(int j=1; j<=target; j++) {
                    //각 금액에 대해서 각각의 코인을 사용해서 만들 수 있는 경우의 수를 누적
                    if(j-coin > 0) dp[j] += dp[j-coin]; //j금액에서 현재 코인의 금액을 뺀 값이 양수라면 만들고 남는 금액이 있으니 dp[j-coin]의 부분문제 해를 이용해 누적
                    else if(j-coin == 0) dp[j]++; //딱코로 만들 수 있다면 경우의 수 1추가
                }
            }

            System.out.println(dp[target]);
        }
    }
}