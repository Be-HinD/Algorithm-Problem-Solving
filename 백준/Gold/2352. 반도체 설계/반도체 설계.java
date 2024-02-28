import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

//BOJ_2352 반도체 설계
public class Main {
    static int N, res;
    static int[] arr, dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        dp = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            //입력
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int idx = 0;
        for(int i=0; i<N; i++) {

            if(dp[idx] < arr[i]) {
                dp[++idx] = arr[i];
            }
            else {
                int target = LowerBound(0, idx, arr[i]);
                dp[target] = arr[i];
            }
        }

        System.out.println(idx);


    }

    private static int LowerBound(int low, int high, int key) {
        while(low < high) {
            int mid = (low + high) / 2;

            if(dp[mid] < key) { //mid값보다 현재 key값이 크다면 오른쪽에서 탐색
                low = mid + 1;
            }
            else { //작을경우 왼쪾에서 탐색
                high = mid;
            }
        }
        return high;
    }
}