import java.io.*;
import java.util.*;

//26153
public class Main {
    static class XY{
        int x;
        int y;
        int vector;
        int cnt;
        int sum;

        public XY(int x, int y, int vector, int cnt, int sum){
            this.x = x;
            this.y = y;
            this.vector = vector;
            this.cnt = cnt;
            this.sum = sum;
        }
    }
    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};
    static int n, m, sum, start_x, start_y;
    static int[][] map;
    static boolean visited[][];

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for(int i = 0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0;j<m;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        start_x = Integer.parseInt(st.nextToken());
        start_y = Integer.parseInt(st.nextToken());
        int cnt = Integer.parseInt(st.nextToken());

        sum = map[start_x][start_y];
        visited = new boolean[n][m];
        dfs(new XY(start_x,start_y,-1,cnt,map[start_x][start_y]));

        System.out.println(sum);
    }

    static void dfs(XY start){
        if(visited[start.x][start.y]) return;

        if(start.cnt < 0) return;
        sum = Math.max(sum, start.sum); //80% 반례


        for(int i = 0;i<4;i++){
            int nx = start.x + dx[i];
            int ny = start.y + dy[i];

            if(nx < 0 || ny <0|| nx>=n|| ny>=m) continue;
            if(nx == start_x && ny == start_y) continue;

            visited[start.x][start.y] = true;
            if(start.vector!=-1 && start.vector != i){ //시작x, 직선파이프이면 안됨.
                if(start.cnt - 2 >= 0) dfs(new XY(nx,ny,i,start.cnt - 2, start.sum + map[nx][ny]));
            }
            else{
                dfs(new XY(nx,ny,i,start.cnt - 1, start.sum + map[nx][ny]));
            }
            visited[start.x][start.y] = false;
        }
    }
}