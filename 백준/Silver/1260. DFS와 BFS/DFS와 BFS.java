import java.io.*;
import java.util.*;

//BOJ_1260
public class Main {
    static int n, m, v;
    static List<List<Integer>> list;
    static boolean[] visited;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        for(int i=0; i<=n; i++) list.add(new ArrayList<>());

        visited = new boolean[n+1];

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list.get(x).add(y);
            list.get(y).add(x);
        }

        for(int i=0; i<=n; i++) {
            Collections.sort(list.get(i));
        }

        visited[v] = true;

        sb = new StringBuilder();
        dfs(v);
        System.out.println(sb.toString());

        sb = new StringBuilder();
        visited = new boolean[n+1];
        bfs(v);
        System.out.print(sb.toString());
    }

    static void dfs(int idx) {
        sb.append(idx).append(" ");

        for(int next : list.get(idx)) {
            if(visited[next]) continue;
            visited[next] = true;
            dfs(next);
        }
    }

    static void bfs(int start) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        visited[start] = true;

        while(!q.isEmpty()) {
            int idx = q.poll();
            sb.append(idx).append(" ");

            for(int next : list.get(idx)) {
                if(visited[next]) continue;
                q.offer(next);
                visited[next] = true;
            }
        }
    }
}