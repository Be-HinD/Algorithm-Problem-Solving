import java.util.*;
class Solution {
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        
        Map<String, Integer> condition = new HashMap<>();
        condition.put("code", 0);
        condition.put("date", 1);
        condition.put("maximum", 2);
        condition.put("remain", 3);
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2) -> o1[condition.get(sort_by)] - o2[condition.get(sort_by)]);
        
        for(int[] item : data) {
            if(item[condition.get(ext)] < val_ext) {
                pq.offer(item);
            }
        }
        
        int[][] answer = new int[pq.size()][4];
        int size = pq.size();
        for(int i=0; i<size; i++) {
            answer[i] = pq.poll();
        }
        
        return answer;
    }
}