import java.io.*;
import java.util.*;

//BOJ_21924
public class Main {
    static class Node {
        int x;
        int y;
        int w;
        public Node(int x, int y, int w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }
    }
    static int n, m;
    static long res;
    static int[] parent;
    static List<Node> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        parent = new int[n+1];
        for(int i=0; i<=n; i++) parent[i] = i;

        list = new ArrayList<>();
        long totalValue = 0;
        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            totalValue += w;
            list.add(new Node(x,y,w));
        }

        Collections.sort(list, (o1, o2) -> o1.w - o2.w);

        for(Node cur : list) {
            if(!union(cur.x, cur.y)) continue;
            res += cur.w;
        }

        for(int i=1; i<=n; i++) {   // 평탄화
            find(i);
        }

        int value = parent[1];
        for(int i=2; i<=n; i++) {
            if(parent[i] != value) {
                System.out.println(-1);
                return;
            }
        }

        System.out.println(totalValue - res);
    }

    static int find(int x) {
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot == bRoot) return false;

        parent[aRoot] = bRoot;
        return true;
    }
}