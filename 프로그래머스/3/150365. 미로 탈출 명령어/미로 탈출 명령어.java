import java.util.*;
class Solution {
    static class Node {
        int x;
        int y;
        int cnt;
        StringBuilder sb;
        public Node(int x, int y, int cnt, StringBuilder sb) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.sb = sb;
        }
    }
    static int max;
    static String res;
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        res = "z";
        
        
        max = k;
        
        bfs(n,m,x-1,y-1,r-1,c-1);
        
        return res.equals("z") ? "impossible" : res;
    }
    
    static int[] dx = new int[]{1,0,0,-1};
    static int[] dy = new int[]{0,-1,1,0};
    static char[] arr = new char[]{'d','l','r','u'};
    //abcdefghijklmnopqrstu
    static void bfs(int n, int m, int x, int y, int r, int c) {
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(x,y,0,new StringBuilder()));
        boolean[][][] v = new boolean[n][m][2501];
        v[x][y][0] = true;
        while(!q.isEmpty()) {
            Node cur = q.poll();
            
            // System.out.println("?");
            if(cur.cnt > max) continue;
            if(cur.cnt == max) {
                if(cur.x == r && cur.y == c) {
                    // 결과값 갱신
                    if(res.compareTo(cur.sb.toString()) > 0) {
                        res = cur.sb.toString();
                    }
                }
                continue;
            }
            
            for(int i=0; i<4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if(nx<0 || ny<0 || nx>=n || ny>=m || v[nx][ny][cur.cnt+1]) continue;
                v[nx][ny][cur.cnt+1] = true;
                StringBuilder next = new StringBuilder(cur.sb);
                next.append(arr[i]);
                q.offer(new Node(nx,ny,cur.cnt+1,next));
            }
        }
    }
}