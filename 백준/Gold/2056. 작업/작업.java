import java.io.*;
import java.util.*;

//BOJ_2056
public class Main {
    static int N;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        /**
         * 주의할 키워드
         * 이 작업들은 번호가 아주 예쁘게 매겨져 있어서, K번 작업에 대해 선행 관계에 있는(즉, K번 작업을 시작하기 전에 반드시 먼저 완료되어야 하는) 작업들의 번호는 모두 1 이상 (K-1) 이하
         * === 현재 i번째의 선행노드는 i보다 크지 않다.
         * **/

        N = Integer.parseInt(br.readLine());
        int[] dp = new int[N+1]; //dp[i]는 i번째 작업이 최종적으로 수행된 시간을 기록

        int res = 0;

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            dp[i] = time;

            for(int j=0; j<M; j++) {
                int next = Integer.parseInt(st.nextToken());

                dp[i] = Math.max(dp[i], dp[next] + time); //제일 늦게 끝난 선행 노드를 기점으로 계산
            }

            res = Math.max(res, dp[i]); //N번 노드와 앞선 노드 전부와 연관이 없으므로 매번 갱신
        }

        System.out.println(res);

    }
}