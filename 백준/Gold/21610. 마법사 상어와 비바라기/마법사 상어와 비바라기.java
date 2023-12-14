import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ_21610 마법사 상어와 블리자드
public class Main {
    static class COLUD {
        int x;
        int y;

        public COLUD(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int N, M, res;
    static int[][] map;
    static int[] dx = new int[]{0,-1,-1,-1,0,1,1,1};
    static int[] dy = new int[]{-1,-1,0,1,1,1,0,-1};
    static ArrayList<COLUD> cloud;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //초기 구름 정보
        cloud = new ArrayList<>();
        cloud.add(new COLUD(N-1,0));
        cloud.add(new COLUD(N-1,1));
        cloud.add(new COLUD(N-2,0));
        cloud.add(new COLUD(N-2,1));

        for(int i=0; i<M; i++) { //M개 이동정보
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken())-1; //방향
            int s = Integer.parseInt(st.nextToken()); //속도

            Move(s,d); //구름 이동


            rain(); //비내리기

            bug(); //물복사버그

            newColud(); //새로운 구름

        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                res += map[i][j];
            }
        }

        System.out.println(res);

    }

    private static void Move(int s, int d) {
        for(COLUD idx : cloud) {
            //모듈러 연산을 통해 이동
            idx.x = (N + idx.x + dx[d]* (s % N)) % N;
            idx.y = (N + idx.y + dy[d]* (s % N)) % N;
        }
    }

    private static void rain() {
        for(COLUD idx : cloud) {
            map[idx.x][idx.y]++;
        }
    }

    private static void bug() {
        for(COLUD idx : cloud) {
            for(int i=1; i<8; i+=2) {
                int nx = idx.x + dx[i];
                int ny = idx.y + dy[i];
                if(nx<0 || ny<0 || nx>=N || ny>=N || map[nx][ny] == 0) continue;
                map[idx.x][idx.y]++;
            }
        }
    }

    private static void newColud() {
        ArrayList<COLUD> list = new ArrayList<>();
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(map[i][j] >= 2) {
                    if (isValidPoint(i, j)) continue; //i,j좌표가 기존 구름이 있던 자리인지 판단
                    list.add(new COLUD(i,j));
                    map[i][j] -= 2;
                }
            }
        }
        cloud = list; //COPY
    }

    private static boolean isValidPoint(int x, int y) {
        for(COLUD idx : cloud) {
            if(idx.x == x && idx.y == y) return true;
        }

        return false;
    }
}