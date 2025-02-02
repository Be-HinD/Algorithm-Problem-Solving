class Solution {
    public long solution(long n) {
        long answer = 0;
        
        int x = 1;
        
        while(Math.pow(x,2) < n) {
            x++;
        }
        return (long) Math.pow(x, 2) == n ? (long) Math.pow(x+1,2) : -1;
    }
}