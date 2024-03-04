import java.io.*;
import java.util.StringTokenizer;

//BOJ_11047 동전 0
public class Main {
    static int N, K, res;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N];
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        for(int i=N-1; i>=0; i--) {
            if(K >= arr[i]) {
                int cnt = K / arr[i];
                res += cnt;
                K -= cnt*arr[i];
                if(K == 0) break;
            }
        }

        System.out.println(res);

    }
}