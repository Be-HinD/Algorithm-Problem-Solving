import java.util.*;
class Solution {
    public long solution(int n, int[] works) {
        
        /**
        시작한 시점 + 남은 일의 작업량^2
        N값을 이용하여 works의 평균값을 맞춰주는 작업.
        **/
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        
        //pq에 삽입하는 과정 == O(works.length)
        for(int i=0; i<works.length; i++) pq.offer(works[i]);
        
        //O(N)
        while(n-- > 0) {
            int idx = pq.poll();
            if(idx - 1 < 0) pq.offer(0);
            else pq.offer(idx - 1);
        }
        
        long res = 0;
        //O(works.length)
        while(!pq.isEmpty()) {
            long idx = (long) pq.poll();
            res += Math.pow(idx, 2);
        }
        return res;
    }
}