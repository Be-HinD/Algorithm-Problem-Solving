import java.util.*;
class Solution {
    static class IDX implements Comparable<IDX> {
        int num;
        float ratio;
        public IDX(int num, float ratio) {
            this.num = num;
            this.ratio = ratio;
        }
         @Override
        public int compareTo(IDX o) {
            return Float.compare(o.ratio, this.ratio);
        }
    }
    static int res;
    public int solution(int[][] info, int n, int m) {
        /**
        1차 : dfs -> 7,9,10,26 시초
        2차 : 정렬
        B를 기준으로 오름차순 같다면 A가 더 높은것 기준
        B가 낮은것부터 B가 먹되, 같다면 A가 더 큰거부터
        실패 4개 -> 정답 확인해보니 비율로 정렬해야함...
        zzzz float / int 정렬 어케함;
        **/
        PriorityQueue<IDX> pq = new PriorityQueue<>();
        
        for(int i=0; i<info.length; i++) {
            float ratio = (float)info[i][0]/ (float)info[i][1];
            pq.offer(new IDX(i, ratio));
        }
        
        int origin = n;
        
        while(!pq.isEmpty()) {
            IDX cur = pq.poll();
            if(m - info[cur.num][1] <= 0) {
                n -= info[cur.num][0];
                continue;
            }
            m -= info[cur.num][1];
        }
        
        return n <= 0 ? -1 : origin - n;
    }
}