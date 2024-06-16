import java.util.*;
class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];

        Set<String> set = new HashSet<>();
        int people = 0;
        char prev = words[0].charAt(0);
        for(int i=0; i<words.length; i++) {
            //현재 턴이 누구인지
            char now = words[i].charAt(0);
            if(set.contains(words[i]) || prev != now) {
                //탈락
                answer[0] = people + 1;
                float temp = (float) (i+1) / (float) n;
                answer[1] = (int) Math.ceil(temp);  //반올림이 아니라 올림처리해야지 멍청아
                break;
            }
            prev = words[i].charAt(words[i].length() - 1);
            set.add(words[i]);
            people++;
            people %= n;
        }

        return answer;
    }
}