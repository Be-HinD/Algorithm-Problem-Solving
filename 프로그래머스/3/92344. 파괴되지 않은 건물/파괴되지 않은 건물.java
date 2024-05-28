import java.util.*;
class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        
        /**
        1차원 배열이라 생각하고 풀이
        **/
        int[][] sum = new int[board.length+1][board[0].length+1];
        
        for(int i=0; i<skill.length; i++) {
            int[] cur = skill[i];
            int degree = cur[5];
            if(cur[0] == 1) {
                //공격
                degree *= - 1;
            }
            
            sum[cur[1]][cur[2]] += degree;  //왼쪽 위
            sum[cur[1]][cur[4]+1] -= degree;    //오른쪽 위
            sum[cur[3]+1][cur[2]] -= degree;  //왼쪽 아래
            sum[cur[3]+1][cur[4]+1] += degree;  //오른쪽 아래
        }
        
        // for(int i=0; i<sum.length; i++) {
        //     System.out.println(Arrays.toString(sum[i]));
        // }
        // System.out.println();
        
        //누적합 계산 (위에서 아래로)
        for(int i=0; i<sum[0].length; i++) {
            for(int j=1; j<sum.length; j++) {
                sum[j][i] += sum[j-1][i];    
            }
        }
        //누적합 계산 (왼쪽에서 오른쪽으로)
        for(int i=0; i<sum.length; i++) {
            for(int j=1; j<sum[i].length; j++) {
                sum[i][j] += sum[i][j-1];
            }
        }
        
        // for(int i=0; i<sum.length; i++) {
        //     System.out.println(Arrays.toString(sum[i]));
        // }
        
        
        //파괴되지 않은 건물 개수 탐색
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[i].length; j++) {
                if(board[i][j] + sum[i][j] > 0) {
                    answer++;
                }
            }
        }
        
        return answer;
    }
}