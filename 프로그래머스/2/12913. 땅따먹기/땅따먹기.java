import java.util.*;
class Solution {
    int solution(int[][] land) {
        int res = 0;

    
        /**
        400,000 DFS ? ㅋㅋ
        BFS ㄱㄱ == 메모리초과
        dp ㄱㄱ
        **/
        int[][] dp = new int[land.length][4];
        
        for(int i=0; i<4; i++) {
            //초기화
            dp[0][i] = land[0][i];
        }
        
        for(int i=1; i<land.length; i++) {
            for(int j=0; j<4; j++) {
                for(int k=0; k<4; k++) {
                    if(j == k) continue;
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][k] + land[i][j]);
                }
            }
        }
        
        for(int i=0; i<4; i++) {
            res = Math.max(res, dp[land.length-1][i]);
        }
        
        return res;
    }
}