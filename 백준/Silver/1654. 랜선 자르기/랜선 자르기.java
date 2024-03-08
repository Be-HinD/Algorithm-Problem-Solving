import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

//BOJ_16401 과자 나눠주기
public class Main {
    static int N,M;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];

        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        long res = upperBound();

        System.out.println(res==-1?0:res);
    }

    public static long upperBound() {
        long low = 1;
        long high = (long) Integer.MAX_VALUE + 1;

        while (low < high) {
            final long mid = (low + high) /2;

            long cnt = 0;
            for(int i=0; i<N; i++) {
                if(mid <= arr[i]) {
                    cnt += arr[i] / mid;
                }
            }
            if (cnt >= M) {
                low = (mid + 1);
            } else {
                high = mid;
            }
        }
        return (high - 1);
    }

}