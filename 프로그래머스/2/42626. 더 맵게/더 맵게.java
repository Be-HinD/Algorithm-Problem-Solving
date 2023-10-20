import java.util.*;
class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        //0번부터 시작해서 +1 인덱스와 스코빌 지수 계산 후 비교
        //K이하일 시 +1인덱스에 해당 값 저장
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=0; i<scoville.length; i++) {
            pq.offer(scoville[i]);
        }
        boolean flag = false;
        while(pq.size() > 1) {
            int first = pq.poll();
            if(first >= K) {
                flag = true;
                break;
            }
            int second = pq.poll();
            int res = first + second*2;
            pq.offer(res);
            answer++;
        }
        int last = pq.poll();
        if(last >= K) return answer;
        if(flag) return answer;
        else return -1;
    }
}