import java.util.*;
class Solution {
    public int solution(int n) {
        int answer = 0;
        String binaryString = Integer.toBinaryString(n);
    
        int oneCnt = 0;
        for(int i=0; i<binaryString.length(); i++) {
            if(binaryString.charAt(i) == '1') {
                oneCnt++;
            }
        }
        
        int temp = n+1;
        while(true) {
            String p = Integer.toBinaryString(temp);
            int tempCnt = 0;
            for(int i=0; i<p.length(); i++) {
                if(p.charAt(i) == '1') {
                    tempCnt++;
                }
            }
            if(oneCnt == tempCnt) {
                answer = temp;
                break;
            }
            temp++;
        }
        
        return answer;
    }
}