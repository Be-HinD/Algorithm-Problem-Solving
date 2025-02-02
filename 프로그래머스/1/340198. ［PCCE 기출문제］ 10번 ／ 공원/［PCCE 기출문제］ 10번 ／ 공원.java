class Solution {
    public int solution(int[] mats, String[][] park) {
        int answer = -1;
        int n = park.length;
        int m = park[0].length;
        
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                for(int k=0; k<mats.length; k++) {
                    if(search(i, j, mats[k], park)) {
                        answer = Math.max(answer, mats[k]);
                    }
                }
            }
        }
        
        return answer;
    }
    
    static boolean search(int x, int y, int mat, String[][] park) {
        
        for(int i=x; i<x+mat; i++) {
            for(int j=y; j<y+mat; j++) {
                if(i>=park.length || j>=park[0].length) return false;
                if(!"-1".equals(park[i][j])) return false;
            }
        }
        
        return true;
    }
}