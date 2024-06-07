import java.util.*;
class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        /**
        그리디 : x축 1부터 탐색하면서 요격하지 못하는 경우가 생기는 직전 지점만 요격
        예제에서는 3.x, 4.x, 11.x
        접근 : e를 기준으로 sorting, 배열 순회하면서 끝지점 직전만 요격
        요격 후 요격지점 기록 -> 뒤 미사일도 요격되었는지 체크를 
        **/
        
        Arrays.sort(targets, new Comparator<>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
            
        int prevPoint = 0;
        for(int i=0; i<targets.length; i++) {
            int[] arr = targets[i];
            // 직전 요격 지점과 안 겹치는지 확인
            if(prevPoint > arr[0] && prevPoint <= arr[1]) {
                
            }
            else {
                prevPoint = arr[1];
                answer++;
            }
        }
        
        
        return answer;
    }
}