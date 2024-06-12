import java.util.*;
class Solution
{
    public int solution(String s)
    {
        
        Stack<Character> stack = new Stack<>();
        
        for(int i=0; i<s.length(); i++) {
            char idx = s.charAt(i);
            if(stack.isEmpty()) {
                stack.push(idx);
                continue;
            }
            if(stack.peek() == idx) {
                stack.pop();
            }
            else {
                stack.push(idx);
            }
        }
        
        if(stack.isEmpty()) {
            return 1;
        }
        return 0;
    }
}