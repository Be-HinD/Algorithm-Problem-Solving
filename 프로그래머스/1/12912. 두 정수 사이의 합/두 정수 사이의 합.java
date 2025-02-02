class Solution {
    public long solution(int a, int b) {
        if(a==b) return a;
        return (long) (Math.max(a,b) - Math.min(a,b) + 1) * (a + b) / 2;
    }
}