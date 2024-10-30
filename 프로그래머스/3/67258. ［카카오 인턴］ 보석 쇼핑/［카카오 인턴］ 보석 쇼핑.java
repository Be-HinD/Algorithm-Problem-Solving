import java.util.*;
class Solution {
    public int[] solution(String[] gems) {
        int[] answer = {};
        
        //SET자료구조로 종류의 개수 탐색
        //슬라이딩윈도우로 모든 종류를 구매할 수 있는 길이 탐색
        
        Set<String> set = new HashSet<>();
        for(String gem : gems) set.add(gem);
        
        int cnt = set.size();
        
        int l=0;
        
        Map<String, Integer> map = new HashMap<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> 
                                                      {if(o1[2] == o2[2]) return o1[0] - o2[0];
                                                                    return o1[2] - o2[1];});
        int minSize = gems.length;
        for(int i=0; i<gems.length; i++) {
            map.put(gems[i], map.getOrDefault(gems[i],0) + 1);
            if(map.size() != cnt) continue;
        
            while(map.get(gems[l]) > 1) {
                map.put(gems[l], map.get(gems[l])-1);
                l++;
            }
            
            if(i-l < minSize) {
                minSize = i-l;
                pq.offer(new int[]{l+1,i+1, i-l});
            }
            
        }
        
        int[] res = pq.poll();
        return new int[]{res[0], res[1]};
    }
}