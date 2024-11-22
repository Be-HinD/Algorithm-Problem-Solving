import java.util.*;
class Solution {
    public String[] solution(String[] s) {
        
        String[] res = new String[s.length];
        int p = 0;
        for(String idx : s) {
            res[p++] = process(idx);
        }
        return res;
    }
    
    // "110"추출을 제외한 나머지 로직에 대한 메서드
    private static String process(String idx) {
        String remainStr = extract110(idx);
        
        int cnt110 = (idx.length() - remainStr.length()) / 3; //추출된 "110"의 개수
        
        //삽입위치 탐색
        int zeroIdx = 0;
        for(int i=0; i<remainStr.length(); i++) {
            if(remainStr.charAt(i) == '0') {
                zeroIdx = i+1;
            }
        }
        
        StringBuilder res = new StringBuilder();
        for(int i=0; i<zeroIdx; i++) {
            res.append(remainStr.charAt(i));
        }
        for(int i=0; i<cnt110; i++) {
            res.append("110");
        }
        for(int i=zeroIdx; i<remainStr.length(); i++) {
            res.append(remainStr.charAt(i));
        }
        return res.toString();
    }
    
    // "110"추출 메서드
    private static String extract110(String idx) {
        List<Character> str = new ArrayList<>();
        
        for(int i=0; i<idx.length(); i++) {
            if(str.size() >= 2 && idx.charAt(i) == '0') {
                if(str.get(str.size()-2) == '1' && str.get(str.size()-1) == '1') {
                    str.remove(str.size()-1);
                    str.remove(str.size()-1);
                }
                else {
                    str.add(idx.charAt(i));
                }
            }
            else {
                str.add(idx.charAt(i));
            }
        }
        
        // 효율적인 문자열 연산을 위한 StringBuilder 활용
        StringBuilder sb = new StringBuilder();
        for(char x : str) {
            sb.append(x);
        }
        
        return sb.toString();
    }
    
}