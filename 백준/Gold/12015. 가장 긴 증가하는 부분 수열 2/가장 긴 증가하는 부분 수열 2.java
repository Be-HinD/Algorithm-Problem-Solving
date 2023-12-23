import java.io.*;
import java.util.*;

//BOJ_12015 가장 긴 증가하는 부분 수열 2
public class Main {
    static int N, point;
    static int[] arr, lis;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); //수열의 크기

        arr = new int[N+1];
        lis = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        point = 0;
        for(int i=1; i<=N; i++) {
            int idx = lowerBound( 0, point, arr[i]);

            if(idx == -1) {
                lis[point++] = arr[i];
            }
            else {
                lis[idx] = arr[i];
            }
        }

        System.out.println(point);

    }

    private static int lowerBound(int low, int high, int key) {
        int res = 0;
        while(low <= high) {
            int mid = (low + high) / 2;

            if(key <= lis[mid]) {
                res = mid;
                high = mid-1;
            }
            else {
                low = mid+1;
            }
        }

        if(low == point + 1) {
            return -1;
        }
        else {
            return res;
        }
    }
}