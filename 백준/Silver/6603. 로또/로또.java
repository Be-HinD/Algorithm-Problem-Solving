import java.io.*;
import java.util.*;

//BOJ_6603 로또
public class Main {
    static int[] arr, temp;
    static StringBuilder sb;
    static boolean[] v;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        temp = new int[6];
        sb = new StringBuilder();

        while(true) {
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());

            if(K == 0) break;
            arr = new int[K];
            for(int i=0; i<K; i++) arr[i] = Integer.parseInt(st.nextToken());

            Comb(0, 0);
            sb.append("\n");
        }

        System.out.println(sb);

    }

    private static void Comb(int cnt, int start) {
        if(cnt == 6) {
            Arrays.sort(temp);
            for(int i=0; i<6; i++) sb.append(temp[i]).append(" ");
            sb.append("\n");
            return;
        }
        for(int i=start; i<arr.length; i++) {
            temp[cnt] = arr[i];
            Comb(cnt+1, i+1);
        }
    }
}