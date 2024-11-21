import java.util.*;

class Solution {
    public String[] solution(String[] s) {
        String[] answer = new String[s.length];
        
        for(int i=0; i<s.length; i++){
            String ans = solve(s[i]);   
            answer[i] = ans;
        }
        
        return answer;
    }
    
    public String solve(String s){
        StringBuilder sb = new StringBuilder();
        StringBuilder ooz = new StringBuilder();
        
        for(int i=0; i<s.length(); i++){
            Character c = s.charAt(i);
            if(sb.length()>=2 && c=='0' && sb.charAt(sb.length()-2)=='1' && sb.charAt(sb.length()-1)=='1'){
                ooz.append("110");
                sb.delete(sb.length()-2, sb.length());
            }
            else{
                sb.append(c);
            }
        }
        
        if(ooz.length()>0){
            //0이 없으면
            if(sb.indexOf("0")==-1){
                sb.insert(0, ooz);
            }
            else{
                int idx = sb.lastIndexOf("0");
                sb.insert(idx+1, ooz);
            }
        }
        
        return sb.toString();
    }
}