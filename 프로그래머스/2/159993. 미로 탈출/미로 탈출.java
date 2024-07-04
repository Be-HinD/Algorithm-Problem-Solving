import java.util.*;
class Solution {
    static int s, e, sl, el, sg, eg, res;
    static char[][] map;
    public int solution(String[] maps) {
        /**
        레버까지 최소거리 + 레버에서 탈출구까지 최소거리
        **/
        
        map = new char[maps.length][maps[0].length()];
        for(int i=0; i<maps.length; i++) {
            String idx = maps[i];
            for(int j=0; j<idx.length(); j++) {
                map[i][j] = idx.charAt(j);
                if(map[i][j] == 'S') {
                    s = i;
                    e = j;
                }
                else if(map[i][j] == 'L') {
                    sl = i;
                    el = j;
                }
                else if(map[i][j] == 'E') {
                    sg = i;
                    eg = j;
                }
            }
        }
        
        int lever = bfs(s,e,sl,el);
        if(lever == -1) return -1;
        int hole = bfs(sl,el,sg,eg);
        if(hole == -1) return -1;
        
        return lever + hole;
    }
    
    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};
    static int bfs(int x, int y, int ts, int ty) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x,y,0});
        boolean[][] v = new boolean[map.length][map[0].length];
        v[x][y] = true;
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            
            if(cur[0] == ts && cur[1] == ty) {
                return cur[2];
            }
            for(int i=0; i<4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if(nx<0 || ny<0 || nx>=map.length || ny>=map[0].length || v[nx][ny]) continue;
                if(map[nx][ny] == 'X') continue;
                q.offer(new int[]{nx,ny,cur[2]+1});
                v[nx][ny] = true;
            }
        }
        
        return -1;
    }
}