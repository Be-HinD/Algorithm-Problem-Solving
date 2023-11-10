import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        
        List<Integer> list = new ArrayList<>();
        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        list.add(arr[0]);
        
        for(int i=1; i<arr.length; i++) {
            if(arr[i-1] != arr[i]) {
                list.add(arr[i]);
            }
        }
        
        int[] res = new int[list.size()];
        
        for(int i=0; i<list.size(); i++) {
            res[i] = list.get(i);
        }
        
        return res;
    }
}