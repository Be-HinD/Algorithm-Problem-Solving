import java.util.*;
class Solution {
    static int N;
    static int[][] map;
    static boolean[][] v;
    public int solution(int[][] board) {
        
        N = board.length;
        map = board;
        
        
        return Dijkstra();
    }
    
    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};

    private static int Dijkstra() {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2) -> o1[3] - o2[3]);
        pq.offer(new int[]{0,0,-1,0});
        int[][][] dist = new int[N][N][4];
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                Arrays.fill(dist[i][j], Integer.MAX_VALUE);
            }
        }
        
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            
            if(cur[0] == N-1 && cur[1] == N-1) {
                return cur[3];
            }
            
            for(int i=0; i<4; i++) {
            int nx = cur[0] + dx[i];
            int ny = cur[1] + dy[i];
            if(!isRange(nx,ny) || map[nx][ny] == 1) continue;
                //진행 가능 방향
                if(cur[2] == -1 || cur[2] == i) {
                    if(dist[nx][ny][i] > cur[3]+100) {
                        pq.offer(new int[]{nx,ny,i,cur[3]+100});
                        dist[nx][ny][i] = cur[3]+100;
                    }
                }
                else {
                    if(dist[nx][ny][i] > cur[3]+600) {
                        pq.offer(new int[]{nx,ny,i,cur[3]+600});
                        dist[nx][ny][i] = cur[3]+600;
                    }
                }
                
            }
        }
        
        return -1;
    }
    
    private static boolean isRange(int x, int y) {
        if(x<0 || y<0 || x>=N || y>=N) return false;
        return true;
    }
}