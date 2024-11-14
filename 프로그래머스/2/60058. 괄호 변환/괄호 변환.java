import java.util.*;
class Solution {
    public String solution(String p) {
        String answer = "";
        
        if(!isBalanced(p)) return p;
        if(isBalanced(p) && isCorrect(p)) return p;
    
        answer = goTo(p);
        
        return answer;
    }
    
    private static String[] seperate(String s) {
        // 앞에서부터 2개씩 isBalanced 메서드 수행.
        // True일 경우 분리
        String[] res = new String[2];
        int left = 0, right = 0;
        for(int i=0; i<s.length(); i++) {
            if(s.charAt(i) == '(') left++;
            else right++;
            
            if(left == right) {
                res[0] = s.substring(0, i+1);
                res[1] = s.substring(i+1);
                return res;
            }
        }
        return res;
    }
    
    private static String goTo(String s) {
        if(s.isEmpty()) return "";  //입력이 빈 문자열 일 경우
        String[] uv = seperate(s); //분리
        if(isCorrect(uv[0])) {
            uv[0] += goTo(uv[1]);
            return uv[0];
        }
        else {
            String res = "(";
            res += goTo(uv[1]);
            res += ")";
            res += reverse(uv[0]);
            return res;
        }
    }
    
    private static String reverse(String s) {
        String res = s.substring(1, s.length()-1);
        
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<res.length(); i++) {
            if(res.charAt(i) == '(') {
                sb.append(')');
            }
            else sb.append('(');
        }
        
        return sb.toString();
    }
    
    private static boolean isBalanced(String s) {
        int left = 0, right = 0;
        for(char idx : s.toCharArray()) {
            if(idx == '(') left++;
            else right++;
        }
        
        if(left == right) return true;
        return false;
    }
    
    private static boolean isCorrect(String s) {
        int left = 0;
        
        for(char idx : s.toCharArray()) {
            if(idx == ')' && left == 0) return false;
            if(idx == ')' && left > 0) left--;
            if(idx == '(') left++;
        }
        
        return true;
    }
    
}