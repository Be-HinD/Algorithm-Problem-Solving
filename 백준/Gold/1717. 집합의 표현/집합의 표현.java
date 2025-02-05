import java.io.*;
import java.util.*;

//BOJ_1717
public class Main {
    static int N, M, res;
    static int[] parent;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   //정점개수
        M = Integer.parseInt(st.nextToken());   //연산개수

        parent = new int[N+1];
        for(int i=0; i<=N; i++) parent[i] = i;

        StringBuilder sb = new StringBuilder();
        while(M-->0) {
            st = new StringTokenizer(br.readLine());
            int oper = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(oper == 0) {
                union(a,b);
            }
            else sb.append(find(a) == find(b) ? "YES" : "NO").append("\n");
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