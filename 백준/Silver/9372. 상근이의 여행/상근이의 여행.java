import java.io.*;
import java.util.*;

//BOJ_9372 (Graph)
public class Main {
    static int t, n, m;
    static List<List<Integer>> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        t = Integer.parseInt(br.readLine());

        while(t-->0) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            list = new ArrayList<>();
            for(int i=0; i<=n; i++) list.add(new ArrayList<>());

            for(int i=0; i<m; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                list.get(x).add(y);
                list.get(y).add(x);
            }

            System.out.println(n-1);

        }
    }
}