import java.util.*;
class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        
        Deque<int[]> stack = new ArrayDeque<>();
        
        for(int i=0; i<priorities.length; i++) {
            //인덱스 번호, 중요도 큐에 추가
            stack.add(new int[]{i,priorities[i]});
        }
        
        int[] sortt = priorities.clone();
        Arrays.sort(sortt); //중요도 순으로 정렬
        System.out.println(Arrays.toString(sortt));
        int pointer = sortt.length-1;
        
        while(!stack.isEmpty()) {
            
            int[] idx = stack.poll();
            
            if(idx[1] == sortt[pointer]) {
                //프로세스 실행
                answer++;
                if(idx[0] == location) break;
                pointer--;
            } else {
                stack.addLast(idx);
            }
            
            
        }
        
        return answer;
    }
}