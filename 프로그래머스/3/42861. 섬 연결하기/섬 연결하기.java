import java.util.*;
class Solution {
    static List<List<int[]>> list;
    public int solution(int n, int[][] costs) {

        list = new ArrayList<>();
        for(int i=0; i<n; i++) list.add(new ArrayList<>());
        
        for(int[] edge : costs) {
            list.get(edge[0]).add(new int[]{edge[1],edge[2]});
            list.get(edge[1]).add(new int[]{edge[0],edge[2]});
        }
        
        int res = prim(n, costs[0][0]);
        
        return res;
    }
    
    private static int prim(int n, int start) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2) -> o1[1]-o2[1]);
        boolean[] v = new boolean[n];
        pq.offer(new int[]{start,0});
        int total = 0;
        
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            
            if(v[cur[0]]) continue;
            v[cur[0]] = true;
            total += cur[1];
            
            for(int[] next : list.get(cur[0])) {
                if(!v[next[0]]) {
                    pq.offer(next);
                }
            }
        }
        
        return total;
    }
}