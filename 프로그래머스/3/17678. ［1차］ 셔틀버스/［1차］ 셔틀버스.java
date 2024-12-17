/**
n회, t분 간격, 최대 m명 승객
**/
import java.util.*;
class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for(String time : timetable) {
            int minute = timeToMinute(time);
            pq.offer(minute);
        }
        
        int arriveMinute = timeToMinute("09:00");
        int min = 0;
        for(int i=0; i<n; i++) {
            if(i != 0) arriveMinute += t;
            int max = m;
            while(!pq.isEmpty() && max > 0) {
                if(pq.peek() <= arriveMinute) {
                    min = Math.max(min, pq.peek()-1);
                    pq.poll();
                    max--;
                }
                else break;
            }
            if(max > 0) {
                min = arriveMinute;
            }
        }
        System.out.println(min);
        answer = minuteToTime(min);
        
        return answer;
    }
    
    static int timeToMinute(String time) {
        String[] arr = time.split(":");
        
        int res = 0;
        res += Integer.parseInt(arr[0]) * 60;
        res += Integer.parseInt(arr[1]);
        return res;
    }
    static String minuteToTime(int minute) {
        int hour = minute/60;
        minute %= 60;
        
        String res = "";
        if(hour < 10) {
            res = "0" + Integer.toString(hour);
        }
        else res += Integer.toString(hour);
        
        res += ":";
        if(minute < 10) {
            res += "0" + Integer.toString(minute);
        }
        else res += Integer.toString(minute);
        return res;
    }
}