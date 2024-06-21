import java.util.*;
class Solution {
    public int[] solution(int[] fees, String[] records) {
        
        /**
        차량 번호 별로 누적 주차 시간 구하기
        Map<차량번호, 누적 주차 시간>
        Map<차량번호, 입차 시간>
        OUT의 경우 이미 Map에 있는 경우니까 Map에서 입차시간 꺼내서
        출차 시간 - 입차 시간으로 차이 구한 후 누적 주차 시간 증가
        출차가 없는 경우 Map 순회하면서 23:59 - 입차 시간 계산 후 누적 주차 시간 증가
        
        차량번호 오름차순 별로 주차 요금 계산 및 return
        오름차순 ? => 누적 Map 순회하면서 INT로 변경 후 Queue에 추가
        누적 Map.size() => 차량 수
        **/
        
        Map<String,Integer> sum = new HashMap<>();
        Map<String,String> temp = new HashMap<>();
        
        for(int i=0; i<records.length; i++) {
            String[] cur = records[i].split(" ");
            
            if(cur[2].equals("IN")) {
                temp.put(cur[1], cur[0]);   //차량번호, 입차시간
            }
            else {
                //OUT의 경우
                String idx = temp.get(cur[1]);   //입차 시간
                String inHour = idx.substring(0,2);
                String inMinute = idx.substring(3,5);
                
                String outHour = cur[0].substring(0,2);
                String outMinute = cur[0].substring(3,5);
                
                int diffHour = (Integer.parseInt(outHour) - Integer.parseInt(inHour)) * 60;
                int diffMinute = (Integer.parseInt(outMinute) - Integer.parseInt(inMinute));
                int diff = (diffHour + diffMinute);
                if(sum.containsKey(cur[1])) {
                    sum.put(cur[1], sum.get(cur[1]) + diff);
                }
                else {
                    sum.put(cur[1], diff);
                }
                temp.remove(cur[1]);
                // System.out.println(cur[1] + " : " + diff);
                
            }
        }
        
        //출차시간이 없는 경우 계산 후 누적 시간 증가
        for(String key : temp.keySet()) {
            String idx = temp.get(key);
            String inHour = idx.substring(0,2);
            String inMinute = idx.substring(3,5);
            int diffHour = (23 - Integer.parseInt(inHour)) * 60;
            int diffMinute = (59 - Integer.parseInt(inMinute));
            int diff = (diffHour + diffMinute);
            
            if(sum.containsKey(key)) {
                    sum.put(key, sum.get(key) + diff);
                }
            else {
                sum.put(key, diff);
            }
        }
        
        //차량 번호 기준 오름차순 키값 정리
        int[] answer = new int[sum.size()];
        
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        
        for(String key : sum.keySet()) {
            int value = sum.get(key);
            
            pq.offer(new int[]{Integer.parseInt(key), value});
        }
        
        int p = 0;
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            //요금 계산
            if(cur[1] <= fees[0]) {
                answer[p++] = fees[1];
            }
            else {
                int fee = cur[1] - fees[0];
                if(fee % fees[2] == 0) {
                    fee /= fees[2];
                } 
                else {
                    //나누어떨어지지 않는다면 올림
                    fee /= fees[2];
                    fee += 1;
                }
                fee *= fees[3];
                answer[p++] = fee + fees[1];
            }
        }
        
        
        return answer;
    }
}