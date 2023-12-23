import java.io.*;
import java.util.*;

//BOJ_12015 가장 긴 증가하는 부분 수열 2
public class Main {
    static int N, point;
    static int INF = 1000000000;
    static int[] arr, lis;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuffer sb = new StringBuffer();

        N = Integer.parseInt(br.readLine()); //수열의 크기

        arr = new int[N];
        lis = new int[N+1];
        Arrays.fill(lis, INF);

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        point = 0;
        for(int i=0; i<N; i++) {

            if(lis[point] < arr[i]) {
                lis[++point] = arr[i];
            }
            else {
                int idx = lowerBound( 0, point, arr[i]);
                lis[idx] = arr[i];
            }

        }

        System.out.println(point+1);
    }

    private static int lowerBound(int low, int high, int key) {

        while(low < high) {
            int mid = (low + high) / 2;

            if(key <= lis[mid]) {
                high = mid;
            }
            else {
                low = mid+1;
            }
        }
        return high;
    }
}