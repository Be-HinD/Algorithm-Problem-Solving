import java.util.*;
class Solution {

    static Map<String, Integer> hs = new HashMap<String, Integer>();
    public int solution(String[][] clothes) {
        
        
        for(int i=0; i<clothes.length; i++) {
            if(hs.containsKey(clothes[i][1])) {
                //해당 종류의 옷이 이미 있을 경우
                hs.put(clothes[i][1], hs.get(clothes[i][1]) + 1);
            } else {
                //해당 종류의 옷이 없을 경우 추가
                hs.put(clothes[i][1], 1);
            }
        }
        
        int res = 1;
        
        for(Integer value : hs.values()) {
            res *= value + 1;
        }
        
        return res - 1;
    }
}