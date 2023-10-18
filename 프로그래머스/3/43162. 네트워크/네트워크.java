import java.util.*;
class Solution {
    static boolean[] v;
    public int solution(int n, int[][] computers) {
        int answer = 0;
        v = new boolean[n]; //컴퓨터 개수 길이 초기화
        for(int i=0; i<n; i++) { //각 컴퓨터 별로 탐색
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
            v[idx] = true; //방문체크
            for(int i=0; i<n; i++) {
                if(idx == i) continue; //전처리
                if(c[idx][i] == 1) { //연결되있을 경우
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