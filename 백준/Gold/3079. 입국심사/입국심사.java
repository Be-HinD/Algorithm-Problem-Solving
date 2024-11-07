import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ_3079
public class Main {
    static int N, M;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        long res = lower();

        System.out.println(res);

    }

    private static long lower() {
        long low = 1;
        long high = Long.MAX_VALUE;

        while(low < high) {
            final long mid = low + (high-low)/2;

            long people = 0;
            for(int i=0; i<N; i++) {
                people += mid / arr[i];
                if(people >= M) break;
            }

            if(people >= M) {
                high = mid;
            }
            else {
                low = mid + 1;
            }
        }

        return high;
    }
}