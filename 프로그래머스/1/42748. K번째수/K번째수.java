import java.util.*;
class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        int pointer = 0;
        
        for(int i=0; i<commands.length; i++) {
            int leng = (commands[i][1]-commands[i][0])+1;
            int[] arr = new int[leng];
            int p = commands[i][0] - 1;
            for(int j=0; j<leng; j++) {
                arr[j] = array[p++];
            }
            Arrays.sort(arr);
            
            int searchNum = commands[i][2] - 1;
            answer[pointer++] = arr[searchNum];
        }
        
        return answer;
    }
}