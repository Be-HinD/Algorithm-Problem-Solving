import java.io.*;
import java.util.*;

//BOJ_13458
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int b = Integer.parseInt(st.nextToken());   //총감독관
        int c = Integer.parseInt(st.nextToken());   //부감독관

        long res = 0;
        for(int people : arr) {
            people -= b;
            if(people < 0) people = 0;
            res += (int) Math.ceil((double) people / c);
            res++;
        }

        System.out.println(res);

    }
}