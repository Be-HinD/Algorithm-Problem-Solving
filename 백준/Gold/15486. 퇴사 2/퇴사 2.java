import java.io.*;
import java.util.*;

//BOJ_15486
public class Main {
    static int n, res;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        /**
         * 순차적으로 수행 가능. (3일차 수행 후 1일차 수행불가)
         * dfs(완탐) -> 1,500,000^2 x
         * 그리디 -> 기간/금액 기준이 안잡힘 x
         * DP -> i일차에 얻을 수 있는 최대 금액 갱신
         *
         * **/
        n = Integer.parseInt(br.readLine());

        int[][] arr = new int[n][2];
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            arr[i][0] = t;
            arr[i][1] = p;
        }

        int[] dp = new int[n+1];
        for(int i=n-1; i>=0; i--) {
            //i일차 수행 가능 여부
            if(arr[i][0] + i > n) {
                dp[i] = dp[i+1];
                continue;
            }

            //현재 상담을 진행한다는 건 현재 상담 기간 + 현재 날짜부터 상담을 할 수 있다는 것
            dp[i] = Math.max(arr[i][1] + dp[i+arr[i][0]], dp[i+1]);
        }

        System.out.println(dp[0]);

    }
}