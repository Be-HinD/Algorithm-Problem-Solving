import java.util.*;
class Solution {
    public int solution(int n, int[] stations, int w) {
        int res = 0;
        int start = 1;
        int leng = w*2 + 1;
        for(int idx : stations) {
            int left = idx - w;
            int right = idx + w;
            int diff = left - start;
            res += Math.ceil((float) diff / leng);
            start = right+1;
        }
        if(start-1 < n) {
            int diff = n - (start-1);
            res += Math.ceil((float) diff / leng);
        }
        
        return res;
    }
}