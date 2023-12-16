import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ_15684 사다리 조작
public class Main {
    static int N, M, H, res;
    static int[][] map;
    static boolean[][] v;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();


        N = Integer.parseInt(st.nextToken()); //세로선
        M = Integer.parseInt(st.nextToken()); //가로선
        H = Integer.parseInt(st.nextToken()); //세로선마다 가로선을 놓을 수 있는 위치의 개수

        map = new int[H+2][N+2];
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[a][b] = 1;
        }

        res = Integer.MAX_VALUE; //최소값 비교를 위한 초기화
//        v = new boolean[H+1][N];
        //사다리를 내려가는 시뮬레이션
        dfs(0);

        System.out.println(res == Integer.MAX_VALUE ? -1 : res); //최소값을 못 구한 경우 -1 출력
    }

    private static void dfs(int cnt) {
        //기저조건
        if(cnt > 3) return; //사다리 추가 개수가 3을 넘어가면 탐색종료
        if(cnt >= res) return; //백트래킹 : 최소값을 구하는 문제이기 때문에 최소값과 같거나 넘을 경우 탐색 종료

        boolean flag = true;
        for(int i=1; i<=N; i++) {
            flag = game(i);
            if(!flag) break;
        }

        if(flag) { //각각의 사다리가 자기번호로 도착할 수 있는 경우
            res = cnt;
            return;
        }

        //사다리게임이 실패할 경우 사다리 추가 로직
        if(cnt < 3) { //cnt가 3일 경우 탐색을 할 필요가 없음.(3일 때도 탐색하면 8% 시간초과 발생)
            for (int i = 1; i <= H; i++) {
                for (int j = 1; j < N; j++) {
                    if (map[i][j] != 1 && map[i][j - 1] != 1) { //사다리를 놓을 수 있는 자리에 대해
                        map[i][j] = 1;
//                    v[i][j] = true;
                        dfs(cnt + 1);
                        //백트래킹
                        map[i][j] = 0;
                    }
                }
            }
        }
    }

    private static boolean game(int start) {
        int r = 1;
        int c = start;
        while(r <= H+1) {
            if(map[r][c] == 1) { //해당 좌표에 사다리가 존재할 경우 오른쪽으로 이동
                c += 1;
            }
            else if(map[r][c-1] == 1) { //해당 좌표의 왼쪽에 사다리가 있는 경우 왼쪽으로 이동
                c -= 1;
            }
            r += 1;
        }

        if(c == start) return true; //자기 번호에 맞게 도착한 경우
        return false;
    }
}