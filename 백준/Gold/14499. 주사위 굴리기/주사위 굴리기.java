import java.io.*;
import java.util.*;

//BOJ_14499 주사위 굴리기
public class Main {
    static int N, M, X, Y, K, res;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] dice = new int[6];    //0 : 위, 1 : 아래, 2|3|4|5 : 동서남북

        int[] dx = new int[]{0,0,-1,1};
        int[] dy = new int[]{1,-1,0,0};
        st = new StringTokenizer(br.readLine());
        int nx = X;
        int ny = Y;
        for(int i=0; i<K; i++) {
            int command = Integer.parseInt(st.nextToken()) - 1;
            //동쪽은 1, 서쪽은 2, 북쪽은 3, 남쪽은 4
            nx += dx[command];
            ny += dy[command];
            if(nx<0 || ny<0 || nx>=N || ny>=M) {
                nx -= dx[command];
                ny -= dy[command];
                continue;
            }

            //STEP 1. 주사위 값 이동
            if(command == 0) {
                //오른쪽
                int temp = dice[1];
                dice[1] = dice[2];
                dice[2] = dice[0];
                dice[0] = dice[3];
                dice[3] = temp;
            }
            else if(command == 1) {
                int temp = dice[1];
                dice[1] = dice[3];
                dice[3] = dice[0];
                dice[0] = dice[2];
                dice[2] = temp;
            }
            else if(command == 2) {
                //위
                int temp = dice[1];
                dice[1] = dice[5];
                dice[5] = dice[0];
                dice[0] = dice[4];
                dice[4] = temp;
            }
            else if(command == 3) {
                //아래
                int temp = dice[1];
                dice[1] = dice[4];
                dice[4] = dice[0];
                dice[0] = dice[5];
                dice[5] = temp;
            }
            if(map[nx][ny] == 0) {
                //주사위에 있는 수 칸으로 복사, 주사위 = 0
                map[nx][ny] = dice[1];
            }
            else {
                //칸에 있는 수 주사위로 복사, 칸 = 0
                dice[1] = map[nx][ny];
                map[nx][ny] = 0;
            }

            sb.append(dice[0]).append("\n");
        }

        System.out.println(sb);

    }
}