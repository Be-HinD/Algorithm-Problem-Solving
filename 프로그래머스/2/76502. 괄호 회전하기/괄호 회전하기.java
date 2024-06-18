import java.util.*;
class Solution {
    public int solution(String s) {
        int answer = 0;
        
        if(s.length() == 1) return 0;
        for(int i=0; i<s.length(); i++) {
            //i번째부터 시작 -> 왼쪽으로 한칸씩 이동
            int mini = 0;   //소괄호
            int mid = 0;    //중괄호
            int big = 0;    //대괄호
            boolean flag = true;
            int p = i;
            for(int j=0; j<s.length(); j++) {
                
                char idx = s.charAt(p);
                
                char prev = 'a';
                if(p == 0) prev = s.charAt(s.length() -1);
                else prev = s.charAt(p - 1);
                
                if(idx == '(' || idx == ')') {
                    if(idx == '(') {
                        mini++;
                    }
                    else {
                        
                        if(prev == '[' || prev == '{') {
                            flag = !flag;
                            break;
                        }
                        
                        if(mini == 0) {
                            flag = !flag;
                            break;
                        }
                        else mini--;
                    }
                }
                else if(idx == '[' || idx == ']') {
                    if(idx == '[') {
                        mid++;
                    }
                    else {
                        
                        if(prev == '(' || prev == '{') {
                            flag = !flag;
                            break;
                        }
                        
                        if(mid == 0) {
                            flag = !flag;
                            break;
                        }
                        else mid--;
                    }
                }
                else {
                    if(idx == '{') {
                        big++;
                    }
                    else {
                        if(prev == '[' || prev == '(') {
                            flag = !flag;
                            break;
                        }
                        
                        if(big == 0) {
                            flag = !flag;
                            break;
                        }
                        else big--;
                    }
                }
                p = (p + 1) % s.length();
            }
        
            if(flag) {
                if(mini != 0 || mid != 0 || big != 0) continue;
                answer++;
            }
        }
        
        return answer;
    }
}