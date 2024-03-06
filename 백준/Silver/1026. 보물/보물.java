import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

//BOJ_1026 보물
public class Main {
    static int N;
    static Integer[] a;
    static int[] b;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        a = new Integer[N];
        b = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(a, Comparator.reverseOrder());
        Arrays.sort(b);

        int res = 0;
        for(int i=0; i<N; i++) {
            res += a[i] * b[i];
        }

        System.out.println(res);

    }
}