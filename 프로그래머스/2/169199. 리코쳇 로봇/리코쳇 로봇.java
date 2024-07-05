import java.util.*;
class Solution {
    static int N, M;
    static int[] start, whole;
    static char[][] map;
    public int solution(String[] board) {
        int answer = 0;
        N = board.length;
        M = board[0].length();
        map = new char[N][M];
        
        /**
        HashMap에 장애물, 출구 좌표만 넣고
        좌표 연산 후 해당 좌표에 대한 도착지점에 대한 
        **/
        
        //2차원 배열 초기화
        for(int i=0; i<N; i++) {
            String input = board[i];
            for(int j=0; j<M; j++) {
                char idx = input.charAt(j);
                map[i][j] = idx;
                if(map[i][j] == 'R') {
                    start = new int[2];
                    start[0] = i;
                    start[1] = j;
                }
                else if(map[i][j] == 'G') {
                    whole = new int[2];
                    whole[0] = i;
                    whole[1] = j;
                }
            }
        }
        
        int res = bfs();
        
        return res == 0 ? -1 : res;
    }
    
    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};
    static int bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{start[0], start[1], 0});
        boolean[][][] v = new boolean[N][M][4];
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            
            if(cur[0] == whole[0] && cur[1] == whole[1]) return cur[2];
            for(int i=0; i<4; i++) {
                
                //동서남북방향으로 진출 가능한지, 가능하다면 최종 좌표를 구한 후 Queue 추가
                
                int[] now = isValidate(cur[0], cur[1], i);
                if(now[0] == -1) continue; //가지 못하는 곳
                
                if(v[now[0]][now[1]][i]) continue;
                q.offer(new int[]{now[0], now[1], cur[2] + 1});
                v[now[0]][now[1]][i] = true;
                
                
            }
        }
        
        return -1;
    }
    
    static int[] isValidate(int x, int y, int dist) {
        boolean flag = false;
        
        while(true) {
            x += dx[dist];
            y += dy[dist];
            if(x<0 || y<0 || x>=N || y>=M || map[x][y] == 'D') break;
            flag = true;
        }
        
        if(!flag) {
            return new int[]{-1,-1};
        }
        return new int[]{x - dx[dist], y - dy[dist]};
    }
}