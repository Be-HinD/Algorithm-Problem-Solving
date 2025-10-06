import java.io.*;
import java.util.*;

//BOJ_2792 (Parametric Search)
public class Main {
    static int n, m, res;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());   // 아이들의 수
        m = Integer.parseInt(st.nextToken());   // 색상의 수

        arr = new int[m];
        for(int i=0; i<m; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        res = Integer.MAX_VALUE;
        lower();

        System.out.println(res);

    }

    static void lower() {
        int low = 1;
        int high = Integer.MAX_VALUE;

        while(low<high) {
            final int mid = low + (high-low) / 2;
            // mid값만큼 n명 분배가 가능한지 여부 탐색
            int cnt = 0;
            for(int i=0; i<arr.length; i++) {
                cnt += (int) Math.ceil((double) arr[i] / mid);
            }

            if(cnt <= n) {
                high = mid;
                res = Math.min(res, mid);
            }
            else {
                low = mid + 1;
            }
        }
    }
}