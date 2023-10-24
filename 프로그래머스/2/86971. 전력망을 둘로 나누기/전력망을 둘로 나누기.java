import java.util.*;
class Solution {
    static int res;
    static boolean[] v;
    public int solution(int n, int[][] wires) {
        int answer = -1;
        res = Integer.MAX_VALUE;
        for(int i=0; i<wires.length; i++) {
            v = new boolean[n+1];
            int temp = wires[i][1];
            wires[i][1] = -1;
            int cntA = bfs(1, wires); //첫번째부터 탐색
            if(i == 5) {
                for(int k=0; k<v.length; k++) {
                    if(v[k]) System.out.print(1 + " ");
                    else System.out.print(0 + " ");
                }
            }
            System.out.println();
            int cntB = n - cntA;
            wires[i][1] = temp; //백트래킹
            System.out.println(cntA + " " + cntB);
            res = Math.min(res, Math.abs(cntA - cntB));
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