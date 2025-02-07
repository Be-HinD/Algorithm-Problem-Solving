import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] process;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        process = new int[N];
        st = new StringTokenizer(br.readLine());
        int max = 0;
        for(int i=0; i<N; i++) {
            process[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, process[i]);
        }

        System.out.println(max + N-1);
    }
}