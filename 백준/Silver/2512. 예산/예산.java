import java.io.*;
import java.util.*;

//BOJ_1920 수 찾기
public class Main {
    static int N, M, max;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuffer sb = new StringBuffer();

        N = Integer.parseInt(br.readLine()); //수열의 크기

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, arr[i]);
        }

        Arrays.sort(arr);

        M = Integer.parseInt(br.readLine());

        System.out.println(BinarySearch());

    }

    private static int BinarySearch() {
        int low = 0;
        int high = max;
        int res = 0;

        while(low <= high) {
            final int mid = (low+high) / 2; //상한액

            int sum = 0;
            for(int i=0; i<N; i++) {
                if(arr[i] >= mid) {
                    sum += mid;
                }
                else {
                    sum += arr[i];
                }
            }

            if(M - sum < 0) {
                high = mid - 1;
            }
            else if(M - sum >= 0) {
                low = mid + 1;
                res = Math.max(res, mid);
            }
        }
        return res;
    }
}