import java.util.*;
class Solution {
    static int p;
    static char[][] map;
    public int[] solution(String[] maps) {
        
        /**
        영역 나누기 문제랑 동일.
        bfs로 영역을 마킹 (인덱스 1부터 시작)
        그 후 각 영역 별 총 합을 bfs로 구한 뒤 결과 출력 ㄱ
        **/
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        map = new char[maps.length][maps[0].length()];
        
        // 2차원 배열로 복사
        for(int i=0; i<map.length; i++) {
            String value = maps[i];
            for(int j=0; j<value.length(); j++) {
                map[i][j] = value.charAt(j);
            }
        }
        
        for(int i=0; i<map.length; i++) {
            for(int j=0; j<map[0].length; j++) {
                if(map[i][j] != 'X') {
                    pq.offer(calcSum(i,j));
                }
            }
        }
        
        if(!pq.isEmpty()) {
            int[] answer = new int[pq.size()];
            int z = 0;
            while(!pq.isEmpty()) {
                answer[z++] = pq.poll();
            }
            return answer;
        }
        return new int[]{-1};
    }
    
    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};
    static int calcSum(int x, int y) {
        Queue<int[]> q = new ArrayDeque<>();
        // boolean[][] v = new boolean[map.length][map[0].length];
        // v[x][y] = true;
        q.offer(new int[]{x,y});
        int res = map[x][y] - '0';
        map[x][y] = 'X';
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            for(int i=0; i<4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if(nx<0 || ny<0 || nx>=map.length || ny>=map[0].length) continue;
                if(map[nx][ny] != 'X') {
                    res += map[nx][ny] - '0';
                    map[nx][ny] = 'X';
                    // v[nx][ny] = true;
                    q.offer(new int[]{nx, ny});
                }
            }
            
        }
        
        return res;
    }
}