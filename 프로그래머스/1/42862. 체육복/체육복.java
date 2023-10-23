import java.util.*;
class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = n - lost.length;
        
        HashSet<Integer> hs = new HashSet<>();
        for(int i=0; i<reserve.length; i++) { //해시셋
            hs.add(reserve[i]);
        }
        
        for(int i=0; i<lost.length; i++) { //여벌 체육복을 가져온 학생이 체육복을 도난당했을 경우
            if(hs.contains(lost[i])) {
                answer++;
                hs.remove(lost[i]);
                lost[i] = -100;
            }
        }
        
        Arrays.sort(lost);
        //한명씩 앞 뒤를 살펴보며 빌릴 수 있으면 cnt++
        for(int i=0; i<lost.length; i++) {
            int idx = lost[i];
            if(hs.contains(idx-1)) {
                hs.remove(idx-1);
                answer++;
                continue;
            }
            if(hs.contains(idx+1)) {
                hs.remove(idx+1);
                answer++;
            }
        }
        return answer;
    }
}