        /**
        키워드 : 구간합, 누적합, 투포인터
        접근법
        누적합 1차원 배열 탐색
        투포인터 활용
        l, m, r
        포인터 이동조건
        m의 위치별로 투포인터 탐색
        if(m == 2)
        
        
        **/
import java.util.*;
class Solution {
    public int solution(int[] cookie) {
        int answer = 0;
        

        int[] sum = new int[cookie.length];
        sum[0] = cookie[0];
        for(int i=1; i<cookie.length; i++) {
            sum[i] = cookie[i] + sum[i-1];
        }
        
        for(int r=1; r<cookie.length; r++) {
            int l = 0;
            int m = 0;
            //l과 m은 같아도 된다.
            while(l<=m) {
                int left = sum[m];
                if(l != 0) left -= sum[l-1];
                int right = sum[r] - sum[m];
                
                if(left == right) answer = Math.max(answer, left);
                //m을 증가하는 기준 : 오른쪽값 낮추기
                //l을 증가하는 기준 : 왼쪽값 낮추기
                if(left < right) m++;
                else l++;
            }
            
        }
        
        
        return answer;
    }
}