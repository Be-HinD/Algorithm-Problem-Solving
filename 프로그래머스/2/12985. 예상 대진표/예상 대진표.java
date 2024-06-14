class Solution
{
    public int solution(int n, int a, int b)
    {
        int answer = 0;

        /**
        20번 -> 1,2 3,4 5,6 7,8 9,10 11,12 13,14 15,16 17,18 19,20
                1   2    3   4   5     6     7     8    9      10
        홀수 번호라면 /2 + 1
        짝수 번호라면 /2
        
        **/

        while(a != b) { //같은 승부 다음 번호는 서로 같아질테니까
            
            a = (a+1) / 2;
            b = (b+1) / 2;
            answer++;
            
        }
        
        return answer;
    }
}