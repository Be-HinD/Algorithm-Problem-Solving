import java.util.*;
class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        HashMap<String, Integer> map = new HashMap<>();
        
        for(String people : completion) {
            if(map.containsKey(people)) {
                map.put(people, map.get(people) + 1);
                continue;
            }
            map.put(people, 1);
        }
        
        for(String people : participant) {
            if(!map.containsKey(people)) {
                answer = people;
                break;
            }
            if(map.get(people) == 1) map.remove(people);
            else {
                map.put(people, map.get(people) - 1);
            }
        }
        return answer;
    }
}