import java.util.*;
class Solution {
    public int solution(int[][] sizes) {
        int answer = 0;
        int max = 0;
        int[] arr = new int[sizes.length];
        
        for(int i=0; i<sizes.length; i++) {
            max = Math.max(max, sizes[i][0]);
            max = Math.max(max, sizes[i][1]);
            int min = Integer.MAX_VALUE;
            min = Math.min(min, sizes[i][0]);
            min = Math.min(min, sizes[i][1]);
            arr[i] = min;
        }
        
        Arrays.sort(arr);
        
        return max * arr[arr.length-1];
    }
}