class Solution {
    static long answer;
    public long solution(int n) {
        
        /**
        1번에 1칸 또는 2칸 뛸 수 있음
        뛸 수 있는 모든 조합의 경우의 수를 출력
        **/
        if(n == 1) {
            return 1;
        }
        if(n == 2) {
            return 2;
        }
        long prev2 = 1;
        long prev = 2;
        
        for(int i=3; i<=n; i++) {
            answer = (prev2 + prev) % 1234567;
            prev2 = prev;
            prev = answer;
        }
        
        return answer;
    }
}