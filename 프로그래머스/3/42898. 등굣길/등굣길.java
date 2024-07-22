import java.util.*;
class Solution {
    static final int mod = 1000000007;
    static int[][] map;
    public int solution(int m, int n, int[][] puddles) {
        
        /**
        dp[i][j] == 해당 지점에서 도착지점까지 갈 수 있는 경우의 수.
        bfs 돌려서 도착하는 경우의 수 체크하면 되지않나?
        **/
        map = new int[n+1][m+1];
        
        for(int i=0; i<puddles.length; i++) {
            int[] water = puddles[i];
            map[water[1]][water[0]] = -1;
        }
        
        map[1][1] = 1;
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=m; j++) {
                if(map[i][j] == -1) continue;
                if(map[i-1][j] > 0) map[i][j] += map[i-1][j] % mod;
                if(map[i][j-1] > 0) map[i][j] += map[i][j-1] % mod;
            }
        }
        
        
        return map[n][m] % mod;
    }
}