import java.util.*;
class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        
        // 소요시간을 기준으로 오름차순 정렬
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        
        // 시작시간을 기준으로 오름차순 정렬
        PriorityQueue<int[]> startPq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        
        int time = 0;
        int sum = 0;
        List<int[]> list = new ArrayList<>();
        for(int i=0; i<jobs.length; i++) {
            startPq.offer(jobs[i]);
        }
        while(true) {
            //현재 시간에 작업 가능한 목록 리스트를 조회
            if(startPq.isEmpty()) break;
            while(!startPq.isEmpty() && startPq.peek()[0] <= time) {
                pq.offer(startPq.poll());
            }
            
            if(pq.isEmpty()) {
                time++;
                continue;
            }
            else {
                //작업 가능한 목록이 있을 경우
                int[] job = pq.poll();
                sum += (time-job[0]) + job[1];
                time += job[1];
                //남은 작업들은 다시 진행대기 큐에 추가
                while(!pq.isEmpty()) {
                    startPq.offer(pq.poll());
                }
            }
            
        }
        
        answer = sum / jobs.length;
        return answer;
    }
}