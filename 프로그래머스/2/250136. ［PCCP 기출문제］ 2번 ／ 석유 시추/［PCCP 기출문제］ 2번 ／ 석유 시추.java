import java.util.*;
class Solution {
    static int sec = 2;
    public int solution(int[][] land) {
        int res = 0;
        
        /**
        정확성 100/100, 효율성 500/500
        500 ^ 2 250,000
        bfs 영역 구하기 -> 석유 량 체크
        각 영역 번호 메기고 Map으로 각 영역 번호 당 크기 저장
        
        한 열 마다 확인하며, 체크
        **/
        Map<Integer, Integer> map = new HashMap<>();
        
        for(int i=0; i<land.length; i++) {
            for(int j=0; j<land[i].length; j++) {
                if(land[i][j] == 1) {
                    //구하지 못한 영역이 있을 경우
                    int cnt = bfs(i,j, land);
                    map.put(sec, cnt);
                    sec++;
                }
            }
        }
        
        //탐색 시작
        
        for(int i=0; i<land[0].length; i++) {
            int sum = 0;
            boolean[] v = new boolean[sec];
            for(int j=0; j<land.length; j++) {
                if(land[j][i] != 0) {
                    if(!v[land[j][i]]) {
                        v[land[j][i]] = true;
                        sum += map.get(land[j][i]);
                    }
                }
            }
            res = Math.max(res, sum);
        }
        
        return res;
    }
    
    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};
    static int bfs(int x, int y, int[][] land) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x,y});
        land[x][y] = sec;
        int cnt = 1;
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            
            for(int i=0; i<4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if(nx<0 || ny<0 || nx>=land.length || ny>=land[0].length || land[nx][ny] != 1) continue;
                land[nx][ny] = sec;
                cnt++;
                q.offer(new int[]{nx,ny});
            }
        }
        return cnt;
    }
}