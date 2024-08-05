import java.util.*;
class Solution {
    public int[] solution(String[] gems) {
        
        //1번에서부터 다 포함하는 지점 탐색
        //1번부터 하나씩 빼서 조건 만족하는 구간 탐색
        Set<String> condition = new HashSet<>();
        //카운팅 배열 쓰고 싶은데
        for(int i=0; i<gems.length; i++) {
            if(!condition.contains(gems[i])) {
                condition.add(gems[i]);
            }
        }
        
        //탐색하면서 set 삭제
        Map<String, Integer> map = new HashMap<>();
        
        int l = 0, left = 0, right = 0;
        int leng = Integer.MAX_VALUE;
        for(int i=0; i<gems.length; i++) {
            
            map.put(gems[i], map.getOrDefault(gems[i], 0) + 1);
            
            while(map.get(gems[l]) > 1) {
                map.put(gems[l], map.get(gems[l]) - 1);
                l++;
            }
            
            if(map.size() == condition.size() && (i - l) < leng) {
                leng = i - l;
                left = l;
                right = i;
            }
        }
        
                
        return new int[]{left+1, right+1};
    }
}