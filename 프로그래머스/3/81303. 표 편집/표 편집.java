import java.util.*;

class Solution {
    static int[] prev, nxt;
    public String solution(int n, int k, String[] cmd) {
        
        prev = new int[n+2];
        nxt = new int[n+2];
        
        for(int i=1; i<=n; i++) {
            prev[i] = i-1;
            nxt[i] = i+1;
        }
        
        k++; //배열 구현 제약
        
        Stack<Integer> del = new Stack<>();
        
        for(String cur : cmd) {
            StringTokenizer st = new StringTokenizer(cur);
            
            String command = st.nextToken();
            if(command.equals("U")) {
                int up = Integer.parseInt(st.nextToken());
                for(int i=0; i<up; i++) {
                    k = prev[k];
                }
            }
            else if(command.equals("D")) {
                int down = Integer.parseInt(st.nextToken());
                for(int i=0; i<down; i++) {
                    k = nxt[k];
                }
            }
            else if(command.equals("C")) {  //삭제
                del.add(k);
                prev[nxt[k]] = prev[k];
                nxt[prev[k]] = nxt[k];
                if(nxt[k] == n+1) k = prev[k];
                else k = nxt[k];
            }
            else {  //복구
                int re = del.pop();
                prev[nxt[re]] = re;
                nxt[prev[re]] = re;
            }
        }
        
        StringBuilder res = new StringBuilder();
        for(int i=0; i<n; i++) res.append("O");
        
        while(!del.isEmpty()) {
            int delIdx = del.pop();
            res.replace(delIdx-1, delIdx, "X");
        }
        
        return res.toString();
    }
}