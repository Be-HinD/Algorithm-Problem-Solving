import java.util.*;
class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        int leng = citations.length;
        
        Arrays.sort(citations);
        
        /**
        뒤에서부터 탐색해서,
        i번째 값과 leng - i 가 똑같다면 그 지점이 H-Index
        **/
        for(int i=0; i<leng; i++) {
            int diff = leng - i;
            if(citations[i] >= diff) {
                answer = diff;
                break;
            }
        }
        
        return answer;
    }
}