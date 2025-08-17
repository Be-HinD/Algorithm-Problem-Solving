import java.io.*;
import java.util.*;

//BOJ_2578
public class Main {
    static int[][] bingo = new int[5][5];
    static Map<Integer, int[]> index = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        /**
         * 극한의 구현
         * 최적화는 생각안남
         * **/

        for(int i=0; i<5; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<5; j++) {
                int idx = Integer.parseInt(st.nextToken());
                bingo[i][j] = idx;
                index.put(idx, new int[]{i,j});
            }
        }

        int res = 0;
        for(int i=0; i<5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                res++;
                int idx = Integer.parseInt(st.nextToken());
                int[] point = index.get(idx);
                bingo[point[0]][point[1]] = -1;
                if(checkBingo() > 2) {
                    System.out.println(res);
                    return;
                }
            }
        }

    }

    static int checkBingo() {
        int bingoCnt = 0;
        // TODO : 가로
        for(int i=0; i<5; i++) {
            boolean flag = true;
            for(int j=0; j<5; j++) {
                if(bingo[i][j] != -1) {
                    flag = !flag;
                    break;
                }
            }
            if(flag) bingoCnt++;
        }
        
        // TODO : 세로
        for(int i=0; i<5; i++) {
            boolean flag = true;
            for(int j=0; j<5; j++) {
                if(bingo[j][i] != -1) {
                    flag = !flag;
                    break;
                }
            }
            if(flag) bingoCnt++;
        }
        
        // TODO : 대각선
        boolean flag = true;
        for(int i=0; i<5; i++) {
            if(bingo[i][i] != -1) {
                flag = !flag;
                break;
            }
        }
        if(flag) bingoCnt++;
        
        flag = true;
        //0,4 1,3 2,2 3,1 4,0
        for(int i=0; i<5; i++) {
            if(bingo[i][4-i] != -1) {
                flag = !flag;
                break;
            }
        }
        if(flag) bingoCnt++;
        
        return bingoCnt;
    }
}