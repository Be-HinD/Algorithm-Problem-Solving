import java.io.*;
import java.util.*;

//BOJ_1106
public class Main {
    static int c, n;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        /**
         * 열 == 인원
         * 행 == 도시
         * i,j == 해당 도시를 선택해서 채울 수 있는 최소 비용
         *
         * **/

        c = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        int[][] cities = new int[n][2];
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            cities[i][0] = Integer.parseInt(st.nextToken());
            cities[i][1] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[c+100];

        Arrays.fill(dp, 100055);
        dp[0] = 0;

        for(int i=0; i<n; i++) {
            int[] city = cities[i];
            for(int j=1; j<dp.length; j++) {
                if(j-city[1] < 0) continue;
                dp[j] = Math.min(dp[j-city[1]] + city[0], dp[j]);
            }
        }

        int res = Integer.MAX_VALUE;
        for(int i=c; i<dp.length; i++) {
            res = Math.min(res, dp[i]);
        }

        System.out.println(res);

    }
}