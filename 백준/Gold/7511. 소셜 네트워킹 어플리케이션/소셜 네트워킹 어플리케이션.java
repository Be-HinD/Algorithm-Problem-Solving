import java.io.*;
import java.util.*;

//BOJ_7511
public class Main {
    static int t, n, k;
    static int[] parent;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        t = Integer.parseInt(br.readLine());    //Test case

        StringBuilder sb = new StringBuilder();
        for(int tc=1; tc<=t; tc++) {
            n = Integer.parseInt(br.readLine());    //정점
            k = Integer.parseInt(br.readLine());    //간선
            sb.append("Scenario ").append(tc).append(":\n");

            parent = new int[n+1];
            for(int i=1; i<=n; i++) parent[i] = i;

            while(k--> 0) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                union(x, y);
            }

            int m = Integer.parseInt(br.readLine());
            while(m-->0) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                sb.append(find(x) == find(y) ? 1 : 0).append("\n");
            }

            sb.append("\n");
        }

        System.out.println(sb);

    }

    static int find(int x) {
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a < b) parent[b] = a;
        else parent[a] = b;
    }
}