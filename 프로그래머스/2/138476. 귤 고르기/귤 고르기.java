import java.util.*;
class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        
        /**
        HashMap에 크기 별 개수를 담고 개수가 가장 많은 순으로 정렬하면 되지않으려나
        **/
        int[] arr = new int[10000055];
        for(int i=0; i<tangerine.length; i++) {
            arr[tangerine[i]]++;
        }
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for(int i=0; i<arr.length; i++) {
            pq.offer(arr[i]);
        }
        int cnt = 0;
        while(cnt < k) {
            if(pq.isEmpty()) break;
            int idx = pq.poll();
            cnt += idx;
            answer++;
        }
        
        return answer;
    }
}