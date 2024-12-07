import java.util.*;
class Solution {
    static int res;
    public int solution(int n, int[] money) {
        
        /**
        키워드
        방법의 경우의 수 (최소 개수 x)
        접근법
        보통 dp 풀이
        **/
        Arrays.sort(money);
        
        long[] dp = new long[n+1];
        
        //첫번째 화폐에 대한 초기화
        for(int i=0; i<=n; i++) {
            if(i % money[0] == 0) dp[i] = 1;
        }
        // System.out.println(Arrays.toString(dp));
        
        //현재 화폐에 대해서 1개 ~ 사용할 수 있는 최대 개수까지 썻을 때의 경우의 수 덧셈
        for(int i=1; i<money.length; i++) {
            int coin = money[i]; //현재 화폐 금액
            for(int j=coin; j<=n; j++) {
                dp[j] += dp[j-coin];
            }
        }
        // System.out.println(Arrays.toString(dp));
        return (int) dp[n] % 1000000007;
    }
}