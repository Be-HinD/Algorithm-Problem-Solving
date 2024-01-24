import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ_11724 연결 요소의 개수
public class Main {
    static int N, M, res;
    static List<List<Integer>> list;
    static boolean[] v;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        for(int i=0; i<=N; i++) list.add(new ArrayList<>());

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            list.get(start).add(end);
            list.get(end).add(start);   //양방향 그래프
        }

        v = new boolean[N+1];
        for(int i=1; i<=N; i++) {
            if(v[i]) continue;
            bfs(i);
            res++;
        }
        System.out.println(res);

    }

    private static void bfs(int start) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);

        v[start] = true;

        while(!q.isEmpty()) {
            int cur = q.poll();

            for(int idx : list.get(cur)) {
                if(v[idx]) continue;
                q.offer(idx);
                v[idx] = true;
            }
        }
    }
}