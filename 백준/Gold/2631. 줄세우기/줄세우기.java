import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

//BOJ_2631 줄 세우기
public class Main {
    static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int[] arr = new int[N+1];
        int[] lis = new int[N+1];

        for(int i=0; i<N; i++) {
            int idx = Integer.parseInt(br.readLine());
            arr[i] = idx;
            lis[i] = 1;
            for(int j=0; j<i; j++) {
                if(arr[j] < arr[i]) {
                    lis[i] = Math.max(lis[i], lis[j] + 1);
                }
            }

        }

        Arrays.sort(lis);
        System.out.println(N - lis[N]);

    }
}