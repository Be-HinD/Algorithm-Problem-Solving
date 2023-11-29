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
            for(int i=0; i<V; i++) list.add(new ArrayList<>()); //인접 리스트 초기화

            for(int i=0; i<E; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken()) - 1;
                int y = Integer.parseInt(st.nextToken()) - 1;
                list.get(x).add(y);
                list.get(y).add(x); //양방향 그래프
            }

            v = new int[V];

            for(int i=0; i<V; i++) {
                if(v[i] == 0) bfs(i);
                if(!flag) break;
            }

            System.out.println(flag?"YES":"NO");
        }
    }

    private static void bfs(int x) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(x);
        int color = 1; //1은 빨강, -1은 파랑으로 구분
        v[x] = color; //시작정점 구분

        while(!q.isEmpty()) {
            int cur = q.poll();
            color = v[cur] * -1; //현재 정점의 반대 색

            for(int now : list.get(cur)) { //인접한 정점들에 대해서
                if(v[now] == 0) { //방문하지않은 정점
                    v[now] = color;
                    q.offer(now);
                    continue;
                }
                if(v[now] == v[cur])  { //이미 방문한 정점의 색이 현재정점의 색과 같다면 이분 그래프가 아님
                    flag = false;
                    return;
                }
            }
        }
    }
}