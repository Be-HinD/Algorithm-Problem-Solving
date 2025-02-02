import java.util.*;
class Solution {
    public int[] solution(int[] arr, int divisor) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for(int idx : arr) {
            if(idx % divisor == 0) pq.offer(idx);
        }
        
        if(pq.isEmpty()) return new int[]{-1};
        
        int[] res = new int[pq.size()];
        int p = 0;
        while(!pq.isEmpty()) res[p++] = pq.poll();
        
        return res;
    }
}