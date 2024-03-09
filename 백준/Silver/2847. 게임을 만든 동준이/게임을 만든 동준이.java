import java.io.*;

//BOJ_2847 게임을 만든 동준이
public class Main {
    static int N;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        for(int i=0; i<N; i++) {
            int idx = Integer.parseInt(br.readLine());
            arr[i] = idx;
        }

        int res = 0;
        for(int i=N-2; i>=0; i--) {
            while(arr[i+1] <= arr[i]) {
                arr[i]--;
                res++;
            }
        }
        System.out.println(res);
    }
}