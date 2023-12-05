import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ_16920 확장 게임
public class Main {
    static int N, M, P;
    static int[] res;
    static int[] dist;
    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};
    static char[][] map;
    static ArrayList<ArrayList<int[]>> casttleList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken()); //플레이어 수

        res = new int[P+1];

        casttleList = new ArrayList<>();
        for(int i=0; i<=P; i++) casttleList.add(new ArrayList<>());


        dist = new int[P+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=P; i++) {
            dist[i] = Integer.parseInt(st.nextToken());
        }

        map = new char[N][M];
        for(int i=0; i<N; i++) {
            String input = br.readLine();
            for(int j=0; j<M; j++) {
                char idx = input.charAt(j);
                map[i][j] = idx;
                if(idx != '.' && idx != '#') {
                    int player = idx - '0';
                    casttleList.get(player).add(new int[]{i,j, 0});
                    res[player]++;
                }
            }
        }

        while(true) {
            int cnt = 0;

            for(int i=1; i<=P; i++) {
                cnt += bfs(i);
            }
            if(cnt == 0) break;
        }

        for(int i=1; i<=P; i++) {
            System.out.print(res[i] + " ");
        }
    }

    private static int bfs(int player) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] v = new boolean[N][M];
        for(int[] idx : casttleList.get(player)) {
            q.offer(idx);
            v[idx[0]][idx[1]] = true;
        }
        casttleList.get(player).clear();
        int cnt = 0;
        while(!q.isEmpty()) {
            int[] idx = q.poll();
            if(idx[2] == dist[player]) continue;

            for(int i=0; i<4; i++) {
                int nx = idx[0] + dx[i];
                int ny = idx[1] + dy[i];
                if(nx<0 || ny<0 || nx>=N || ny>=M || v[nx][ny]) continue;
                if(map[nx][ny] == '.') { //확장 가능할 경우에만
                    casttleList.get(player).add(new int[]{nx,ny,0});
                    v[nx][ny] = true;
                    map[nx][ny] = (char) player;
                    q.offer(new int[]{nx,ny, idx[2]+1});
                    cnt++;
                    res[player]++;
                }
            }
        }
        return cnt;
    }
}