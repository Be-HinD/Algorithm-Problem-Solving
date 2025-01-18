import java.io.*;
import java.util.*;

//BOJ_1240
public class Main {
    static class Node {
        int v;
        int w;
        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
    static int N, M;
    static List<List<Node>> list;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();

        for(int i=0; i<=N; i++) list.add(new ArrayList<>());

        for(int i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list.get(x).add(new Node(y,w));
            list.get(y).add(new Node(x,w));
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            sb.append(bfs(start, end)).append("\n");
        }

        System.out.println(sb);
    }

    static int bfs(int start, int end) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{start,0});
        boolean[] v = new boolean[N+1];
        v[start] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();

            if(cur[0] == end) return cur[1];
            for(Node n : list.get(cur[0])) {
                if(!v[n.v]) {
                    v[n.v] = true;
                    q.offer(new int[]{n.v, cur[1]+n.w});
                }
            }
        }
        return -1;
    }
}