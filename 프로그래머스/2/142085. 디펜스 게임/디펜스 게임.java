import java.util.*;
class Solution {
    static int res;
    public int solution(int n, int k, int[] enemy) {

        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        
        for(int i=0; i<enemy.length; i++) {
            if(n<enemy[i] && k == 0) {
                break;
            }
            pq.offer(enemy[i]);
            
            if(n < enemy[i]) {
                //무적권을 써야할 경우
                n += pq.poll();
                k--;
            }
            
            n -= enemy[i];
            res++;
        }
        
        
        
        return res;
    }
}