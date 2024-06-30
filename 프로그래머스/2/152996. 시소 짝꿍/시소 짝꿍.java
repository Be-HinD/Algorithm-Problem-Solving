import java.util.*;
class Solution {
    public long solution(int[] weights) {
        long answer = 0;
        
        /**
        각 사람마다 2m, 3m, 4m의 3개 값들을 List로 저장
        1,000의 사람에 대해서 각각 999번 비교 1,000 * 999 == 999,000 번
        1,000이 아니라 100,000
        다른 풀이
        : Map을 통해서 무게 별 카운팅
        Map Iter 돌면서 두 명이 다른 위치에 앉는 경우의 수 체크
        **/
        Arrays.sort(weights);
        Map<Double, Integer> count = new HashMap<>();
        
        for(int idx : weights) {
            double f1 = idx*1.0;
    		double f2 = (idx*2.0)/3.0;
    		double f3 = (idx*1.0)/2.0;
    		double f4 = (idx*3.0)/4.0;
            
            if(count.containsKey(f1)) answer += count.get(f1);
            if(count.containsKey(f2)) answer += count.get(f2);
            if(count.containsKey(f3)) answer += count.get(f3);
            if(count.containsKey(f4)) answer += count.get(f4);
            
            count.put((idx*1.0), count.getOrDefault((idx*1.0), 0) + 1);
            
        }
                      
        
        return answer;
    }
}