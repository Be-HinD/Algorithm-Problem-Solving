import java.util.*;
class Solution {
    static int dist;
    public int solution(String dirs) {
        int answer = 0;
        int[][] map = new int[11][11];
        
        boolean[][][] v = new boolean[11][11][4];
        
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{5,5});
        
        
        for(char idx : dirs.toCharArray()) {
            int[] cur = q.poll();
            int[] next = move(cur[0], cur[1], idx);
            if(next[0] < 0 || next[0] >= 11 || next[1] < 0 || next[1] >= 11) {
                q.offer(cur);
                continue;
            }
            
            if(!v[next[0]][next[1]][dist]) {
                answer++;
            }
            q.offer(new int[]{next[0],next[1]});
        
            v[next[0]][next[1]][dist] = true;   //이전 지점 어디서온지 체크
            v[cur[0]][cur[1]][prev] = true;
            
        }
        
        
        return answer;
    }

    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};
    static int prev;
    static int[] move(int x, int y, char idx) {
        if(idx == 'U') {
            dist = 0;
            prev = 1;
            return new int[]{x-1, y};
        }
        else if(idx == 'D') {
            dist = 1;
            prev = 0;
            return new int[]{x+1, y};
        }
        else if(idx == 'R') {
            dist = 2;
            prev = 3;
            return new int[]{x,y+1};
        }
        else {
            dist = 3;
            prev = 2;
            return new int[]{x,y-1};
        }
    }
}