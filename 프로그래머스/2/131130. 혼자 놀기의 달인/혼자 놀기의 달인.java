import java.util.*;
class Solution {
    static boolean[] v;
    public int solution(int[] cards) {
        
        /**
        접근법
        cards <= 100 -> O(N^4) 까지 가능
        1번부터 N번까지 상자를 순차적으로 열어서 최대값 2개를 탐색
        **/
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        v = new boolean[cards.length];
        for(int i=0; i<cards.length; i++) {
            //i번 상자를 열었을 때 얻을 수 있는 최대 상자 개수 탐색
            int cnt = openBox(i, cards);
            pq.offer(cnt);
        }
        
        if(pq.size() < 2) return 0;
        
        return pq.poll() * pq.poll();
    }
    
    static int openBox(int idx, int[] arr) {
        int res = 0;
        while(!v[idx]) {
            res++;
            v[idx] = true;
            idx = arr[idx]-1;
        }
        
        return res;
    }
}