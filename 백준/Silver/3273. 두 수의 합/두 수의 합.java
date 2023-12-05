import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ_3273 두 수의 합
public class Main {
    static int N, res;
    static long[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new long[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) arr[i] = Integer.parseInt(st.nextToken());

        int x = Integer.parseInt(br.readLine());

        Arrays.sort(arr);

        int start = 0;
        int end = N-1;

        while(start < end) {
            long sum = arr[start] + arr[end];
            if(sum > x) end--;
            else if(sum < x) start++;
            else {
                res++;
                start++;
            }
        }

        System.out.println(res);
    }
}