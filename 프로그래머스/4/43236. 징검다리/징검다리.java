import java.util.*;
class Solution {
    static int[] arr;
    public int solution(int distance, int[] rocks, int n) {

        Arrays.sort(rocks); //O(N)
        
        //모든 바위를 제거할 수 있는 경우
        if(n == rocks.length) return distance;
        
        arr = new int[rocks.length+1];
        arr[0] = 0;
        for(int i=0; i<rocks.length; i++) { //O(N)
            arr[i+1] = rocks[i];
        }
        
        return upper(distance, n) - 1;
    }
    
    private static int upper(int dist, int n) {
        int low = 0;
        int high = dist;
        
        while(low < high) { //O(logN)
            final int mid = low + (high-low) /2;
            
            if(!isOk(mid, n, dist)) {
                high = mid;
            }
            else {
                low = mid + 1;
            }
        }
        return high;
    }
    
    private static boolean isOk(int key, int delCnt, int dist) {
        int point = 0;
        
        for(int i=1; i<arr.length; i++) {  //O(N)
            int next = arr[i];
            if(next - arr[point] < key) {
                if(--delCnt < 0) return false;
                continue;
            }
            point = i;
        }
        
        //마지막 바위에서 목적지까지 비교
        if(dist - arr[point] < key) {
            if(delCnt == 0) return false;
        }
        
        return true;
    }
    
}