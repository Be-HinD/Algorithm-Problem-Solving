import java.io.*;
import java.util.*;

//BOJ_1018 (아이디어 떠올리기 힘든 문제)
public class Main {
    static class Node {
        int x;
        int y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int n,m,res;
    static Map<Character, Character> info;
    static char[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        /**
         * 8x8 영역 나누기 -> 각 칸 사방탐색 통해서 현재 칸과 같은 칸이 더 많다면 현재칸을 변경

         * **/

        res = Integer.MAX_VALUE;

        info = new HashMap<>();
        info.put('W', 'B');
        info.put('B', 'W');

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];

        for(int i=0; i<n; i++) {
            String ipt = br.readLine();
            for(int j=0; j<m; j++) {
                map[i][j] = ipt.charAt(j);
            }
        }

        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(m - j < 8 || n - i < 8) continue;
                check(i, j);
            }
        }

        System.out.println(res);
    }

    static void check(int x, int y) {
        char[][] copy = new char[8][8];
        for(int i=x; i<x+8; i++) {
            for(int j=y; j<y+8; j++) {
                copy[i-x][j-y] = map[i][j];
            }
        }

        //현재 상태 그대로
        char[][] reverse = new char[8][8];
        for(int i=0; i<8; i++) reverse[i] = copy[i].clone();

        int ans = bfs(0, 0, copy);

        res = Math.min(res, ans);

//        반대색상일 경우
        reverse[0][0] = info.get(reverse[0][0]);
        ans = bfs(0, 0, reverse);

        res = Math.min(res, ans+1);
    }

    static int[] dx = new int[]{0,1};
    static int[] dy = new int[]{1,0};
    static int bfs(int x, int y, char[][] chess) {
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(x,y));
        boolean[][] v = new boolean[8][8];
        v[0][0] = true;

        int cnt = 0;
        while(!q.isEmpty()) {
            Node cur = q.poll();

            for(int i=0; i<2; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if(nx<0 || ny<0 || nx>=8 || ny>=8 || v[nx][ny]) continue;

                if(chess[nx][ny] == chess[cur.x][cur.y]) {
                    chess[nx][ny] = info.get(chess[cur.x][cur.y]);
                    cnt++;
                }
                q.offer(new Node(nx,ny));
                v[nx][ny] = true;
            }
        }
        return cnt;
    }
}