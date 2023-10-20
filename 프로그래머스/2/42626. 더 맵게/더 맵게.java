import java.util.*;
class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        //PQ로 최소힙 구현
        //PQ에서 두 개씩 poll 후 비교
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=0; i<scoville.length; i++) {
            pq.offer(scoville[i]);
        }
        boolean flag = false; //조건 체크 플래그
        while(pq.size() > 1) { //pq 사이즈가 1일 경우 런타임 에러
            int first = pq.poll();
            if(first >= K) { //최소 스코빌 지수가 K 이상이라면
                flag = true;
                break;
            }
            int second = pq.poll();
            int res = first + second*2;
            pq.offer(res);
            answer++;
        }
        int last = pq.poll();
        if(last >= K) return answer; //마지막 스코빌 지수가 K 이상이라면
        if(flag) return answer;
        else return -1;
    }
}