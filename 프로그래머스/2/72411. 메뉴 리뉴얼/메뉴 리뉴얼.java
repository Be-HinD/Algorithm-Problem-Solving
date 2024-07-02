import java.util.*;
class Solution {
    static int n, max;
    static boolean flag;
    static char[] item;
    static boolean[] v;
    static HashMap<String, Integer> map;
    public String[] solution(String[] orders, int[] course) {
        
        /**
        조합 트라이 -> 각 개수별 TreeMap 한 후 Iter ㄱㄱ
        **/
        
        List<String> result = new ArrayList<>();
        for(int i=0; i<course.length; i++) {
            int target = course[i];
            max = 0;
            map = new HashMap<>();
            n = target;
            
            for(int j=0; j<orders.length; j++) {
                String idx = orders[j];
                item = new char[target];
                Comb(0,0,idx);
            }
            if(max < 2) continue;
            for(String key : map.keySet()) {
                    int count = map.get(key);
                    if(count == max) {
                        result.add(key);
                    }
                }
        }
        
        String[] answer = new String[result.size()];
        int p = 0;
        for(String idx : result) {
            answer[p++] = idx;
        }
        Arrays.sort(answer);
        return answer;
    }
    static void Comb(int start, int idx, String arr) {
        
        if(idx == n) {
            String value = "";
            char[] copy = item.clone();
            Arrays.sort(copy);
            for(int i=0; i<copy.length; i++) {
                value += copy[i];
            }
            
            map.put(value, map.getOrDefault(value, 0) + 1);
            max = Math.max(max, map.get(value));
            
            return;
        }
        for(int i=start; i<arr.length(); i++) {
            item[idx] = arr.charAt(i);
            Comb(i+1, idx+1, arr);
        }
    }

}