import java.util.*;
class Solution {
    static boolean[] v;
    public int solution(int n, int[][] computers) {
        int answer = 0;
        v = new boolean[n];
        for(int i=0; i<n; i++) {
            if(v[i]) continue;
            answer += bfs(i, n, computers);
        }
        return answer;
    }
    
    private static int bfs(int x, int n, int[][] c) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(x);
        while(!q.isEmpty()) {
            int idx = q.poll();
            v[idx] = true;
            for(int i=0; i<n; i++) {
                if(idx == i) continue;
                if(c[idx][i] == 1) {
                    //방문체크
                    c[idx][i] = 2;
                    c[i][idx] = 2;
                    q.offer(i);
                }
            }
        }
        return 1;
    }
}