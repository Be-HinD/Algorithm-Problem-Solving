import java.util.*;
class Solution {
    public boolean solution(String s) {
        
        if(s.length() != 4 && s.length() != 6) {
            return false;
        }
        
        char[] chars = s.toCharArray();
        for(char idx : chars) {
            if(!Character.isDigit(idx))
                return false;
        }
        
        return true;
    }
}