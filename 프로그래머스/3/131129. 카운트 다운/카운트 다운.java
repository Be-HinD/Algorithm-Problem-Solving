/**
키워드
최소한의 다트로 0점을 만드는 게 가장 중요
그러한 방법이 여러가지가 있다면 "싱글" 또는 "불"을 최대한 많이 던지는 방법

접근법
받을 수 있는 점수의 종류는 정해져있음.
처음 target점수에서 bfs탐색을 통해 0에 도달하는 가짓수 중 최적해를 탐색
PQ를 통해 같은 횟수에 대해서는 싱글,불 카운트 내림차순으로 추출
**/

import java.util.*;
class Solution {
    static class DART implements Comparable<DART> {
        int score;
        int cnt;
        int spCnt;
        public DART(int score, int cnt, int spCnt) {
            this.score = score;
            this.cnt = cnt;
            this.spCnt = spCnt;
        }
        @Override
        public int compareTo(DART o) {
            if(this.cnt == o.cnt) {
                return o.spCnt - this.spCnt;
            }
            return this.cnt - o.cnt;
        }
    }
    
    static List<Integer> list;
    public int[] solution(int target) {

        Set<Integer> set = new TreeSet<>((o1,o2) -> o2 - o1);
        for(int i=1; i<=20; i++) {
            set.add(i);
            set.add(i*2);
            set.add(i*3);
        }
        
        list = new ArrayList<>();
        for(int idx : set) {
            list.add(idx);
        }
        
        DART res = bfs(target);
        
        return new int[]{res.cnt, res.spCnt};
    }
    
    
    static DART bfs(int start) {
        PriorityQueue<DART> pq = new PriorityQueue<>();
        pq.offer(new DART(start, 0, 0));
        boolean[] v = new boolean[start+1];
        v[start] = true;
        
        //그리디 사고
        //50보다 크면 50위의 수들만 이용해서 줄여나가기.
        while(!pq.isEmpty()) {
            DART cur = pq.poll();
            
            if(cur.score == 0) return cur;
            
            if(cur.score - 50 >= 0 && !v[cur.score-50]) {
                pq.offer(new DART(cur.score-50, cur.cnt+1, cur.spCnt+1)); //불
                v[cur.score-50] = true;
            }
            
            for(int idx : list) {
                int next = cur.score - idx;
                if(next < 0) continue;
                if(v[next]) continue;
                if(idx <= 20) {
                    pq.offer(new DART(next, cur.cnt+1, cur.spCnt+1));
                }
                else {
                    pq.offer(new DART(next, cur.cnt+1, cur.spCnt));
                }
                    v[next] = true;
                
            }
        }
        return new DART(-1,-1,-1);
    }
}