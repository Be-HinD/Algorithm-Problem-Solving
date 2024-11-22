import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ_3109
public class Main {
    static int N, M, res;
    static char[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        /**
         * 0에서 시작하면 최대한 위로 탐색
         * N-1에서 시작하면 최대한 아래로 탐색
         * 생각 못했던 것 : 백트래킹으로 지나간 경로를 되돌릴 필요가 없다. (그 지점에서 못갔던 경로라면 다른 지점에서도 못감)
         * 해결법
         * 각 지점에서 최대한 위로 탐색해서 N-1에 도착하면 경로 기록
         * 도착하지 못하면 지나온 경로에 대해 모두 방문체크
         * DFS에서 특정지점부터 깊이탐색을 중단하는 방법?
         * **/

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for(int i=0; i<N; i++) {
            String input = br.readLine();
            for(int j=0; j<M; j++) {
                map[i][j] = input.charAt(j);
            }
        }

        for(int i=0; i<N; i++) {
            if(dfs(i,0)) res++;
        }

        System.out.println(res);
    }

    private static boolean dfs(int x, int y) {
        map[x][y] = 'o';
        if(y == M-1) {
            return true;
        }

        int nx = x -1, ny = y + 1;
        if(isRange(nx, ny) && map[nx][ny] == '.') {
            if(dfs(nx,ny)) return true;
        }

        nx = x; ny = y + 1;
        if(isRange(nx, ny) && map[nx][ny] == '.') {
            if(dfs(nx,ny)) return true;
        }
        nx = x + 1; ny = y + 1;
        if(isRange(nx, ny) && map[nx][ny] == '.') {
            if(dfs(nx,ny)) return true;
        }

        return false;

    }

    private static boolean isRange(int x, int y) {
        if(x<0 || y<0 || x>=N || y>=M) return false;
        return true;
    }

}