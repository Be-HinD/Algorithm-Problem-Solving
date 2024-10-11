import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Main {
    static int N, M, Max;
    static int[] arr;
    static long Min;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //집의 개수
        M = Integer.parseInt(st.nextToken()); //공유기 개수

        arr = new int[N];
        for(int i=0; i<N; i++) {
            int idx = Integer.parseInt(br.readLine());
            arr[i] = idx;
            Max = Math.max(Max, idx);
        }
        Arrays.sort(arr);
        BinarySearch();
    }
    private static void BinarySearch() {
        int low = 1;
        int high = Max;
        while(low <= high) {
            final int mid = low + (high-low)/2;
            Min = Integer.MAX_VALUE;
            int cnt = 1;
            int value = arr[0];
            for(int i=1; i<arr.length; i++) {
                int diff = arr[i] - value;
                if(diff >= mid) {
                    cnt++;
                    Min = Math.min(Min, diff);
                    value = arr[i];
                }
            }
            if(cnt >= M) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        System.out.println(high);
    }
}