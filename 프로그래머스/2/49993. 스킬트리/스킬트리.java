import java.util.*;
class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        
        /**
        각 원소 별 순회
        Map에 포함되는지 체크 방ㅁ
        **/
        Map<Character, Integer> map = new HashMap<>();
        
        for(int i=0; i<skill.length(); i++) {
            char idx = skill.charAt(i);
            map.put(idx, i+1);
        }
        
        for(int i=0; i<skill_trees.length; i++) {
            String cur = skill_trees[i];
            boolean flag = true;
            boolean[] v = new boolean[map.size()+1];
            
            for(int j=0; j<cur.length(); j++) {
                char idx = cur.charAt(j);
                if(map.get(idx) != null) {
                    int p = map.get(idx);
                    
                    if(p == 1) {
                        v[p] = true;
                        continue;
                    }
                    if(!v[p-1]) {
                        flag = false;
                        break;
                    }
                    v[p] = true;
                }
            }
            if(flag) {
                answer++;
            }
        }
        
        
        return answer;
    }
}