import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ_20056 마법사 상어와 파이어볼
public class Main {
    static class FIRE {
        int r; //행
        int c; //열
        int m; //질량
        int s; //속력
        int d; //방향

        public FIRE(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }
    static int N,M,K, res;
    static int[] dx = new int[]{-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = new int[]{0, 1, 1, 1, 0, -1, -1, -1};
    static int[][] map;
    static ArrayList<FIRE> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        //이동하는 중에는 같은 칸에 여러 개의 파이어볼이 있을 수도 있다.

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken()); //파이어볼 개수
        K = Integer.parseInt(st.nextToken()); //이동 횟수

        map = new int[N][N];

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            list.add(new FIRE(r,c,m,s,d));
        }


        //for문으로 K번만큼 수행
        for(int i=0; i<K; i++) {
            logic();
            for(int r=0; r<N; r++) {
                for(int c=0; c<N; c++) {
                    if(map[r][c] >= 2) {
                        split(r,c);
                    }
                }
            }
            for(int r=0; r<N; r++) {
                Arrays.fill(map[r], 0);
            }

        }

        for(FIRE f : list) {
            res += f.m;
        }

        System.out.println(res);
        //마법사 상어가 이동을 K번 명령한 후, 남아있는 파이어볼 질량의 합을 구해보자.

    }

    private static void logic() {

        for(FIRE f : list) {
            f.r = (N + f.r + dx[f.d] * (f.s%N)) % N;
            f.c = (N + f.c + dy[f.d] * (f.s%N)) % N;
            map[f.r][f.c]++;
        }
    }

    private static void split(int i, int j) {
        boolean holFlag = false;
        boolean jjacFlag = false;
        int mSum = 0;
        int sSum = 0;
        int cnt = 0;
        for(int r=0; r<list.size(); r++) {
            FIRE f = list.get(r);
            if (f.r == i && f.c == j) {
                //홀수인지 짝수인지 방향이
                if (f.d % 2 == 0) { //짝수라면
                    jjacFlag = true;
                } else { //홀수라면
                    holFlag = true;
                }
                mSum += f.m;
                sSum += f.s;
                cnt++;
                list.remove(r);
                r--;
            }
        }

        int newM = mSum/5;
        if(newM == 0) return;

        int newS = sSum/cnt;

        if(jjacFlag && holFlag) {
            int dist = 1;
            for(int q=0; q<4; q++) {
                list.add(new FIRE(i,j,newM, newS, dist));
                dist += 2;
            }
        }
        else { //모두 홀수나 짝수
            int dist = 0;
            for(int q=0; q<4; q++) {
                list.add(new FIRE(i,j,newM, newS, dist));
                dist += 2;
            }
        }
    }
}