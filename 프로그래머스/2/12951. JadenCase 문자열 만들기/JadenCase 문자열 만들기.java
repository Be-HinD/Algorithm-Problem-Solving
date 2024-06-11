import java.util.*;
class Solution {
    public String solution(String s) {
        String answer = "";
        String[] arr = s.split(" ");
        
        StringBuilder sb = new StringBuilder();
        
        for(int i=0; i<arr.length; i++) {
            if(arr[i].isEmpty()) {
                sb.append(" ");
                continue;
            }
            String idx = arr[i];
            
            char p= idx.charAt(0);
            if(!Character.isDigit(p)) {
                //첫 문자를 대문자로
                sb.append(String.valueOf(p).toUpperCase());
                sb.append(idx.substring(1).toLowerCase()).append(" ");
                continue;
            }
            //문자열이 아니라면
            sb.append(idx.toLowerCase()).append(" ");
        }

        //기존 문자열 마지막에 공백이 있는지 없는지 체크
        int blankCnt = 0;
        for(int i=s.length()-1; i>=0 ;i--) {
            if(s.charAt(i) != ' ') {
                break;
            }
            else blankCnt++;
        }
        
        if(blankCnt == 0) sb.deleteCharAt(sb.length() - 1);
        else {
            for(int i=1; i<blankCnt; i++) {
                sb.append(" ");
            }
        }
        
        return sb.toString();
    }
}