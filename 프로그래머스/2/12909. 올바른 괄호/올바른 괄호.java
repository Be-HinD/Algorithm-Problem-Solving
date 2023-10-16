class Solution {
    boolean solution(String s) {
        boolean answer = true;
        int openCnt = 0; //여는괄호의 중첩카운트
        for(int i=0; i<s.length(); i++) {
            char idx = s.charAt(i);
            if(idx == '(') {//여는 괄호일 시
                openCnt++;
            } else { //닫는 괄호일 시
                if(openCnt == 0) { //여는괄호의 중첩이 0일 때
                    answer = false;
                    break;
                }
                openCnt--;
            }
        }
        if(openCnt != 0) answer = false; //여는괄호가 다 닫히지 않았을 때

        return answer;
    }
}