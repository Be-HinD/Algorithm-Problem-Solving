import java.util.Arrays;
class Solution {
    public int solution(int x, int y, int n) {
        
        int[] dp = new int[y+1];
        
        if(x == y) return 0;
        
        for(int i=x; i<=y; i++) {
            if(i != x && dp[i] == 0) {  //한번도 연산결과로 나온적없는 값들에 대해서
                // dp[i] = -1;
                continue;
            }
            
            if(i * 2 <= y) {
                if(dp[i*2] == 0) dp[i*2] += dp[i] + 1;
                else {
                    dp[i*2] = Math.min(dp[i*2], dp[i] + 1);
                }
            }
            if(i * 3 <= y) {
                if(dp[i*3] == 0) dp[i*3] += dp[i] + 1;
                else {
                    dp[i*3] = Math.min(dp[i*3], dp[i] + 1);
                }
            }
            
            if(i + n <= y) {
                if(dp[i+n] == 0) dp[i+n] += dp[i] + 1;
                else {
                    dp[i+n] = Math.min(dp[i+n], dp[i] + 1);
                }
            }
            
            
        }
        // System.out.println(Arrays.toString(dp));
        return dp[y] == 0 ? -1 : dp[y];
        
    }
}