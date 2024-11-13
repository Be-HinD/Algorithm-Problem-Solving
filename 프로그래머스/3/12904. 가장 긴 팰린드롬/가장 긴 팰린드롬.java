import java.util.*;
class Solution
{
    public int solution(String s)
    {
        int answer = 1;

        //O(N^2) 풀이까지 가능.
        //자리수를 기준.
        //짝수일 경우 전체가 같은 문자열일 경우 탐색 x
        //abbaef == 4
        //두개의 연속되는 값이 있다면?
        
        Set<Character> set = new HashSet<>();
        for(int i=0; i<s.length(); i++) {
            set.add(s.charAt(i));
        }
        if(set.size() == 1) return s.length();
        
        for(int idx=1; idx<s.length(); idx++) {
            if(s.charAt(idx-1) == s.charAt(idx)) {
                //두개의 연속되는 값이 있다면 추가 탐색
                int l = idx-1;
                int r = idx;
                int ans = 0;
                while(l>=0 && r<s.length()) {
                if(s.charAt(l) == s.charAt(r)) {
                    ans += 2;
                    answer = Math.max(answer, ans);
                }
                else {
                    break;
                }
                l--;
                r++;
            }
                
            }
            
            int l = idx-1;
            int r = idx+1;
            int ans = 1;
            while(l>=0 && r<s.length()) {
                if(s.charAt(l) == s.charAt(r)) {
                    ans += 2;
                    answer = Math.max(answer, ans);
                }
                else {
                    break;
                }
                l--;
                r++;
            }
        }

        return answer;
    }
}