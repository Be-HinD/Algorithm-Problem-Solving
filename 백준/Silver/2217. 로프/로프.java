import java.io.*;
import java.util.Arrays;

//BOJ_2217 로프
public class Main {
    static int N, res;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        for(int i=0; i<N; i++) {
            int cnt = N-i;
            res = Math.max(res, arr[i] * cnt);
        }

        System.out.println(res);

    }
}