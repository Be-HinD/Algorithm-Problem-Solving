import java.util.*;
class Solution {
    static int min, max;
    public String solution(String s) {
        String answer = "";
        
        String[] arr = s.split(" ");
        
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
        for(int i=0; i<arr.length; i++) {
            int idx = Integer.parseInt(arr[i]);
            min = Math.min(min, idx);
            max = Math.max(max, idx);
        }
        
        answer += min;
        answer += " ";
        answer += max;
        return answer;
    }
}