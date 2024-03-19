import java.util.*;
class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        
        HashSet<Integer> set = new HashSet<>();
        
        for(int i=0; i<win_nums.length; i++) {
            set.add(win_nums[i]);
        }
        
        int zeroCnt = 0;
        int res = 0;
        for(int i=0; i<lottos.length; i++) {
            if(lottos[i] == 0) {
                zeroCnt++;
                continue;
            }
            
            if(set.contains(lottos[i])) res++;
            
        }
        
        int rank = 7;
        int high = rank - (res + zeroCnt);
        int low = rank - res;
        
        int[] answer = new int[2];
        if(high == 7) {
            answer[0] = 6;
        }
        else answer[0] = high;
        
        if(low == 7) {
            answer[1] = 6;
        }
        else answer[1] = low;
        return answer;
    }
}