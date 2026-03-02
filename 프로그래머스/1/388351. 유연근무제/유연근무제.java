import java.util.*;

class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        
        /**
        출근 희망 시각 : 7 ~ 11시 사이
        실제 출근 시각 : 6 ~ 23시59분 사이
        1 = 월 ~ 7 = 일 (6,7은 포함 X)
        한번이라도 못지키면 해당 인원 제외
        */
        
        int n = schedules.length;
        Set<Integer> deletedMember = new HashSet<Integer>();
        
        startday -= 2;
        for(int i=0; i<7; i++) {
            startday = (startday + 1) % 7;  //요일 변경
            if(startday == 5 || startday == 6) continue;    //토요일 or 일요일 이벤트 제외
            
            for(int j=0; j<n; j++) {    //인원 별
                if(deletedMember.contains(j)) continue; //이벤트 대상 제외
                
                // 출근 허용 시간 계산
                int allowTime = (schedules[j] / 100) * 100;
                if((schedules[j] % 100) + 10 >= 60) {   //분 단위 초과
                    allowTime += 100;   //1시간 증가
                    allowTime += ((schedules[j] % 100) + 10) % 60;  //남은 분 단위 계산 및 추가
                }
                else {
                    allowTime += (schedules[j] % 100) + 10; //분 단위 추가
                }
                
                if(timelogs[j][i] > allowTime) {
                    deletedMember.add(j);
                }
            }
        }
        
        return n - deletedMember.size();
    }
}