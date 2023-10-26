import java.util.*;
class Solution {
    static int res;
    static char[] input;
    static boolean flags = false; //순열 탈출 플래그
    static char[] arr; //순열 배열
    static char[] exam = new char[]{'A', 'E', 'I', 'O', 'U'};
    
    public int solution(String word) {
        
        //입력값 배열로 변환
        input = new char[word.length()];
        for(int i=0; i<word.length(); i++) {
            input[i] = word.charAt(i);
        }
            
        arr = new char[5];
        
        Perm(0);
        
        return res;
    }
    
    private static void Perm(int cnt) {
        //기저조건
        if(cnt == 5) {
            return;
        } 
        
        else {
            for(int i=0; i<arr.length; i++) {
                if(flags) return; //정답을 찾았다면 탐색 중단
                
                arr[cnt] = exam[i];
                res++; //인덱스값 증가
            
                //입력값과 길이가 같을때만 검사
                if(cnt == input.length-1) {
                    boolean flag = true;
                    for(int j=0; j<=cnt; j++) {
                        if(input[j] != arr[j]) {
                            flag = false;
                            break;
                        }
                    }
                    //입력과 같을 경우
                    if(flag) {
                        System.out.println(res);
                        flags = true;
                        return;
                    }
                }
                
                Perm(cnt+1); //재귀 호출
            }
        }
    }
}