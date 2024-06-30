class Solution {
    public int solution(int storey) {
        int answer = 0;
        
        /**
        그리디 풀이
        : 맨 앞에서부터 나누면서 1의 자리의 경우에는 5를 기준으로 위로 혹은 아래로.
        **/
        
        while(storey > 0) {
            
            int digit = storey % 10;
            storey /= 10;
            
            if(digit == 5) {
                if(storey % 10 >= 5) {
                    answer += (10 - digit);
                    storey++;
                }
                else {
                    answer += digit;
                }
            }
            else if(digit > 5) {
                answer += (10 - digit);
                storey++;
            }
            else {
                answer += digit;
            }
        }
        
        return answer;
    }
}