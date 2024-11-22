import java.util.*;
class Solution {
    public String[] solution(String[] s) {
        
        /**
        x를 최대한 사전 순으로
        x에 있는 "110"을 뽑아서
        임의의 위치에 다시 삽입
        접근법
        110을 뽑을 수 있는대로 전부 추출
        남은 x에서 뽑은 110들을 삽입할 위치 탐색
        마지막 0의 위치 뒤에 삽입
        **/
        
        String[] res = new String[s.length];
        int p = 0;
        for(String idx : s) {
            res[p++] = process(idx);
        }
        return res;
    }
    
    private static String process(String idx) {
        String remainStr = extract110(idx);
        int cnt110 = (idx.length() - remainStr.length()) / 3;
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
    
    private static String extract110(String idx) {
        Stack<Character> st = new Stack<>();
        
        for(int i=0; i<idx.length(); i++) {
            if(st.size() >= 2 && idx.charAt(i) == '0') {
                char mid = st.pop();
                char first = st.pop();
                if(first == '1' && mid == '1' && idx.charAt(i) == '0') continue;
                else {
                    st.push(first);
                    st.push(mid);
                    st.push(idx.charAt(i));
                }
            }
            else {
                st.push(idx.charAt(i));
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(char x : st) {
            sb.append(x);
        }
        
        return sb.toString();
    }
    
}