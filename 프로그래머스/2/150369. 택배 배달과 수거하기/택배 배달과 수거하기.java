class Solution {
    public long solution(int cap, int n, int[] d, int[] p) {
        long res = 0;
        
        for(int i=n-1; i>=0; i--) {
            if(d[i]==0 && p[i]==0) continue;
            
            // i위치까지는 무조건 가야함.
            move(i, cap, d);
            move(i, cap, p);
            
            res += (i+1) * 2;
            
            if(d[i]!=0 || p[i]!=0) i++;    //중요.
        }
        
        return res;
    }
    
    static void move(int idx, int cap, int[] arr) {
        // idx부터 cap만큼 남은 값 제거
        
        while(cap>0 && idx>=0) {
            if(cap >= arr[idx]) {   //남은 값 전부 처리 가능한 CASE
                cap -= arr[idx];
                arr[idx] = 0;
            }
            else {
                arr[idx] -= cap;
                cap = 0;
            }
            idx--;
        }
    }
}