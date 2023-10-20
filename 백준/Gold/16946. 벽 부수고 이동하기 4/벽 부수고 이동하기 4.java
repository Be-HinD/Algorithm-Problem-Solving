import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, pointer;
    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};
    static int[] dist = new int[100055];
    static int[][] map, res;
    static ArrayList<int[]> list = new ArrayList<>();
    static boolean[][] v;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        res = new int[N][M];

        for(int i=0; i<N; i++) {
            String input = br.readLine();
            for(int j=0; j<M; j++) {
                int idx = input.charAt(j) - '0';
                if(idx == 1) list.add(new int[]{i,j});
                map[i][j] = idx;
            }
        }

        pointer = 2;
        for(int i=0; i<N; i++) { //영역 나누기
            for(int j=0; j<M; j++) {
                if(map[i][j] == 0) {
                    zeroBfs(i, j);
                }
            }
        }

        for(int[] idx : list) {
            boolean[] v = new boolean[pointer];
            for(int i=0; i<4; i++) {
                int nx = idx[0] + dx[i];
                int ny = idx[1] + dy[i];
                if(nx<0 || ny<0 || nx>=N || ny>= M || v[map[nx][ny]]) continue;
                v[map[nx][ny]] = true;
                res[idx[0]][idx[1]] += dist[map[nx][ny]];
            }
            res[idx[0]][idx[1]]++;
            res[idx[0]][idx[1]] %= 10;
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                sb.append(res[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void zeroBfs(int x, int y) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x,y});
        map[x][y] = pointer;
        int cnt = 1;

        while(!q.isEmpty()) {
            int[] idx = q.poll();

            for(int i=0; i<4; i++) {
                int nx = idx[0] + dx[i];
                int ny = idx[1] + dy[i];
                if(nx<0 || ny<0 || nx>=N || ny>= M || map[nx][ny] != 0) continue;
                map[nx][ny] = pointer;
                q.offer(new int[]{nx, ny});
                cnt++;
            }
        }
        dist[pointer++] = cnt;
    }
}