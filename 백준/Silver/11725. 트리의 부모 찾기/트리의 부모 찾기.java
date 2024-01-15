import java.io.*;
import java.util.*;

//BOJ_11725 트리의 부모 찾기
public class Main {
    static int N;
    static int[] res;
    static List<List<Integer>> list;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        list = new ArrayList<>();
        for(int i=0; i<=N; i++) list.add(new ArrayList<>());

        for(int i=1; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            list.get(start).add(end);
            list.get(end).add(start);
        }

        res = new int[N+1];
        bfs();

        for(int i=2; i<=N; i++) {
            sb.append(res[i]).append("\n");
        }

        System.out.println(sb);

    }

    private static void bfs() {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(1);

        while(!q.isEmpty()) {
            int cur = q.poll();

            for(int idx : list.get(cur)) {
                if(res[idx] == 0) {
                    res[idx] = cur;
                    q.offer(idx);
                }
            }
        }
    }
}