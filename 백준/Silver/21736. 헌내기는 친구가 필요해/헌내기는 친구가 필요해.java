import java.io.*;
import java.util.*;

//BOJ_21736
public class Main {
    static class Node {
        int x;
        int y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int N, M, res;
    static char[][] map;
    static Node DOYEON;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];

        for(int i=0; i<N; i++) {
            String input = br.readLine();
            for(int j=0; j<M; j++) {
                map[i][j] = input.charAt(j);
                if(map[i][j] == 'I') DOYEON = new Node(i,j);
            }
        }

        bfs();

        System.out.println(res == 0 ? "TT" : res);

    }

    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};
    private static void bfs() {
        Queue<Node> q = new ArrayDeque<>();
        boolean[][] v = new boolean[N][M];
        q.offer(DOYEON);
        v[DOYEON.x][DOYEON.y] = true;

        while(!q.isEmpty()) {
            Node cur = q.poll();

            if(map[cur.x][cur.y] == 'P') res++;

            for(int i=0; i<4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx<0 || ny<0 || nx>=N || ny>=M) continue;
                if(map[nx][ny] == 'X' || v[nx][ny]) continue;

                q.offer(new Node(nx,ny));
                v[nx][ny] = true;
            }

        }
    }
}