import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int N, M, V; //정점 수, 간선 수, 시작 노드
    static int[][] graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        //그래프 초기화
        graph = new int[N+1][N+1];
        for(int i = 0; i<M; i++) {
            int x, y;
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            graph[x][y] = 1;
            graph[y][x] = 1;

        }

        visited = new boolean[N+1];
        dfs(V);
        System.out.println();
        visited = new boolean[N+1];
        bfs(V);
    }
    //깊이 우선 탐색
    static void dfs(int idx){
        visited[idx] = true;
        System.out.print(idx + " ");
        if(idx == graph.length) {
            return;
        }
        for(int i=1; i< graph.length; i++){
            if(graph[idx][i] == 1 && !visited[i]){
                dfs(i);
            }
        }
    }

    //너비 우선 탐색
    static void bfs(int idx) {
        Queue<Integer> queue = new LinkedList<Integer>();

        queue.add(idx);
        visited[idx] = true;
        System.out.print(idx + " ");

        while(!queue.isEmpty()){
            int s =queue.poll();
            for(int i =1; i< graph.length;i++){
                if(graph[s][i] == 1 && !visited[i]){
                    queue.add(i);
                    visited[i] = true;
                    System.out.print(i + " ");
                }
            }
        }
    }
}