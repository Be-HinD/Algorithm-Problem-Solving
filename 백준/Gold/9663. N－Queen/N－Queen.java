import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N, ans;
    static int[][] map;
    static int[] ex = new int[]{1,-1,-1,1};
    static int[] ey = new int[]{1,-1,1,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N]; //맵 초기화

        dfs(0);
        System.out.println(ans);
    }

    private static void dfs(int idx) {
        if(idx == N) { //8개를 모두 놓았을 경우
            ans++;
            return;
        }

        for(int i=0; i<N; i++) {
            if(isValidate(idx, i)) { //유효성 검사 통과 시
                map[idx][i] = 1;
                dfs(idx+1); //다음행 진입
                map[idx][i] = 0; //백트래킹
            }
        }
    }
    private static boolean isValidate(int x, int y) {
        //가로
        for(int i=0; i<N; i++) {
            if(map[x][i] !=0) return false;
        }
        //세로
        for(int i=0; i<N; i++) {
            if(map[i][y] != 0) return false;
        }
        //대각선
        for(int i=0; i<4; i++) {
            int nx = x;
            int ny = y;
            for(int j=0; j<N; j++) {
                nx += ex[i];
                ny += ey[i];
                if(nx<0 || ny<0 || nx>N-1 || ny>N-1) break;
                if(map[nx][ny] != 0) return false;
            }
        }
        return true;
    }
}