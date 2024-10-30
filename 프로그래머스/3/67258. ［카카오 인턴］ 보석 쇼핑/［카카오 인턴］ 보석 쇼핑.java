import java.util.*;
class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        
        //SET자료구조로 종류의 개수 탐색
        //슬라이딩윈도우로 모든 종류를 구매할 수 있는 길이 탐색
        
        Set<String> set = new HashSet<>();
        for(String gem : gems) set.add(gem);
        
        int cnt = set.size();
        
        int l=0;
        
        Map<String, Integer> map = new HashMap<>();
        
        int minSize = gems.length;  //구간의 최소길이
        
        for(int i=0; i<gems.length; i++) {
            map.put(gems[i], map.getOrDefault(gems[i],0) + 1);
            if(map.size() != cnt) continue;
        
            while(map.get(gems[l]) > 1) {
                map.put(gems[l], map.get(gems[l])-1);
                l++;
            }
            
            if(i-l < minSize) {
                minSize = i-l;
                answer[0] = l+1;
                answer[1] = i+1;
            }
            
        }
        
        return answer;
    }
}