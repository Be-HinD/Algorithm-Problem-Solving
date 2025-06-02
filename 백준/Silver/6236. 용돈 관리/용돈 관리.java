import java.io.*;
import java.util.*;

//BOJ_5913
public class Main {
    static int n, m, min, res;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n];
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            min = Math.max(min, arr[i]);
        }

        res = Integer.MAX_VALUE;

        binarySearch();

        System.out.println(res);
    }

    private static void binarySearch() {
        int low = min;
        int high = Integer.MAX_VALUE;

        while(low < high) {
            final int mid = low + (high - low) / 2; // 인출할 금액 K

            int cnt = 1, coin = mid;
            boolean flag = true;
            for(int i=0; i<arr.length; i++) {
                if(arr[i] > coin) {
                    cnt++;
                    coin = mid - arr[i];
                    if(cnt > m) {
                        flag = false;
                        break;
                    }
                } else {
                    coin -= arr[i];
                }
            }

            if(!flag) {
                low = mid + 1;
            }
            else {
                res = Math.min(res, mid);
                high = mid;
            }
        }
    }
}