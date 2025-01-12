import java.io.*;
import java.util.*;

//BOJ_3584
public class Main {
    static int T, N;
    static int[] node;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        while(T-- > 0) {
            N = Integer.parseInt(br.readLine());

            node = new int[N+1];
            boolean[] v = new boolean[N+1];

            for(int i=0; i<N-1; i++) {
                st = new StringTokenizer(br.readLine());

                int parent = Integer.parseInt(st.nextToken());
                int child = Integer.parseInt(st.nextToken());
                node[child] = parent;
            }

            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            v[a] = true;
            while(node[a] != 0) {
                a = node[a];
                v[a] = true;
            }

            while(!v[b]) {
                b = node[b];
            }

            sb.append(b).append("\n");
        }

        System.out.println(sb);

    }
}