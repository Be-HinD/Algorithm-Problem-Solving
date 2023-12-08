import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ_20165 인내의 도미노 장인 호석
public class Main {
    static int N, M, R, res;
    static int[][] map, copy;
    static int[] dx = new int[]{0,0,1,-1};
    static int[] dy = new int[]{1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken()); //라운드 횟수

        map = new int[N][M];
        copy = new int[N][M];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                copy[i][j] = map[i][j];
            }
        }

        for(int i=0; i<R; i++) {
            st = new StringTokenizer(br.readLine()); //공격
            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;
            char d = st.nextToken().charAt(0); //방향

            Attack(x,y,d); //공격 로직

            st = new StringTokenizer(br.readLine()); //수비
            x = Integer.parseInt(st.nextToken())-1;
            y = Integer.parseInt(st.nextToken())-1;
            map[x][y] = copy[x][y];
        }

        //넘어지면 F
        //그 외는 S
        //매 라운드마다 공격 때 넘어진 도미노회수 출력
        sb.append(res).append("\n"); //공격 턴에 넘어뜨린 도미노의 총 개수
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j] == 0) sb.append("F ");
                else sb.append("S ");
            }
            sb.append("\n");
        }
        
        System.out.println(sb);

    }

    private static void Attack(int x, int y, char d) {
        //E:동, W:서, S:남, N:북
        switch (d) {
            case 'E':
                domino(x,y,0);
                break;
            case 'W':
                domino(x,y,1);
                break;
            case 'S':
                domino(x,y,2);
                break;
            case 'N':
                domino(x,y,3);
                break;
        }
    }

    private static void domino(int x, int y, int d) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x,y,map[x][y]});

        while(!q.isEmpty()) {
            int[] idx = q.poll();

            if(idx[2] == 0) continue;

            map[idx[0]][idx[1]] = 0; //현재좌표 넘어뜨리고 이후 연쇄 작용 큐에 추가
            res++; //결과값 증가

            int nx = idx[0];
            int ny = idx[1];
            for(int i=1; i<idx[2]; i++) {
                nx += dx[d];
                ny += dy[d];
                if(nx<0 || ny<0 || nx>=N || ny>=M) continue;
                if(map[nx][ny] != 0) {
                    q.offer(new int[]{nx,ny,map[nx][ny]});
                }
            }
        }
    }
}