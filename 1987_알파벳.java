import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static String temp;
    static List<Character> list = new ArrayList<>();
    static int[] dx = new int[] {1, -1, 0, 0};
    static int[] dy = new int[] {0, 0, 1, -1};
    static char[][] map;
    static boolean[][] visited;
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        // 2차원 배열 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            temp = st.nextToken(); //공백없이 주어지기 때문에 temp 변수에 임시로 넣어줌
            for (int j = 0; j < M; j++) {
                map[i][j] = temp.charAt(j); //charAT 메서드를 활용하여 한 글자씩 넣어줌
            }
        }
        visited = new boolean[N][M];
        list.add(map[0][0]);
        visited[0][0] = true;
        dfs(0, 0, 1); //좌측상단부터 dfs start
        System.out.print(ans);
    }
    static void dfs(int x, int y, int cnt) {
        //탈출부
        ans = Math.max(cnt, ans); //최대치 갱신
        //동작부
        for(int i=0; i<4; i++){
            if(x+dx[i] < 0 || x+dx[i] >= N || y+dy[i] < 0 || y+dy[i] >= M) continue;

            if(!list.contains(map[x+dx[i]][y+dy[i]]) && !visited[x+dx[i]][y+dy[i]] ) { //문자비교 || 방문비교
                //방문포인트 체크 및 비교 문자열 리스트 추가
                visited[x+dx[i]][y+dy[i]] = true;
                list.add(map[x+dx[i]][y+dy[i]]);
                dfs(x+dx[i], y+dy[i], cnt + 1);
                //백트래킹
                visited[x+dx[i]][y+dy[i]] = false;
                list.remove((Character)map[x+dx[i]][y+dy[i]]);
            }
        }

    }
}
