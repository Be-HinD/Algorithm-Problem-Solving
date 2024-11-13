import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.*;

public class Main {
    static int N, M;
    static int x, y, nx, ny;
    static ArrayList<Integer>[] list;
    static int[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()) + 1; //사람 수 N
        st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken()); //출력을 위한 관계 x
        y = Integer.parseInt(st.nextToken()); //출력을 위한 관계 x
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken()); //입력 M
        list = new ArrayList[N]; //인접 리스트 초기화
        for(int i=0; i<N; i++) {
            list[i] = new ArrayList<>();
        }
        visited = new int[N]; //방문배열 초기화
        for(int i=0; i<M; i++) { //맵 입력
            st = new StringTokenizer(br.readLine());
            nx = Integer.parseInt(st.nextToken());
            ny = Integer.parseInt(st.nextToken());
            list[nx].add(ny);
            list[ny].add(nx);
        }
        bfs(x);
        if(visited[y] == 0) System.out.println(-1);
        else System.out.println(visited[y] - 1);
    }
    private static void bfs(int x) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(x);
        visited[x] = 1;
        while(!q.isEmpty()) {
            int id = q.poll();
            for(int i =0; i<list[id].size(); i++) {
                int idx = list[id].get(i);
                if(visited[idx] == 0) {
                    q.offer(idx);
                    visited[idx] = visited[id] + 1;
                }
            }
        }
    }
}