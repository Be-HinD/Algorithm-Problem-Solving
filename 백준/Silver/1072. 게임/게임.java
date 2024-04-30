import java.io.*;
import java.util.*;

//BOJ_1072 게임
public class Main {
    static int X, Y, res;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        /**
         * 현재 승률을 구하고 ?
         * 승률은 올라갈 수 밖에 없으니까 winRate가 1이라도 올라갈 수 있다면 값을 구해야함.
         * **/

        int winRate =  (int) ((long)Y * 100 / X);
        res = -1;
        binSearch(winRate);

        System.out.println(res);
    }

    private static void binSearch(int key) {
        int low = 0;
        int high = (int) 1e9;

        while(low <= high) {
            final int mid = (low + high) / 2;
            //승률 계산
            int x = X + mid;
            int y = Y + mid;
            int winRate = (int) ((long)y * 100 / x);
            if(winRate != key) {
                res = mid;
                high = mid - 1;
            }
            else {
                low = mid + 1;
            }
        }
    }
}