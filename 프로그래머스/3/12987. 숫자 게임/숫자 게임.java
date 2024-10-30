import java.util.*;
class Solution {
    public int solution(int[] A, int[] B) {
        int leng = A.length;
        
        PriorityQueue<Integer> aList = new PriorityQueue<>((o1,o2) -> o2-o1);
        PriorityQueue<Integer> bList = new PriorityQueue<>((o1,o2) -> o2-o1);
        for(int i=0; i<leng; i++) {
            aList.offer(A[i]);
            bList.offer(B[i]);
        }
        
        int res = 0;
        while(!aList.isEmpty()) {
            int aIdx = aList.poll();
            
            if(bList.peek() > aIdx) {
                bList.poll();
                res++;
            }
        }
        return res;
    }
}