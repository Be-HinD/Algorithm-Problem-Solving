import java.util.*;
class Solution {
    public int solution(int[] order) {
        int answer = 0;
        
        /**
        보조 컨테이너 벨트 == Stack Lifo
        메인 순회 현재 순번 x == 보조 이동
        **/
        Stack<Integer> st = new Stack<>();
        int cur = 0;
        int p = 0;
        
        for(int i=1; i<=order.length; i++) {
            st.push(i);
            
            while(!st.isEmpty() && st.peek().intValue() == order[p]) {
                answer++;
                p++;
                st.pop();
            }
        }
        
        
        return answer;
    }
}