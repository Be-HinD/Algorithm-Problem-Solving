import java.util.*;
class Solution {
    public String solution(String number, int k) {
        String answer = "";
        
        /**
        스택 풀이 참조
        **/
        int max = k;
        
        Stack<Character> stack = new Stack<>();
        
        for(char idx : number.toCharArray()) {
            // System.out.println(idx);
            while(!stack.isEmpty() && stack.peek() < idx && k > 0) {
                k--;
                stack.pop();
            }
            
            stack.add(idx);
        }
        int cnt = 0;
        for(char idx : stack) {
            answer += idx;
            cnt++;
            if(cnt == number.length() - max) break;
        }
        
        return answer;
    }
}