import java.util.*;
class Solution {
    public int solution(int n, int[] cores) {
        int answer = 0;
    
        if(n <= cores.length) {
            return n;
        }
        int low = 1;
        int high = 10000*n;
        int task = 0;
        int time = 0;
        while(low <= high) {
            final int mid = low + (high-low)/2;
            
            int sum = cores.length; //0초에 모두 코어에 진입.
            
            for(int i=0; i<cores.length; i++) {
                sum += mid / cores[i];
            }
            
            if(sum >= n) {
                high = mid - 1;
                time = mid;
                task = sum;
            }
            else {
                low = mid + 1;
            }
        }
        
        task -= n; //남은 작업
        
        for(int i=cores.length-1; i>=0; i--) {
            if(time % cores[i] == 0) {
                if(task == 0) {
                    answer = i + 1;
                    break;
                }
                task--;
            }
        }
        
        
        return answer;
    }
}