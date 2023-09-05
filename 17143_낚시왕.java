import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int R, C, M, ans;
    static int[] fishKing;
    static shark[][] map;
    static int[] dx = new int[] {0,-1,1,0,0}; //방향벡터
    static int[] dy = new int[] {0,0,0,1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new shark[R+1][C+1];
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken()); //속력
            int d = Integer.parseInt(st.nextToken()); //방향
            int z = Integer.parseInt(st.nextToken()); //크기
            map[r][c] = new shark(r,c,s,d,z, true);
        }
        fishKing = new int[]{0, 0}; //낚시왕의 위치
        
        while(true) {
            if(fishKing[1] == C) { //종료조건
                System.out.println(ans);
                return;
            }
            catchShark();
        }
    }

    private static void catchShark() { //낚시왕 이동 및 로직 시작
        fishKing[1] += 1; //오른쪽 한 칸 이동
        isLife(); //열에 상어가 있는지 체크
        move(); //상어 이동
    }
    private static void isLife() {
        int c = fishKing[1];
        for(int i=1; i<=R; i++) {
            if(map[i][c] == null) continue; //예외처리
            if(map[i][c].life) { //제일 가까운 상어 먹고 결과값 갱신 및 상어 제거
                ans += map[i][c].z;
                map[i][c].life = false;
                return;
            }
        }
    }
    private static void move() { //상어 이동
        shark[][] temp = new shark[R+1][C+1];
        
        for(int i=0; i<=R; i++) {
            for(int j=0; j<=C; j++) {
                if(map[i][j] == null) continue; //상어가 없는 곳
                if(map[i][j].life) { //살아있는 상어에 한해서
                    int dist = map[i][j].d;
                    int speed = map[i][j].s;
                    if(dist == 1 || dist == 2) speed = speed % ((R-1)*2); //제자리가 되는 경우를 찾아서 최적화
                    else speed = speed % ((C-1)*2); //최적화
                    int r = i;
                    int c = j;
                    for(int k=0; k<speed; k++) { //최적화된 이동회수만큼 이동
                        if(dist == 1) {
                            if(r == 1) dist = 2;
                            r += dx[dist];
                            c += dy[dist];
                        } else if(dist == 2) {
                            if(r == R) dist = 1;
                            r += dx[dist];
                            c += dy[dist];
                        } else if(dist ==3) {
                            if(c == C) dist = 4;
                            r += dx[dist];
                            c += dy[dist];
                        } else {
                            if(c == 1) dist = 3;
                            r += dx[dist];
                            c += dy[dist];
                        }
                    }
                    //이동 끝
                    //이동된 위치에 상어가 존재하지 않을 경우
                    if(temp[r][c] == null) temp[r][c] = new shark(r,c,speed,dist,map[i][j].z, true);
                    else { //이동된 위치에 상어가 이미 존재할 경우
                        if(temp[r][c].z < map[i][j].z) { //위치할 좌표에 이미 상어가 있다면 비교 (같은 크기는 없음)
                            temp[r][c] = new shark(r,c,speed,dist,map[i][j].z, true);
                        }
                    }
                }
            }
        }
        map = temp; //갱신된 맵 연결
    }

    static class shark { //상어에 대한 정보클래스
        int r;
        int c;
        int s;
        int d;
        int z;
        boolean life;
        shark(int r, int c, int s, int d, int z, boolean life) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
            this.life = life;
        }
    }
}
