import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

//BOJ_18405 경쟁적 전염
public class Main {
    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int N, K, S, desx, desy, res;
    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};
    static int[][] map;
    static List<List<Node>> list;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb;

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        for(int i=0; i<=K; i++) list.add(new ArrayList<>());

        map = new int[N][N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                int idx = Integer.parseInt(st.nextToken());
                map[i][j] = idx;
                list.get(idx).add(new Node(i, j));
            }
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        desx = Integer.parseInt(st.nextToken()) - 1;
        desy = Integer.parseInt(st.nextToken()) - 1;

        for(int i=0; i<S; i++) {
            for(int j=1; j<=K; j++) {
                bfs(j);
            }
        }

        System.out.println(map[desx][desy] == 0 ? 0 : map[desx][desy]);
    }

    private static void bfs(int obj) {
        Queue<Node> q = new ArrayDeque<>();
        for(int i=0; i<list.get(obj).size(); i++) {
            Node n = list.get(obj).get(i);
            q.offer(new Node(n.x, n.y));
            list.get(obj).remove(i);
            i--;
        }

        while(!q.isEmpty()) {
            Node cur = q.poll();

            for(int i=0; i<4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx<0 || ny<0 || nx>=N || ny>=N) continue;
                if(map[nx][ny] == 0) {
                    map[nx][ny] = obj;
                    list.get(obj).add(new Node(nx, ny));
                }
            }
        }
    }
}