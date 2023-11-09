import java.util.*;
class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        
        Arrays.sort(routes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        
        int camera = Integer.MIN_VALUE;
        for(int i=0; i<routes.length; i++) {
            if(camera < routes[i][0]) {
                camera = routes[i][1];
                answer++;
            }
        }
        
        
        
        //15 13 5 3
        //System.out.println(routes[0][1]);
        return answer;
    }
}