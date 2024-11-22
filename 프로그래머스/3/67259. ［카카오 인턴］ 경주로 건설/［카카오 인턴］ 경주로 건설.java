/**
키워드
0 또는 1
0은 칸이 비어 있음
1은 해당 칸이 벽으로 채워져 있음
도착점은 (N-1, N-1) 칸
상, 하, 좌, 우로 인접한 두 빈 칸을 연결하여 건설
인접한 두 빈 칸을 상하 또는 좌우로 연결한 경주로를 직선 도로 100원
직선 도로가 서로 직각으로 만나는 지점을 코너 500원
최소 비용
접근법
board = 25이하 -> 완탐 O(N^6) 까지 가능
항상 경주로 건설 가능 형태
출발, 도착 == 항상 0
그리디 : 최대한 직선만 활용해서 건설
bfs 탐색
- 가는방향 그대로 : 100원 추가
- 가는방향과 다르면(코너) : 500원 추가
모든 방향 탐색이 진행되야 함으로 DFS 모든 경로 탐색 및 최소값 갱신

----
N = 25, dfs = O(2^N) 시간초과
dfs -> 다익스트라 변경 : 80점 -> 88점
**/

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
            
            if(cur[2] != -1) {
                if(dist[cur[0]][cur[1]][cur[2]] < cur[3]) continue;

                dist[cur[0]][cur[1]][cur[2]] = cur[3];
            }
            
            for(int i=0; i<4; i++) {
            int nx = cur[0] + dx[i];
            int ny = cur[1] + dy[i];
            if(!isRange(nx,ny) || map[nx][ny] == 1) continue;
                //진행 가능 방향
                if(cur[2] == -1 || cur[2] == i) {
                    if(dist[nx][ny][i] > cur[3]+100) {
                        pq.offer(new int[]{nx,ny,i,cur[3]+100});
                    }
                }
                else {
                    if(dist[nx][ny][i] > cur[3]+600) {
                        pq.offer(new int[]{nx,ny,i,cur[3]+600});
                    }
                }
                
            }
        }
        
        return -1;
    }
//     private static void dfs(int x, int y, int d, int sum) {
//         if(sum > res) return; //백트래킹 추가 : 52점 -> 80점
        
//         if(x == N-1 && y == N-1) {
//             res = Math.min(res, sum);
//             return;
//         }
        
//         // d에 따라서 직선인지 코너인지
        
//         for(int i=0; i<4; i++) {
//             int nx = x + dx[i];
//             int ny = y + dy[i];
//             if(!isRange(nx,ny) || map[nx][ny] == 1 || v[nx][ny]) continue;

//             //진행 가능 방향
//             v[nx][ny] = true;
//             if(d == -1 || d == i) {
//                 dfs(nx, ny, i, sum + 100);
//             }
//             else {
//                 dfs(nx, ny, i, sum + 600);
//             }
            
//             v[nx][ny] = false;
//         }
//     }
    
    private static boolean isRange(int x, int y) {
        if(x<0 || y<0 || x>=N || y>=N) return false;
        return true;
    }
}