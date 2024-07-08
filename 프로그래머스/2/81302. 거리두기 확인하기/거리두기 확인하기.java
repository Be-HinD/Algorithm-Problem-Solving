import java.util.*;
class Solution {
    static char[][] map;
    static List<int[]> list;
    public int[] solution(String[][] places) {
        
        /**
        맨헤튼 거리 = 2 -> bfs탐색 depth < 2
        **/
        int[] res = new int[5];
        Arrays.fill(res, 1);
        // 5 By 5 배열
        for(int i=0; i<places.length; i++) {
            String[] arr = places[i];
            map = new char[5][5];
            list = new ArrayList<>();
            for(int j=0; j<arr.length; j++) {
                String idx = arr[j];
                for(int k=0; k<5; k++) {
                    map[j][k] = idx.charAt(k);
                    if(map[j][k] == 'P') list.add(new int[]{j,k});
                }
            }
            //대기실 형성 후 대기실 안의 P의 위치들에 대해 bfs 수행
            boolean flag = true;
            for(int[] idx : list) {
                if(!bfs(idx)) {
                    flag = false;
                    break;
                }
            }
            
            if(!flag) {
                res[i] = 0;
            }
        }
        
        return res;
    }
    
    static int[] dx = new int[]{0,0,1,-1};
    static int[] dy = new int[]{1,-1,0,0};
    static boolean bfs(int[] point) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{point[0],point[1], 0});
        boolean[][] v = new boolean[5][5];
        v[point[0]][point[1]] = true;
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            
            if(cur[2] >= 2) continue;
            
            for(int i=0; i<4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if(nx<0 || ny<0 || nx>=5 || ny>=5 || v[nx][ny]) continue;
                
                if(map[nx][ny] == 'X') continue;
                if(map[nx][ny] == 'P') {
                    return false;
                }
                v[nx][ny] = true;
                q.offer(new int[]{nx,ny,cur[2] + 1});
            }
        }
        
        return true;
    }
}