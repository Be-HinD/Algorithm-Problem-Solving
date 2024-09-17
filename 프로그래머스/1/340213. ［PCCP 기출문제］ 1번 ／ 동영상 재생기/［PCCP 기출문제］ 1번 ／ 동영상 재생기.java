class Solution {
    static int totalVideo;
    static int cur;
    static int startOp;
    static int endOp;
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String answer = "";
        
        totalVideo = strToInt(video_len);
        cur = strToInt(pos);
        startOp = strToInt(op_start);
        endOp = strToInt(op_end);
        
        //시작 지점이 오프닝 구간일 경우
        if(startOp <= cur && cur <= endOp) {
            cur = endOp;
        }
        
        for(int i=0; i<commands.length; i++) {
            moveToVideo(commands[i]);
        }
        
    
        int minute = cur/60;
        int sec = cur % 60;
    
        if(minute < 10) {
            answer += "0";
            answer += Integer.toString(minute);
        }
        else answer += Integer.toString(minute);
        answer += ":";
        if(sec < 10) {
            answer += "0";
            answer += Integer.toString(sec);
        }
        else answer += Integer.toString(sec);
        
        return answer;
    }
    
    private static void moveToVideo(String command) {
        if(command.equals("next")) {
            cur += 10;
            if(cur > totalVideo) {
                cur = totalVideo;
            }
        }
        else {
            cur -= 10;
            if(cur < 0) cur = 0;
        }
        
        if(startOp <= cur && cur <= endOp) {
            //오프닝 구간일 경우
            cur = endOp;
        }
    }
    
    private static int strToInt(String time) {
        String[] temp = time.split(":");
        int totalValue = Integer.parseInt(temp[1]);
        totalValue += (Integer.parseInt(temp[0]) * 60);
        
        return totalValue;
    }
}