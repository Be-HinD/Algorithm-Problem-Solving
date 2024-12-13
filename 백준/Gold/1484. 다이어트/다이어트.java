import java.io.*;
import java.util.*;

//BOJ_1484
class Main {
    static int G;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
        /**
         * 접근법
         * 특정 몸무게 i^2에서 특정 무게 j^2를 뻇을 때 G가 나오는 경우를 모두 탐색
         * **/

        G = Integer.parseInt(br.readLine()); //15

        long[] arr = new long[100055];

        for(int i=1; i<arr.length; i++) {
            arr[i] = (long) Math.pow(i, 2);
        }

        int l = 1, r = 1, cnt = 0;

        StringBuilder sb = new StringBuilder();
        while(l<=r && r<arr.length) {
            long sum = arr[r] - arr[l];
            if(sum == G) {
                sb.append(r).append("\n");
                cnt++;
            }
            if(sum > G) {
                l++;
            }
            else {
                r++;
            }
        }
        System.out.println(cnt == 0 ? -1 : sb);


    }
}