/**
프로그래머스 << 등굣길
오른쪽 또는 아래 방향으로 한 칸씩 이동
dp 메모이제이션
3차원으로 2인지 여부를 따로 저장
[i][j][0] == 둘다 올 수 있는 경우
[i][j][1] == 단방향일때 ? 한쪽만? 좌회전금지
현재 좌표를 기준으로 이전 좌표의 조건 여부 확인
2의 경우에는 오른쪽과 아래쪽 두가지의 경우의 수 따로 저장
**/
import java.util.*;
class Solution {
    int MOD = 20170805;
    public int solution(int m, int n, int[][] cityMap) {
        int answer = 0;
        
        int[][][] dp = new int[m+1][n+1][2];
        
        for(int i=1; i<=n; i++) {
            if(cityMap[0][i-1] == 1) break;
            dp[1][i][0] = 1;
        }
        for(int i=1; i<=m ;i++) {
            if(cityMap[i-1][0] == 1) break;
            dp[i][1][1] = 1;
        }
        for(int i=2; i<=m; i++) {
            for(int j=2; j<=n; j++) {
                if(cityMap[i-1][j-1] == 1) continue;
                
                if (i > 1 && cityMap[i - 2][j - 1] == 2) {
                    dp[i][j][1] = (dp[i][j][1] + dp[i - 1][j][1]) % MOD;
                } else {
                    dp[i][j][1] = (dp[i][j][1] + dp[i - 1][j][0] + dp[i - 1][j][1]) % MOD;
                }

                if (j > 1 && cityMap[i - 1][j - 2] == 2) {
                    dp[i][j][0] = (dp[i][j][0] + dp[i][j - 1][0]) % MOD;
                } else {
                    dp[i][j][0] = (dp[i][j][0] + dp[i][j - 1][0] + dp[i][j - 1][1]) % MOD;
                }
            }
        }
        
//         for(int i=0; i<=m; i++) {
//             for(int j=0; j<=n; j++) {
//                 System.out.print(dp[i][j][0]);
//             }
//             System.out.println();
//         }
//             System.out.println();
        
//         for(int i=0; i<=m; i++) {
//             for(int j=0; j<=n; j++) {
//                 System.out.print(dp[i][j][1]);
//             }
//             System.out.println();
//         }
        
        return (dp[m][n][0] + dp[m][n][1]) % MOD;
    }
}