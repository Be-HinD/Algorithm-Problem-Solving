import java.util.*;
class Solution {
    static int[] supo1 = new int[]{1, 2, 3, 4, 5};
    static int[] supo2 = new int[]{2, 1, 2, 3, 2, 4, 2, 5};
    static int[] supo3 = new int[]{3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
    public int[] solution(int[] answers) {
        int[] answer = new int[3];
        
        //1번 수포자
        int cnt1 = 0;
        int cnt2 = 0;
        int cnt3 = 0;
        int pointer = 0;
        int p = -1;
        while(true) {
            if(answers[pointer] == supo1[(p+1) % 5]) cnt1++;
            if(answers[pointer] == supo2[(p+1) % 8]) cnt2++;
            if(answers[pointer++] == supo3[(p+1) % 10]) cnt3++;
            p++;
            if(pointer == answers.length) break;
        }

        int Max = Math.max(Math.max(cnt1, cnt2), cnt3);
        
        int length = 0;
        if(Max == cnt1) length++;
        if(Max == cnt2) length++;
        if(Max == cnt3) length++;
        
        int[] res = new int[length];
        
        p = 0;
        if(Max == cnt1) res[p++] = 1;
        if(Max == cnt2) res[p++] = 2;
        if(Max == cnt3) res[p++] = 3;
   
        return res;
    }
}