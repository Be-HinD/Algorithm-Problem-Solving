import java.util.*;
class Solution {
    public int solution(int[][] maps) {
        int answer = 0;
        
        int[] dx = new int[]{1,-1,0,0};
        int[] dy = new int[]{0,0,1,-1};
        
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0,0,0});
        boolean[][] v = new boolean[maps.length][maps[0].length];
        v[0][0] = true;
        while(!q.isEmpty()) {
            int[] idx = q.poll();
            if(idx[0] == maps.length-1 && idx[1] == maps[0].length-1) {
                answer = idx[2] +1;
                break;
            }
            for(int i=0; i<4; i++) {
                int nx = idx[0] + dx[i];
                int ny = idx[1] + dy[i];
                if(nx<0 || ny<0 || nx>=maps.length || ny>=maps[0].length || v[nx][ny]) continue;
                if(maps[nx][ny] == 0) continue;
                q.offer(new int[]{nx,ny,idx[2]+1});
                v[nx][ny] = true;
                
            }
        }
        if(answer == 0)  return -1;
        return answer;
    }
}