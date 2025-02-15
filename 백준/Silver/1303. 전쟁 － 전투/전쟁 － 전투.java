import java.io.*;
import java.util.*;

//BOJ_1303
public class Main {
    static int n, m, myTeam, ohterTeam;
    static char[][] map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new char[n][m];

        for(int i=0; i<n; i++) {
            String input = br.readLine();
            for(int j=0; j<m; j++) {
                map[i][j] = input.charAt(j);
            }
        }

        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(map[i][j] == 'x') continue;
                if(map[i][j] == 'W') bfs(i, j, true);
                else bfs(i, j, false);
            }
        }

        System.out.println(myTeam + " " + ohterTeam);

    }

    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};
    static void bfs(int x, int y, boolean flag) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x,y});
        map[x][y] = 'x';
        int cnt = 0;

        while(!q.isEmpty()) {
            int[] cur = q.poll();

            cnt++;
            for(int i=0; i<4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if(nx<0 || ny<0 || nx>=n || ny>=m || map[nx][ny] == 'x') continue;

                if(flag && map[nx][ny] == 'B') continue;
                if(!flag && map[nx][ny] == 'W') continue;
                q.offer(new int[]{nx,ny});
                map[nx][ny] = 'x';
            }

        }

        if(flag) myTeam += (int) Math.pow(cnt, 2);
        else ohterTeam += (int) Math.pow(cnt, 2);
    }
}