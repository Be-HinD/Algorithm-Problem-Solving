import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main {
    static int K, N, MaxLength;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        arr = new int[K];
        for(int i=0; i<K; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            MaxLength = Math.max(MaxLength, arr[i]);
        }
        BinarySearch(MaxLength, N);
    }
    private static void BinarySearch(int MaxLength, int key) {
        long low = 1;
        long high = MaxLength;
        while(low <= high) {
            final long mid = low + (high-low)/2;
            int cnt = 0;
            for(int idx : arr) {
                cnt += idx / mid;
            }
            if(cnt >= key) {
                low = mid + 1;
            }
            else {
                high = mid - 1;
            }
        }
        System.out.println(high);
    }
}