import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

//BOJ_18870 좌표 압축
public class Main {
    static int N;
    static int[] arr, copy;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        copy = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            copy[i] = arr[i];

        }

        Arrays.sort(copy);
        for(int i=N-1; i>0; i--) {
            if(copy[i] == copy[i-1]) {
                copy[i] = Integer.MAX_VALUE;
            }
        }
        Arrays.sort(copy);

        for(int i=0; i<N; i++) {
            int idx = BinarySearch(arr[i]);
            sb.append(idx).append(" ");
        }
        System.out.println(sb);
    }

    private static int BinarySearch(int key) {
        int low = 0;
        int high = arr.length - 1;

        while(low < high) {
            final int mid = (low + high) / 2;

            if(copy[mid] >= key ) {
                high = mid;
            }
            else {
                low = mid + 1;
            }
        }
        return low;
    }
}