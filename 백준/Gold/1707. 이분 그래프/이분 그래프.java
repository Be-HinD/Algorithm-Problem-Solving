import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ_1707 이분 그래프
public class Main {
    static int T, V, E;
    static ArrayList<ArrayList<Integer>> list;
    static int[] v;
    static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for(int tc=0; tc<T; tc++) { //TestCase
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            flag = true;
            list = new ArrayList<>();
            for(int i=0; i<V; i++) list.add(new ArrayList<>());

            for(int i=0; i<E; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken()) - 1;
                int y = Integer.parseInt(st.nextToken()) - 1;
                list.get(x).add(y);
                list.get(y).add(x);
            }

            v = new int[V];
            Arrays.fill(v, Integer.MAX_VALUE);

            for(int i=0; i<V; i++) {
                if(v[i] == Integer.MAX_VALUE) bfs(i);
                if(!flag) break;
            }

            if(flag) System.out.println("YES");
            else System.out.println("NO");
        }

    }

    private static void bfs(int x) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(x);
        int start = 1;
        v[x] = start;

        while(!q.isEmpty()) {
            int cur = q.poll();
            start = (v[cur] + 1) % 2; //for문안에서 계속 바뀌면 안되는 값.
            for(int now : list.get(cur)) {
                if(v[now] == Integer.MAX_VALUE) { //방문하지않은 정점
                    v[now] = start;
                    q.offer(now);
                    continue;
                }
                if(v[now] == (start+1) % 2)  { //이미 방문한 정점
                    flag = false;
                    return;
                }
            }
        }
    }
}