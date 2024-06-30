import java.util.*;
class Solution {
    public int solution(String[][] book_time) {
        int answer = 0;
        
        /**
        스케쥴링 문제! Well Known
        시작시간을 기준으로 정렬 후 ArrayList를 통해 종료 시간을 기록.
        List의 최대 size를 계속 저장.
        **/
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        //시작 시간을 기준으로 pq 삽입
        for(int i=0; i<book_time.length; i++) {
            String[] cur = book_time[i];
            String[] start = cur[0].split(":");
            String[] end = cur[1].split(":");
            int startTime = Integer.parseInt(start[0] + start[1]);
            int endTime = Integer.parseInt(end[0] + end[1]);
            pq.offer(new int[]{startTime, endTime});
        }
        
        List<Integer> list = new ArrayList<>();
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            for(int i=0; i<list.size(); i++) {
                int prevRoom = list.get(i);
                if(cur[0] >= prevRoom) {
                    list.remove(i);
                }
            }
            
            int diff = cur[1] % 100;
            diff += 10;
            if(diff >= 60) {
                cur[1] += 50;
            }
            else {
                cur[1] += 10;
            }
            list.add(cur[1]);
            answer = Math.max(answer, list.size());
        }
        
        return answer;
    }
}