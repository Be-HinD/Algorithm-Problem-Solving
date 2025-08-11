import java.io.*;
import java.util.*;

//BOJ_14891 (45분)
public class Main {
    static int[][] wheel = new int[4][8];
    static boolean[] v;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        /**
         * 톱니바퀴는 배열로 관리 (구상)
         * rotation 로직 작성
         * wheel[2] : 오른쪽, wheel[6] : 왼쪽
         * 극 값 -> 0 : n, 1 : s
         * 재귀 형태로 구현 : 타겟 톱니 왼쪽, 오른쪽 탐색 -> 회전해야할 끝 톱니부터 변경 시작
         * **/

        for(int i=0; i<4; i++) {
            String input = br.readLine();
            for(int j=0; j<8; j++) {
                wheel[i][j] = input.charAt(j) - '0';
            }
        }

        int n = Integer.parseInt(br.readLine());
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int dist = Integer.parseInt(st.nextToken());
            v = new boolean[4];
            proc(start, dist);
        }

        int res = 0;
        for(int i=0; i<4; i++) {
            res += (int) (wheel[i][0] * Math.pow(2, i));
        }

        System.out.println(res);

    }

    static void proc(int num, int dist) {
        v[num] = true;
        // TODO : num 양쪽 톱니바퀴 탐색
        int left = num - 1;
        if(left > -1) {
            if(wheel[left][2] != wheel[num][6] && !v[left]) proc(left, dist * -1);
        }
        int right = num + 1;
        if(right < 4) {
            if(wheel[right][6] != wheel[num][2] && !v[right]) proc(right, dist * -1);
        }

        // TODO : 탐색 종료 이후 회전 진행
        rotation(num, dist);
    }

    static void rotation(int num, int dist) {
        if(dist == 1) { //시계 방향
            int temp = wheel[num][7];
            for(int i=7; i>0; i--) {
                wheel[num][i] = wheel[num][i-1];
            }
            wheel[num][0] = temp;
        }

        else {  //반시계 방향
            int temp = wheel[num][0];
            for(int i=0; i<7; i++) {
                wheel[num][i] = wheel[num][i+1];
            }
            wheel[num][7] = temp;
        }

    }
}