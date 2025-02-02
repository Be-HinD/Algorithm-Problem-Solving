class Solution {
    public int solution(int num) {
        int answer = 0;
        
        long n = num;
        while(n != 1 && answer < 501) {
            if((n & 1) == 0) {
                n /= 2;
            }
            else n = n*3 + 1;
            answer++;
        }
        
        return answer > 500 ? -1 : answer;
    }
}