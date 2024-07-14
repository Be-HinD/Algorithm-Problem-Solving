import java.util.*;
class Solution {
    public String[] solution(String[][] plans) {
        
        /**
        Queue 자료구조를 활용해서 풀이.
        스탑과제 / 새로운 과제
        새로운 과제를 진행하다가, 새로운 과제
        **/
        Stack<String[]> old = new Stack<>();
        Arrays.sort(plans, new Comparator<>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                int a = Integer.parseInt(o1[1].substring(0,2) + o1[1].substring(3));
                int b = Integer.parseInt(o2[1].substring(0,2) + o2[1].substring(3));

                return a - b;
            }
        });
        
        //step1. 배열 순회하면서 끝나는 작업은 res 큐에 저장, 안끝나는 작업은 old 스택에 저장
        Queue<String> res = new ArrayDeque<>();
        
        String[] firstWork = plans[0];
        for(int i=1; i<plans.length; i++) {
            String[] now = plans[i];
            //HHMM == 00:00일경우 int 형식은 0으로 받음
            String oldTime = calcDate(firstWork[1]);
            String freshTime = calcDate(now[1]);
            
            int diffMinutes = clacDiffTime(freshTime, oldTime);
            if(Integer.parseInt(firstWork[2]) > diffMinutes) {
                //다음 작업 시작 전까지 현재 작업을 끝내지 못할 경우 남은 시간을 old 큐에 저장
                String remainTime = (Integer.parseInt(firstWork[2]) - diffMinutes) + "";
                firstWork[2] = remainTime;
                
                old.push(firstWork);
            }
            else {
                //현재 작업을 마무리하고, 다음 작업까지의 남는 시간이 있을 경우!
                res.offer(firstWork[0]);
                int remainTime = Math.abs(Integer.parseInt(firstWork[2]) - diffMinutes); //남는 시간
                while(!old.isEmpty()) {
                    String[] prev = old.pop();
                    int gapTime = remainTime - Integer.parseInt(prev[2]);
                    
                    if(gapTime >= 0) {
                        //옛날 일 종료
                        res.offer(prev[0]);
                        remainTime -= Integer.parseInt(prev[2]); //남은시간 갱신
                    }
                    else {
                        prev[2] = Integer.toString(Integer.parseInt(prev[2]) - remainTime);
                        old.push(prev);
                        break;
                    }
                }
            }
            
            firstWork = now;
        }
        
        res.offer(firstWork[0]);
        
        // step2. old 큐에 있는 작업들 모두 수행 순차적으로 수행
        String[] ans = new String[res.size() + old.size()];
        int p = 0;
        while(!res.isEmpty()) {
            ans[p++] = res.poll();
        }
        while(!old.isEmpty()) {
            ans[p++] = old.pop()[0];
        }
        return ans;
    }
    
    static String calcDate(String date) {
        // 11:40 -> 1140
        return date.substring(0,2) + date.substring(3);
    }
    
    static int clacDiffTime(String freshTime, String oldTime) {
        // ex) 1210 - 1140 == 70 X -> 30 O
        // 시간 따로, 분 따로
        int hour = Integer.parseInt(freshTime.substring(0,2)) - Integer.parseInt(oldTime.substring(0,2));
        int minute = 60;
        if(Integer.parseInt(freshTime.substring(2)) < Integer.parseInt(oldTime.substring(2))) {
            hour--;
            minute -= Integer.parseInt(oldTime.substring(2));
            minute += Integer.parseInt(freshTime.substring(2));
            
        }
        else {
            minute = Integer.parseInt(freshTime.substring(2));
            minute -= Integer.parseInt(oldTime.substring(2));
        }
        
        return (hour*60) + minute;
    }
}