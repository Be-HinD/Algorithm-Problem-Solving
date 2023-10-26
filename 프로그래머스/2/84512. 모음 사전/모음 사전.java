import java.util.*;
class Solution {
    static int res;
    static char[] input;
    static boolean flags = false;
    static char[] arr;
    static char[] exam = new char[]{'A', 'E', 'I', 'O', 'U'};
    public int solution(String word) {
        int answer = 0;
        
        input = new char[word.length()];
        for(int i=0; i<word.length(); i++) {
            input[i] = word.charAt(i);
        }
            
        arr = new char[5];
        
        Perm(0);
        
        return res;
    }
    
    private static void Perm(int cnt) {
        if(flags) return;
        if(cnt == 5) {
            return;
        } else {
            for(int i=0; i<arr.length; i++) {
                if(flags) return;
                arr[cnt] = exam[i];
                res++;
            
                if(cnt == input.length-1) {
                    boolean flag = true;
                    for(int j=0; j<=cnt; j++) {
                        if(input[j] != arr[j]) {
                            flag = false;
                            break;
                        }
                    }
                    if(flag) {
                        System.out.println(res);
                        flags = true;
                        return;
                    }
                }
                
                Perm(cnt+1);
            }
        }
    }
}