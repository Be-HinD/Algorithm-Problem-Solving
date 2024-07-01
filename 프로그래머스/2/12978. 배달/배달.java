import java.util.*;
class Solution {
    static class Node {
        int v;
        int w;
        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
    static List<List<Node>> list;
    public int solution(int N, int[][] road, int K) {
        int answer = 0;

        /**
        1. 인접리스트 초기화
        2. 1번에서 데이크스트라
        3. dist배열에서 K 레퍼 체크
        임의의 두 마을간에 항상 이동 가능한 경로가 존재합니다. 이긴한데
        데이크스트라 풀이로는 상관없지 않나??.. 어차피 최소 경로 비용 구하는 풀이인데
        **/

        list = new ArrayList<>();
        for(int i=0; i<=N; i++) list.add(new ArrayList<>());
        
        for(int i=0; i<road.length; i++) {
            int[] cur = road[i];
            if(!list.get(cur[0]).contains(cur[1])) {
                list.get(cur[0]).add(new Node(cur[1], cur[2]));
                list.get(cur[1]).add(new Node(cur[0], cur[2]));
            }
            else {
                if(cur[2] < list.get(cur[0]).get(cur[1]).w) {
                    //기존보다 현재 값이 더 최소일 경우
                    list.get(cur[0]).add(new Node(cur[1], cur[2]));
                    list.get(cur[1]).add(new Node(cur[0], cur[2]));
                }
            }
            
        }
        
        
        return Dijkstra(N, K);
    }
    
    static int Dijkstra(int n, int limit) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[]{1, 0});  //1번 city에서 start
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            
            for(Node idx : list.get(cur[0])) {
                int totalDist = idx.w + cur[1];
                if(totalDist < dist[idx.v]) {
                    pq.offer(new int[]{idx.v, totalDist});
                    dist[idx.v] = totalDist;
                }
            }
        }
        
        int res = 0;
        for(int idx : dist) {
            if(idx <= limit) res++;
        }
        return res;
    }
}