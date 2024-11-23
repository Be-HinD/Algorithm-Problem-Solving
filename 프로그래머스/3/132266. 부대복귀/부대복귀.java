import java.util.*;
class Solution {
    static List<List<Integer>> list;
    static int[] dist;
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        /**
         각 지역은 유일한 번호로 구분
         걸리는 시간은 모두 1로 동일
         최단시간에 부대로 복귀
         임무의 시작 때와 다르게 되돌아오는 경로가 없어져 복귀가 불가능한 부대원도 있을 수 있습니다.
         **/
        
        list = new ArrayList<>();
        for(int i=0; i<n; i++) list.add(new ArrayList<>());
        
        for(int i=0; i<roads.length; i++) {
            int[] road = roads[i];
            list.get(road[0]-1).add(road[1]-1);
            list.get(road[1]-1).add(road[0]-1);
        }
        
        dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[destination-1] = 0;
        
        dijk(n, destination-1);
    
        
        int[] res = new int[sources.length];
        for(int i=0; i<sources.length; i++) {
            res[i] = dist[sources[i]-1] == Integer.MAX_VALUE ? -1 : dist[sources[i]-1];
        }
        return res;
    }
    
    private static void dijk(int n, int start) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2) -> o1[1] - o2[1]);
        pq.offer(new int[]{start,0});
        
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            
            if(dist[cur[0]] < cur[1]) continue;
            
            for(int next : list.get(cur[0])) {
                if(dist[next] > cur[1]+1) {
                    pq.offer(new int[]{next, cur[1]+1});
                    dist[next] = cur[1]+1;
                }
            }
        }
        
        return;
    }
}