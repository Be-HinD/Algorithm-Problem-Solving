import java.util.*;
class Solution {
    public int[] solution(String s) {
        
        int changeCnt = 0;
        int delZero = 0;
        while(true) {
            //이진 변환 과정
            changeCnt++;
            int c = 0;
            for(int i=0; i<s.length(); i++) {
                if(s.charAt(i) == '1') c++;
                else delZero++;
            }
            
            s = Integer.toBinaryString(c);
            if(s.equals("1")) break;
        }
        
    
        return new int[]{changeCnt, delZero};
    }
}