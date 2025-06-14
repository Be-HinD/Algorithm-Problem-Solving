import java.io.*;
import java.util.*;

//BOJ_16398
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
    static int n;
    static long res;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        PriorityQueue<Node> list = new PriorityQueue<>((o1, o2) -> o1.w - o2.w);
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                list.offer(new Node(i,j,Integer.parseInt(st.nextToken())));
            }
        }

        // TODO : 가중치를 기준으로 오름차순 정렬
        
        parent = new int[n];
        for(int i=0; i<n; i++) parent[i] = i;

        // TODO : 연결할 수 있는 간선이라면 비용 추가
        while(!list.isEmpty()) {
            Node cur = list.poll();
            if(!union(cur.x, cur.y)) continue;
            res += cur.w;
        }

        System.out.println(res);

    }

    static int find(int x) {
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot == bRoot) return false;
        parent[bRoot] = aRoot;

        return true;
    }
}