import java.util.*;
class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        
        /**
        투포인터 드괘좨
        **/
        
        // 왼쪾에서 부터 원소 위치 별 토핑 종류 구하기
        int[] left = new int[topping.length];
        Set<Integer> set = new HashSet<>();
        for(int i=0; i<topping.length; i++) {
            set.add(topping[i]);
            left[i] = set.size();
        }
        
        set = new HashSet<>();
        int[] right = new int[topping.length];
        for(int i=topping.length -1; i>=0 ;i--) {
            set.add(topping[i]);
            right[i] = set.size();
        }
        
        for(int i=0; i<topping.length-1; i++) {
            if(left[i] == right[i+1]) {
                answer++;
            }
        }
        
        
        return answer;
    }
}