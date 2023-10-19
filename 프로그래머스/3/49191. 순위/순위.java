import java.util.*;
class Solution {
    static int[][] map;
    static boolean[] vv;
    public int solution(int n, int[][] results) {
        int answer = 0;
        
        map = new int[n][n];
        for(int i=0; i<results.length; i++) {
            int x = results[i][0] - 1;
            int y = results[i][1] - 1;
            map[x][y] = 1; //앞 노드가 이긴 사람
        }
        for(int i=0; i<n; i++) {
            vv = new boolean[n];
            winBfs(i, map, n);
            loseBfs(i, map, n); //질 때의 경우
            boolean flag = true;
            for(int j=0; j<vv.length; j++) {
                if(!vv[j]) flag = false;
            }
            if(flag) answer++;
        }
        return answer;
    }
    private static void winBfs(int x, int[][] m, int n) {
        Queue<Integer> q = new ArrayDeque<>();
        boolean[][] v = new boolean[n][n];
        vv[x] = true;
        for(int i=0; i<n; i++) {
            if(x == i) continue;
            if(m[x][i] == 1 && !v[x][i]) { //내가 이긴 사람 찾기
                q.offer(i);
                vv[i] = true;
            }
        }
        while(!q.isEmpty()) { //내게 진 사람들이 이기는 사람 찾기
            int idx = q.poll();
            for(int i=0; i<n; i++) {
                if(idx == i) continue;
                if(m[idx][i] == 1 && !v[idx][i]) { //이긴 사람을 추가
                    vv[i] = true;
                    q.offer(i);
                    v[idx][i] = true;
                }
            }
        }
        return;
    }
    
    private static void loseBfs(int x, int[][] m, int n) {
        Queue<Integer> q = new ArrayDeque<>();
        boolean[][] v = new boolean[n][n];
        vv[x] = true;
        for(int i=0; i<n; i++) {
                if(x == i) continue;
                if(m[i][x] == 1 && !v[i][x]) { //나를 이기는 사람 찾기
                    q.offer(i);
                    vv[i] = true;
                    v[x][i] = true;
                }
            }
        
        while(!q.isEmpty()) {
            int idx = q.poll();
            for(int i=0; i<n; i++) { //나를 이긴 사람에게 이긴 사람 찾기
                if(idx == i) continue;
                if(m[i][idx] == 1 && !v[i][idx]) {
                    q.offer(i);
                    vv[i] = true;
                    v[i][idx] = true;
                }
            }
        }
        return;
    }
}