import java.io.*;
import java.util.*;

//BOJ_24444
public class Main {
    static int N, M, start, res;
    static List<List<Integer>> list = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken());

        for(int i=0; i<=N; i++) list.add(new ArrayList<>());

        while(M-->0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list.get(x).add(y);
            list.get(y).add(x);
        }

        for(int i=1; i<=N; i++) {   // 인접 정점을 오름차순으로 정렬
            Collections.sort(list.get(i));
        }


        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        boolean[] v = new boolean[N+1];
        v[start] = true;
        int[] order = new int[N+1];
        int p = 1;

        while(!q.isEmpty()) {
            int cur = q.poll();

            order[cur] = p++;
            for(int idx : list.get(cur)) {
                if(v[idx]) continue;
                q.offer(idx);
                v[idx] = true;
            }
        }

        StringBuilder sb = new StringBuilder();

        for(int i=1; i<=N; i++) {
            sb.append(order[i]).append("\n");
        }

        System.out.println(sb);

    }
}