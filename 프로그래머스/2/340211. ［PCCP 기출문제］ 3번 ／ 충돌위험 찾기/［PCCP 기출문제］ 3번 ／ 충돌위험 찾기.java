import java.util.*;
class Solution {
    static class Node {
        int x;
        int y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Node point = (Node) obj;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
        
    }
    static List<List<Node>> shortestRoute;
    static Map<Integer, Node> point;
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        
        point = new HashMap<>();
        
        int index = 1;
        for(int i=0; i<points.length; i++) {
            point.put(index++, new Node(points[i][0], points[i][1]));
        }
     
        for(int i=0; i<routes.length; i++) shortestRoute = new ArrayList<>();

        int longestDist = 0;
        for(int i=0; i<routes.length; i++) {
            List<Node> idx = findShortestRoute(routes[i]);
            shortestRoute.add(idx);
            longestDist = Math.max(longestDist, idx.size());
        }
        
        answer = findCount(longestDist);
        
        return answer;
    }
    
    private static int findCount(int longDist) {
        int cnt = 0;
        
        for(int i=0; i<longDist; i++) {
            Map<Node, Integer> map = new HashMap<>();
    
            for(List<Node> cur : shortestRoute) {
                if(cur.isEmpty()) continue;
                map.put(cur.get(0), map.getOrDefault(cur.get(0), 0) + 1);
                if(map.get(cur.get(0)) == 2) {
                    cnt++;
                }
                
                cur.remove(0);
            }
        }
        
        return cnt;
    }
    
    
    private static List<Node> findShortestRoute(int[] path) {
        List<Node> list = new ArrayList<>();
        
        Node start = point.get(path[0]);
        list.add(start);
        
        int sx = start.x;
        int sy = start.y;
        
        for(int i=1; i<path.length; i++) {
            Node next = point.get(path[i]);
            
            while(sx!=next.x || sy!=next.y) {
                if(sx != next.x) {
                    sx += (sx - next.x) < 0 ? 1 : -1;
                    list.add(new Node(sx,sy));
                    continue;
                }
                
                if(sy != next.y) {
                    sy += (sy - next.y) < 0 ? 1 : -1;
                    list.add(new Node(sx,sy));
                }
            }
        }
        
        
        return list;
    }
}