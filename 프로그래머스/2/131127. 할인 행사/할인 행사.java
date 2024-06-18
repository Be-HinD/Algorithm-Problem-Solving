import java.util.*;
class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        
        /**
        그리디하게 풀이하려면 discount 1부터 100,000까지 want 개수만큼 반복
        100,000 * 10(일) == 1,000,000번
        **/
        
        for(int i=0; i<discount.length - 9; i++) {
            Map<String, Integer> list = new HashMap<>();
            for(int j=0; j<want.length; j++) {
                String key = want[j];
                int value = number[j];
                list.put(key, value);
            }
            
            int p = i;
            for(int j=0; j<10; j++) {
                String key = discount[p++];
                if(list.containsKey(key)) {
                    list.put(key, list.get(key) - 1);
                }
            }
            
            //확인 (Map 순회)
            boolean flag = true;
            for(Map.Entry<String, Integer> entry : list.entrySet()) {
                int value = entry.getValue();
                if(value > 0) {
                    flag = false;
                    break;
                }
            }
            
            if(flag) answer++;
        }
        
        return answer;
    }
}