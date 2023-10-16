class Solution {
    boolean solution(String s) {
        boolean answer = true;
        int openCnt = 0;
        for(int i=0; i<s.length(); i++) {
            char idx = s.charAt(i);
            if(idx == '(') {//여는 괄호일 시
                openCnt++;
            } else { //닫는 괄호일 시
                if(openCnt == 0) {
                    answer = false;
                    break;
                }
                openCnt--;
            }
        }
        if(openCnt != 0) answer = false;

        return answer;
    }
}