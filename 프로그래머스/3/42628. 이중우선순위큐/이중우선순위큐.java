import java.util.*;
class Solution {
    public int[] solution(String[] operations) {
        
        PriorityQueue<Integer> minpq = new PriorityQueue<>(new Comparator<Integer>() {
           @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        
        PriorityQueue<Integer> maxpq = new PriorityQueue<>(new Comparator<Integer>() {
           @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        
        for(int i=0; i<operations.length; i++) {
            String[] current = operations[i].split(" ");
            
            if(current[0].equals("I")) {
                minpq.offer(Integer.parseInt(current[1]));
                maxpq.offer(Integer.parseInt(current[1]));
            }
            
            else if(current[0].equals("D")) {
                if(current[1].equals("1")) {
                    if(isEmp(maxpq)) {
                        continue;
                    }
                    int max = maxpq.poll();
                    minpq.remove(max);
                }
                else if(current[1].equals("-1")) {
                    if(isEmp(minpq)) {
                        continue;
                    }
                    int min = minpq.poll();
                    maxpq.remove(min);
                }
            }
        }
        
        int[] answer = new int[2];
        
        if(isEmp(minpq)) {
            answer[0] = 0;
            answer[1] = 0;
        }
        else {
            answer[0] = maxpq.poll();
            answer[1] = minpq.poll();
        }
        
        return answer;
    }
    
    boolean isEmp(PriorityQueue<Integer> request) {
        if(request.isEmpty()) {
            return true;
        }
        return false;
    }
}