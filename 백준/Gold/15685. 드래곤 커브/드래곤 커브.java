import java.io.*;
import java.util.*;

//BOJ_15685 드래곤 커브
public class Main {
    static int N, res;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());    //드래곤 커브 개수

        map = new int[101][101];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            curveDraw(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        for(int i=0; i<100; i++) {
            for(int j=0; j<100; j++) {
                if(check(i,j)) res++;
            }
        }

        System.out.println(res);

    }

    static int[] dx = new int[]{0,-1,0,1};
    static int[] dy = new int[]{1,0,-1,0};
    static void curveDraw(int y, int x, int d, int g) {
        List<Integer> list = new ArrayList<>();
        list.add(d);

        for(int i=0; i<g; i++) {
            for(int j=list.size()-1; j>=0; j--) {
                list.add((list.get(j)+1) % 4);
            }
        }

        map[x][y] = 1;
        for(int idx : list) {
            x += dx[idx];
            y += dy[idx];
            map[x][y] = 1;
        }
    }

    static boolean check(int x, int y) {
        if(map[x][y] != 1 || map[x+1][y] != 1 || map[x][y+1] != 1 || map[x+1][y+1] != 1) {
            return false;
        }
        return true;
    }
}