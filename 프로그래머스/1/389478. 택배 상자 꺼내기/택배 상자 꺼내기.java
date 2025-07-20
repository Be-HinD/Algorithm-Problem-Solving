import java.util.*;
class Solution {
    public int solution(int n, int w, int num) {
        /**
        접근법
        : boolean 배열로 구성
        : 각 인덱스 별 좌표를 저장 (Key - Value)
        : num에 해당하는 좌표에서 x--
        **/
        
        boolean[][] map = new boolean[(n/w) + 1][w];
        
        int x = map.length-1;
        int y = 0;
        int floor = 0;
        int mark = 1;
        Map<Integer, int[]> idx = new HashMap<>();
        
        while(n-->0) {
            if(y == w || y<0) {
                x--;
                floor++;
                if((floor&1)==0) y = 0;
                else y--;
            }
            map[x][y] = true;
            idx.put(mark++, new int[]{x,y});
            
            if((floor&1) == 0) y++;
            else y--;
        }
        
        // for(int i=0; i<map.length; i++) {
        //     for(int j=0; j<map[i].length; j++) {
        //         System.out.print(map[i][j]?1:0);
        //         System.out.print(" ");
        //     }
        //     System.out.println();
        // }
        
        int[] point = idx.get(num);
        
        int res = 0;
        while(point[0] >= 0) {
            if(map[point[0]][point[1]]) res++;
            point[0]--;
        }
        return res;
    }
}