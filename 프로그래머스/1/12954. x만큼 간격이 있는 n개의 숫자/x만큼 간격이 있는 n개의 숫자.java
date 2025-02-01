import java.util.*;
class Solution {
    public long[] solution(int x, int n) {
        long[] answer = new long[n];
        Queue<Long> q = new ArrayDeque<>();
        
        long idx = x;
        while(q.size() < n) {
            q.offer(idx);
            idx += x;
        }
        
        for(int i=0; i<n; i++) answer[i] = q.poll();
        return answer;
    }
}