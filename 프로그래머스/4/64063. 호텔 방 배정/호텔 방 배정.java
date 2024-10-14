import java.util.*;
class Solution {
    Map<Long, Long> map;
    public long[] solution(long k, long[] room_number) {
        
        map = new HashMap<>();
        List<Long> list = new ArrayList<>();
        
        for(int i=0; i<room_number.length; i++) {
            long cur = room_number[i];
            
            List<Long> temp = new ArrayList<>();
            while(true) {
                if(map.get(cur) == null) {
                    list.add(cur);
                    map.put(cur, cur+1);
                    if(!temp.isEmpty()) {
                        for(long idx : temp) {
                            map.put(idx, cur+1);
                        }
                    }
                    break;
                }
                else {
                    //해시에 값이 있다면
                    temp.add(cur);
                    cur = map.get(cur);
                }
            }
        }
    
        long[] res = new long[list.size()];
        
        for(int i=0; i<list.size(); i++) {
            res[i] = list.get(i);
        }
        
        return res;
    }
}