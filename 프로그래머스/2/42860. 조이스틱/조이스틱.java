import java.util.*;
class Solution {
    static int res, target;
    static int moveCnt = Integer.MAX_VALUE;
    static boolean flag, dfsFlag;
    static boolean[] v;
    static char[] arr, ans;
    public int solution(String name) {
        //4가지에 대해 각각 DFS??
        //입력값 배열로 변환
        arr = new char[name.length()];
        ans = new char[name.length()];
        for(int i=0; i<name.length(); i++) {
            arr[i] = 'A'; //A배열 필요가있나
            ans[i] = name.charAt(i);
        }
        
        int cursor = 0;
    
        for(int i=0; i<name.length(); i++) {
            int idx = (int) name.charAt(i);
            int aNum = (int) 'A';
            if(idx == aNum) continue;
            
            int cnt_up = 0;
            for(int j=0; j<30; j++) {
                if((aNum + 1) > 90) aNum = 65;
                else aNum++;
                cnt_up++;
                if(idx == aNum) break;
            }
            
            aNum = (int) 'A';
            int cnt_down = 0;
            for(int j=0; j<30; j++) {
                if((aNum - 1) < 65) aNum = 90;
                else aNum--;
                cnt_down++;
                if(idx == aNum) break;
            }
            
            int diff = Math.min(cnt_up, cnt_down);
            
            res += diff;
        }
        
        int zero_cnt = 0;
        for(int i=0; i<name.length(); i++) {
            if('A' == name.charAt(i)) zero_cnt++;
        }
        
        
        target = name.length() - zero_cnt;
        if(name.charAt(0) != 'A') target--;
        
        ans[0] = 'A';
        bfs(0,0,0);

        return res + moveCnt;
    }
    
    private static void bfs(int cursor, int cnt, int totalcnt) {
        if(arr.length <= totalcnt) {
            return;
        }
        if(cnt == target) {
            moveCnt = Math.min(moveCnt, totalcnt);
            return;
        }

        int num = cursor +1;
        //오른쪽
        if((num) == arr.length) num = 0;
        if(ans[num] != 'A') {
            ans[num] = 'A';
            bfs(num, cnt+1, totalcnt + 1);
            ans[num] = 'Z';
        }
        else bfs(num, cnt, totalcnt + 1);

        //왼쪽
        num = cursor -1;
        if((cursor - 1) == -1) num = arr.length-1;
        if(ans[num] != 'A') {
            ans[num] = 'A';
            bfs(num, cnt+1, totalcnt + 1);
            ans[num] = 'Z';
        }
        else bfs(num, cnt, totalcnt + 1);
    
    }
}