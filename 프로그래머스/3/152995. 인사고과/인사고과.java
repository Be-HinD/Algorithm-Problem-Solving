import java.util.*;
class Solution {
    public int solution(int[][] scores) {
        
        int[] wanho = scores[0];
        
        Arrays.sort(scores, (o1,o2) -> Integer.compare(o2[0], o1[0]));
        
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1,o2) -> Integer.compare(o2,o1));
       
        int prevIdx = scores[0][0];
        int prevEval = 0; //prevIdx에 맞는 평가 최대값
        int idx = scores[0][0];
        int eval = scores[0][1];  //평가 점수의 현재 최대값
        pq.offer(prevIdx + eval);
        
        for(int i=1; i<scores.length; i++) {
            int[] cur = scores[i];
            if(prevIdx > cur[0]) {  //낮아지는 시점에 이전 최대값으로 갱신
                if(idx != cur[0]) {
                    prevIdx = idx;
                    prevEval = Math.max(prevEval, eval);
                }
            }
            
            eval = Math.max(eval, cur[1]);
            idx = cur[0];
            
            if(cur[1] < prevEval) {
                //과락
                if(cur[0] == wanho[0] && cur[1] == wanho[1]) return -1;
                continue;
            }
            
            pq.offer(cur[0] + cur[1]);
        }
        
        int rank = 1, pointer = 1;
        int prevRank = Integer.MAX_VALUE;
        while(!pq.isEmpty()) {
            int score = pq.poll();
            if(prevRank > score) {
                rank = pointer;
            }
            if(score == (wanho[0]+wanho[1])) return rank;
            pointer++;
        }
        return -1;
    }
}