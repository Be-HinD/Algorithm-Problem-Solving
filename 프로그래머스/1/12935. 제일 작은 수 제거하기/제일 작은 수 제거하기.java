import java.util.*;
class Solution {
    public int[] solution(int[] arr) {
        int[] answer = new int[arr.length-1];
        
        if(arr.length == 1) return new int[]{-1};
        
        int min = arr[0];
        for(int idx : arr) min = Math.min(min, idx);
        
        int p = 0;
        for(int idx : arr) {
            if(idx == min) continue;
            answer[p++] = idx; 
        }
        
        
        return answer;
    }
}