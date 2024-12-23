import java.util.*;
class Solution {
    static int v; //홈의 개수
    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = true;
        
        for(int i=0; i<lock.length; i++) {
            for(int j=0; j<lock.length; j++) {
                if(lock[i][j] == 0) v++;
            }
        }
        
        //돌린 결과값에 따라 비교 진행
        for(int i=0; i<4; i++) {
            int[][] temp = rotate(key);
            key = temp;
            
            // System.out.println("변환 후");
            // for(int j=0; j<key.length; j++) {
            //     System.out.println(Arrays.toString(key[j]));
            // }
            boolean flag = checkValitdate(key, lock);
            if(flag) return true;
        }
        
        return false;
    }
    
    static int[][] rotate(int[][] arr) {
        int n = arr.length;
        int[][] temp = new int[n][n];
        int roop = n/2;
        
        for(int r=0; r<roop; r++) {
            for(int i=r; i<n-r; i++) {
                temp[r][i] = arr[(n-1)-i][r]; //상단 가로
                temp[i][r] = arr[(n-1)-r][i]; //왼쪽 세로
                temp[i][(n-r)-1] = arr[r][i]; //오른쪾 세로
                temp[(n-r)-1][i] = arr[(n-1)-i][(n-r)-1]; //하단 가로
            }
        }
        if(n % 2 != 0) {
            temp[n/2][n/2] = arr[n/2][n/2];
        }
        return temp;
    }
    
    static boolean checkValitdate(int[][] diff, int[][] origin) {
        int n = origin.length;
        for(int i=-n; i<=n; i++) {
            for(int j=-n; j<=n; j++) {
                // i, j가 시작점
                // System.out.println("시작점 -> " + i + " : " + j);
                int cnt = v;
                boolean flag = false;
                
                for(int k=0; k<diff.length; k++) {
                    for(int l=0; l<diff.length; l++) {
                        int nx = i+k;
                        int ny = j+l;
                        if(nx<0 || ny<0 || nx>=n || ny>=n) continue;
                        
                        if(origin[nx][ny] == 1 && diff[k][l] == 1) {
                            //겹치는 경우
                            flag = true;
                            break;
                        }
                        if(origin[nx][ny] == 0 && diff[k][l] == 1) {
                            //메꾸는 경우
                            cnt--;
                        }
                    }
                    if(flag) break;
                }
                if(!flag && cnt == 0) return true; //비교가 끝난 후
            }
        }
        return false;
    }
}