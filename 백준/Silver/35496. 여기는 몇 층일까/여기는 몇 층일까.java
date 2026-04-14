import java.io.*;
import java.util.*;

public class Main {
    static int n, q, res;
    static int[] apt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        /**
         * 플래튼 알고리즘 (평탄화)
         * */

        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        apt = new int[n];   //apt[i] = i번 건물의 0층 기준점 (0층 == B1 치환)
        apt[0] = 0;
        for(int i=0; i<n-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            apt[i+1] = b - a + apt[i];  //두 건물의 차이 + i번째 건물의 평탄화 값
        }

        StringBuilder sb = new StringBuilder();
        while(q-->0) {
            st = new StringTokenizer(br.readLine());

            int l = Integer.parseInt(st.nextToken()) - 1;
            int r = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken());   //기준점

            int res = apt[r] + (x - apt[l]);

            if(res <=0) {
                sb.append("B").append(Math.abs(res)+1).append("\n");  //l과 x의 오차만큼 r에 +- 적용
            }
            else {
                sb.append(res).append("\n");
            }

        }
        
        System.out.println(sb.toString());

    }
}