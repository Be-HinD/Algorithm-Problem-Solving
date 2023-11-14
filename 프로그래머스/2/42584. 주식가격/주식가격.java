import java.util.*;
class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        
        Stack<Integer> s = new Stack<>();
        for(int i=0; i<prices.length; i++) {
            s.push(prices[i]);
        }
        
        for(int i=0; i<prices.length-1; i++) {
            int idx = prices[i];
            int cnt = 0;
            int p = i+1;
            while(true) {
                
                cnt++;
                if(prices[p] < idx) {
                    answer[i] = cnt;
                    break;
                } 
                p++;
                if(p == prices.length) {
                    answer[i] = cnt;
                    break;
                }
            }
            
        }
        
        
        
        return answer;
    }
}