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
        arr = new int[M];
        st = new StringTokenizer(br.readLine());

        for(int i=0; i<M; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int res = upperBound();

        System.out.println(res==-1?0:res);
    }

    public static int upperBound() {
        int low = 0;
        int high = 1000000055;
        int rst = 0;
        while (low < high) {
            final int mid = (low + high) /2;
            if(mid == 0) return -1;
            int cnt = 0;

            for(int i=0; i<M; i++) {
                if(mid <= arr[i]) {
                    cnt += arr[i] / mid;
                }
            }

            if (cnt >= N) {
                rst = Math.max(rst, mid);
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return rst;
    }

}