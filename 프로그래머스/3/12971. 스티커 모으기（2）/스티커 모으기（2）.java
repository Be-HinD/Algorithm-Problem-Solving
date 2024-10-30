import java.util.*;
class Solution {
    public int solution(int sticker[]) {
        int res = 0;

        if(sticker.length == 1) return sticker[0];
        
        int[] dp = new int[sticker.length];
        
        dp[1] = sticker[0];
        for(int i=2; i<sticker.length; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + sticker[i-1]);
        }
        
        res = dp[dp.length-1];
        Arrays.fill(dp,0);
        
        dp[1] = sticker[1];
        for(int i=2; i<sticker.length; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + sticker[i]);
        }

        return Math.max(res, dp[dp.length-1]);
    }
}