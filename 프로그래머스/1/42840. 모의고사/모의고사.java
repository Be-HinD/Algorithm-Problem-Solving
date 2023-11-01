import java.util.*;
class Solution {
    static int[] supo1 = new int[]{1, 2, 3, 4, 5};
    static int[] supo2 = new int[]{2, 1, 2, 3, 2, 4, 2, 5};
    static int[] supo3 = new int[]{3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
    public int[] solution(int[] answers) {
        int[] answer = new int[3];
        
        //1번 수포자
        int cnt = 0;
        int pointer = 0;
        int p = 0;
        while(true) {
            if(answers[pointer] == supo1[p]) cnt++;
            pointer++;
            p = (p + 1) % 5;
            if(pointer == answers.length) break;
        }
        answer[0] = cnt;
        
        
        //2번 수포자
        cnt = 0;
        pointer = 0;
        p = 0;
        while(true) {
            if(answers[pointer] == supo2[p]) cnt++;
            pointer++;
            p = (p + 1) % 8;
            if(pointer == answers.length) break;
        }
        answer[1] = cnt;
        
        //3번 수포자
        cnt = 0;
        pointer = 0;
        p = 0;
        while(true) {
            if(answers[pointer] == supo3[p]) cnt++;
            pointer++;
            p = (p + 1) % 10;
            if(pointer == answers.length) break;
        }
        answer[2] = cnt;
        
        int Max = 0;
        for(int i=0; i<3; i++) {
            Max = Math.max(Max, answer[i]);
        }
        
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<3; i++) {
            if(answer[i] == Max) list.add(i);
        }
        
        int[] res = new int[list.size()];
        
        for(int i=0; i<list.size(); i++) {
            res[i] = list.get(i) + 1;
        }
        return res;
    }
}