import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static int R, C, T;
    static int[][] map;
    static int[][] robot = new int[2][2];
    static int[] dx = new int[]{0,-1,0,1,0,1,0,-1};
    static int[] dy = new int[]{1,0,-1,0,1,0,-1,0};
    static HashMap<Integer, Integer> hash; //사다리정보 해시맵
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken()); //시간
        map = new int[R][C];
        int p = 0;
        for(int i=0; i<R; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<C; j++) {
                int idx = Integer.parseInt(st.nextToken());
                if(idx == -1) { //로봇 좌표값 저장
                    robot[p][0] = i;
                    robot[p++][1] = j;
                }
                map[i][j] = idx;
            }
        }

        for(int i=0; i<T; i++) { //시간 경과
            //먼지 뿌리기
            spread();
            //공기청정기 작동
            cleaning();
        }
        int ans = 0;
        for(int i=0; i<R; i++) { //맵 돌면서 미세먼지 개수 확인
            for(int j=0; j<C; j++) {
                if(map[i][j] != -1) ans += map[i][j];
            }
        }
        System.out.println(ans);
    }

    private static void spread() {
        int[][] copy = new int[R][C]; //카피배열 초기화
        //로봇위치 -1로 초기화
        int x = robot[0][0];
        int y = robot[0][1];
        copy[x][y] = -1;
        x = robot[1][0];
        y = robot[1][1];
        copy[x][y] = -1;

        for(int i=0; i<R; i++) { //맵을 돌면서
            for(int j=0; j<C; j++) {
                if(map[i][j] != 0 && map[i][j] != -1) { //미세먼지가 있는곳일 경우
                    //사방탐색 및 개수구하기
                    int cnt = isValidate(i,j); //cnt : 주변에 뿌릴 수 있는 개수
                    int value = map[i][j]/5; //뿌릴 값
                    copy[i][j] += (map[i][j] - (value*cnt)); //현재좌표 값 갱신
                    for(int k=0; k<4; k++) { //4방탐색
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if(nx<0 || ny<0 || nx>=R || ny>= C || map[nx][ny] == -1) continue;
                        copy[nx][ny] = copy[nx][ny] + value; //현재좌표 기준 미세먼지 확산
                    }
                }
            }
        }
        map = copy; //미세먼지 확산이 끝난 뒤 원본배열과 연결
    }
    private static int isValidate(int x, int y) { //x,y좌표 기준 주변 4방탐색
        int cnt = 0;
        for(int i=0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx<0 || ny<0 || nx>=R || ny>=C || map[nx][ny] == -1) continue;
            cnt++; //맵 밖 또는 로봇이 아닐 경우 cnt증가
        }
        return cnt;
    }

    private static void cleaning() {
        int[][] copy = new int[R][C];
        for(int i=0; i<R; i++) { //깊은 복사
            copy[i] = map[i].clone();
        }
        int nextX; //다음 좌표 넣을 변수 초기화
        int nextY;

        //위쪽 공기청정기 작동
        int x = robot[0][0];
        int y = robot[0][1];
        int vector = 0;
        int nx = x + dx[vector];
        int ny = y + dy[vector];
        copy[nx][ny] = 0; //공청기 바로 다음 칸은 항상 0
        while(true) {
            nextX = nx + dx[vector]; //현재좌표기준 다음vector좌표
            nextY = ny + dy[vector];
            if(nextX<0 || nextY<0 || nextX>=R || nextY>=C){ //다음 지역이 맵밖을 넘어갈 때
                vector++;
                nextX = nx + dx[vector]; //값 갱신
                nextY = ny + dy[vector];
            }
            if(map[nextX][nextY] == -1) break; //한바퀴 끝
            copy[nextX][nextY] = map[nx][ny]; //맵 갱신
            nx = nextX; //다음 좌표로 이동
            ny = nextY;
        }

        //아래쪽 공기청정기 작동 (위쪽 공기청정기 로직과 동일)
        x = robot[1][0];
        y = robot[1][1];
        vector = 4;
        nx = x + dx[vector];
        ny = y + dy[vector];
        copy[nx][ny] = 0; //공청기 바로 다음 칸은 항상 0
        while(true) {
            nextX = nx + dx[vector];
            nextY = ny + dy[vector];
            if(nextX<0 || nextY<0 || nextX>=R || nextY>=C){ //다음 지역이 맵밖을 넘어갈 때
                vector++;
                nextX = nx + dx[vector];
                nextY = ny + dy[vector];
            }
            if(map[nextX][nextY] == -1) break; //한바퀴 끝
            copy[nextX][nextY] = map[nx][ny];
            nx = nextX;
            ny = nextY;
        }
        map = copy;
    }
}
