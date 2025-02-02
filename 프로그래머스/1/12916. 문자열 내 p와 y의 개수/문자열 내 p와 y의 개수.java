class Solution {
    boolean solution(String s) {

       int pCnt = 0, yCnt = 0;
        
        for(char idx : s.toCharArray()) {
            if(idx == 'p' || idx == 'P') pCnt++;
            else if(idx == 'y' || idx == 'Y') yCnt++;
        }

        return pCnt == yCnt ? true : false;
    }
}