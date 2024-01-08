import java.io.*;
import java.util.*;

//BOJ_3987 보이저 1호
public class Main {
    static int N, M, sx, sy, res;
    static int[] dx = new int[]{-1,0,1,0};  //하상우좌
    static int[] dy = new int[]{0,1,0,-1};
    static char[][] map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for(int i=0; i<N; i++) {
            String input = br.readLine();
            for(int j=0; j<M; j++) {
                char idx = input.charAt(j);
                map[i][j] = idx;
            }
        }

        //무한히 전파될 수 있는 경우 체크 가능하지 ㅇㅇ
        st = new StringTokenizer(br.readLine());
        sx = Integer.parseInt(st.nextToken()) - 1;
        sy = Integer.parseInt(st.nextToken()) - 1;

        char resDist = '?';
        for(int i=0; i<4; i++) {
            int nx = sx + dx[i];
            int ny = sy + dy[i];
            if(!rangeValidation(nx, ny)) continue;
            int dist = bfs(i);
            if(dist == -1) {
                if(i==0) resDist = 'U';
                else if(i==1) resDist = 'R';
                else if(i==2) resDist = 'D';
                else if(i==3) resDist = 'L';
                System.out.println(resDist + "\n" + "Voyager");
                return;
            }
            else {
                if(res < dist) {
                    if(i==0) resDist = 'U';
                    else if(i==1) resDist = 'R';
                    else if(i==2) resDist = 'D';
                    else if(i==3) resDist = 'L';
                    res = dist;
                }
            }
        }

        System.out.println(resDist + "\n" + res);

    }

    private static int bfs(int dist) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{sx, sy, 0, dist});
        boolean[][][] v = new boolean[4][N][M];

        while(!q.isEmpty()) {
            int[] cur = q.poll();

            if(!rangeValidation(cur[0], cur[1])) {
                return cur[2];
            }

            if(v[cur[3]][cur[0]][cur[1]]) continue;

            v[cur[3]][cur[0]][cur[1]] = true;

            if(map[cur[0]][cur[1]] == 'C') {
                return cur[2];
            }

            if(map[cur[0]][cur[1]] == '\\') {
                int nextDist = 0;

                if(cur[3] == 0) nextDist = 3;
                else if(cur[3] == 1) nextDist = 2;
                else if(cur[3] == 2) nextDist = 1;
                else if(cur[3] == 3) nextDist = 0;

                int nx = cur[0] + dx[nextDist];
                int ny = cur[1] + dy[nextDist];
                q.offer(new int[]{nx, ny, cur[2]+1, nextDist});
                continue;
            }

            else if(map[cur[0]][cur[1]] == '/') {
                //한방에 몬함 ㅋㅋ
                int nextDist = 0;

                if(cur[3] == 0) nextDist = 1;
                else if(cur[3] == 1) nextDist = 0;
                else if(cur[3] == 2) nextDist = 3;
                else if(cur[3] == 3) nextDist = 2;

                int nx = cur[0] + dx[nextDist];
                int ny = cur[1] + dy[nextDist];
                q.offer(new int[]{nx,ny, cur[2]+1,nextDist});
                continue;
            }

            int nx = cur[0] + dx[cur[3]];
            int ny = cur[1] + dy[cur[3]];
            q.offer(new int[]{nx, ny, cur[2]+1, cur[3]});
        }
        return -1;
    }

    private static boolean rangeValidation(int x, int y) {
        if(x<0 || y<0 || x>=N || y>= M) return false;
        return true;
    }
}