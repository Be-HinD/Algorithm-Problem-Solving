import java.util.*;
class Solution
{
    public int solution(int []A, int []B)
    {
        int answer = 0;
        
        PriorityQueue<Integer> aq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        
        PriorityQueue<Integer> bq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return -(o1 - o2);
            }
        });
        
        for(int i=0; i<A.length; i++) {
            aq.offer(A[i]);
            bq.offer(B[i]);
        }
        
        while(!aq.isEmpty()) {
            int a = aq.poll();
            int b = bq.poll();
            answer += a*b;
        }
        
        return answer;
    }
}