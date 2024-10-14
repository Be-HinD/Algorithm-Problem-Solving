import java.util.*;
class Solution {
    public int solution(int[] money) {
        int res = 0;
        
        int[] dp = new int[money.length];
        int[] dp2 = new int[money.length];
        
        dp[1] = money[0];
        for(int i=2; i<money.length; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + money[i-1]);
        }
        res = dp[dp.length-1];
        Arrays.fill(dp, 0);
        dp[1] = money[1];
        for(int i=2; i<money.length; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + money[i]);
        }
        
        
        return Math.max(res, dp[dp.length-1]);
    }
}