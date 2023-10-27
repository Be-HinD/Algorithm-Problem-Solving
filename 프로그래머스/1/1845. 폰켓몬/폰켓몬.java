import java.util.*;
class Solution {
    static int N, res;
    static HashSet<Integer> hs;
    public int solution(int[] nums) {
        N = nums.length/2;
        
        hs = new HashSet<>();
        for(int i=0; i<nums.length; i++) {
            hs.add(nums[i]);
        }
        
        if(hs.size() >= N) return N;
        return hs.size();
    }
}