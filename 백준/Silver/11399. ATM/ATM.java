import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

//BOJ_11399 ATM
public class Main {
    static int N, res;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int temp = 0;
        for(int i=0; i<N; i++) {
            res += temp + arr[i];
            temp += arr[i];
        }

        System.out.println(res);
        br.close();
    }
}