import java.util.*;
class Solution {
    static int res;
    static boolean[] v;
    public int solution(int n, int[][] wires) {
        res = Integer.MAX_VALUE; //결과값 초기화
        for(int i=0; i<wires.length; i++) {
            v = new boolean[n+1];
            int temp = wires[i][1];
            wires[i][1] = -1;
            int cntA = bfs(1, wires); //첫번째부터 탐색
            int cntB = n - cntA; //나머지 그룹은 n-A
            wires[i][1] = temp; //백트래킹
            res = Math.min(res, Math.abs(cntA - cntB)); //최소값 갱신
        }
        return res;
    }
    
    private static int bfs(int start, int[][] wires) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        v[start] = true;
        int cnt = 1;
        
        while(!q.isEmpty()) {
            int idx = q.poll();
            
            for(int i=0; i<wires.length; i++) {
                if(wires[i][1] == -1) continue;
                //양방향 그래프이기 때문에 앞 뒤로 확인.
                if(wires[i][0] == idx || wires[i][1] == idx) {
                    if(!v[wires[i][1]]) {
                    q.offer(wires[i][1]);
                    v[wires[i][1]] = true;
                    cnt++;
                    }
                    if(!v[wires[i][0]]) {
                    q.offer(wires[i][0]);
                    v[wires[i][0]] = true;
                    cnt++;
                    }
                }
            }
        }
        return cnt;
    }
}