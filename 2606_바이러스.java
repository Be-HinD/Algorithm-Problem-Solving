import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static boolean[][] graph;
    static int temp_a, temp_b;
    static boolean[] visited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N+1];
        //그래프 입력
        graph = new boolean[N+1][N+1];
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            temp_a = Integer.parseInt(st.nextToken());
            temp_b = Integer.parseInt(st.nextToken());
            graph[temp_a][temp_b] = true;
            graph[temp_b][temp_a] = true;
        }
        visited[1] = true;
        dfs(1);
        int ans = 0;
        for(int i=0; i< visited.length; i++){
            if(visited[i]) ans++;
        }
        System.out.print(ans-1);
    }
    static void dfs(int idx) {
        //동작부
        for(int i=0; i< graph.length; i++) {
            if(graph[idx][i] && !visited[i]){
                visited[i] = true;
                dfs(i);
            }
        }
    }
}
