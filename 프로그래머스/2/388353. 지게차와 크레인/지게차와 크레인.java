import java.util.*;

/**
접근법
: 완전탐색
: 지게차의 경우 꺼낼 수 있는 컨테이너를 기록 -> 이후 한번에 제거 (상태값 변경)
--> List로 관리
**/

class Solution {
    static char[][] map;
    public int solution(String[] storage, String[] requests) {
        
        // map 초기화
        map = new char[storage.length+2][storage[0].length()+2];
        
        for(int i=1; i<map.length-1; i++) {
            for(int j=1; j<map[0].length-1; j++) {
                map[i][j] = storage[i-1].charAt(j-1);
            }
        }
        
        for(String req : requests) {
            if(req.length() == 2) { //크레인
                removeCrain(req.charAt(0));
            }
            else {  //지게차
                remove(req.charAt(0));
            }
        }
        
        int res = 0;
        for(int i=1; i<map.length-1; i++) {
            for(int j=1; j<map[0].length-1; j++) {
                if(map[i][j] != '\0') res++;
            }
        }
        
        return res;
    }
    
    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};
    
    // 외부 조건 잘못보고 급하게 bfs로 전환
    static boolean isPossible(int x, int y) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x,y});
        boolean[][] v = new boolean[map.length][map[0].length];
        v[x][y] = true;
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            for(int i=0; i<4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if(nx<0 || ny<0 || nx>=map.length || ny>=map[0].length) return true;
                if(v[nx][ny] || map[nx][ny] != '\0') continue;
                v[nx][ny] = true;
                q.offer(new int[]{nx,ny});
            }
        }
        
        return false;
    }
    
    static void remove(char idx) {

        List<int[]> candidate = new ArrayList<>();
        for(int i=1; i<map.length-1; i++) {
            for(int j=1; j<map[0].length-1; j++) {
                if(map[i][j] == idx && isPossible(i,j)) {
                    candidate.add(new int[]{i,j});
                }
            }
        }
        
        for(int[] cur : candidate) {
            map[cur[0]][cur[1]] = '\0';
        }
    }
    
    static void removeCrain(char idx) {
        
        List<int[]> candidate = new ArrayList<>();
        for(int i=1; i<map.length-1; i++) {
            for(int j=1; j<map[0].length-1; j++) {
                if(map[i][j] == idx) {
                    candidate.add(new int[]{i,j});
                }
            }
        }
        
        for(int[] cur : candidate) {
            map[cur[0]][cur[1]] = '\0';
        }
    }
}