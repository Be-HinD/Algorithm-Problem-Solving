import java.util.*;
class Solution {
    public int[] solution(int n, int s) {
        int[] res = new int[n];
        
        if(s/n == 0) return new int[]{-1};  //예외처리
    
        int idx = 0;
        while(n != 0) {
            int cur = s / n;
            res[idx++] = cur;
            s -= cur;
            n--;
        }
        //
        
        // 5 5 
        
        
        
        return res;
    }
}