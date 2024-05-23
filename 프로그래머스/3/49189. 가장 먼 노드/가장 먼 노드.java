import java.util.*;
class Solution {
    static int res;
    static List<List<Integer>> list;
    static int[] cnt;
    public int solution(int n, int[][] edge) {
        
        list = new ArrayList<>();
        
        for(int i=0; i<=n; i++) list.add(new ArrayList<>());
        
        for(int i=0; i<edge.length; i++) {
            int[] arr = edge[i];
            list.get(arr[0]).add(arr[1]);
            list.get(arr[1]).add(arr[0]);
        }
        
        
        // 최대 깊이 cnt 탐색 후 cnt만큼의 노드 개수 탐색
        cnt = new int[20055];
        int max = bfs(n);
        
        
        return cnt[max];
    }
    
    private int bfs(int n) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{1,1}); // cur, depth
        boolean[] v = new boolean[n+1];
        v[1] = true;
        int max = 1;
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            
            if(cur[1] > max) max = cur[1];
            cnt[cur[1]]++;
            for(int next : list.get(cur[0])) {
                if(v[next]) continue;
                q.offer(new int[]{next, cur[1] + 1});
                v[next] = true;
            }
            
            
        }
        
        return max;
    }
}