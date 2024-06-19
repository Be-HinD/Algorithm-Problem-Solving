import java.util.*;
class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        
        /**
        스택을 통해 비교
        **/
        Stack<int[]> st = new Stack<>();
        
        for(int i=0; i<numbers.length; i++) {
            int idx = numbers[i];
            int p = i-1;
            while(!st.isEmpty()) {
                int[] cur = st.peek();
                if(cur[1] < idx) {
                    answer[cur[0]] = idx;
                    st.pop();
                }
                else break;
            }
            st.push(new int[]{i, idx});
        }
        
        for(int i=0; i<answer.length; i++) {
            if(answer[i] == 0) answer[i] = -1;
        }
        return answer;
    }
}