import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Point { //감시카메라 정보 클래스
        int x;
        int y;
        int type;
        Point(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }
    static int N, M, ans;
    static int[][] map;
    static int[] arr;
    static int[] dx = new int[] {-1,0,1,0}; //북쪽부터 시계방향벡터
    static int[] dy = new int[] {0,1,0,-1};
    static ArrayList<Point> list; //감시카메라 리스트

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        list = new ArrayList<>();
        for(int i=0; i<N; i++) { //맵 입력
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                int idx = Integer.parseInt(st.nextToken());
                map[i][j] = idx;
                if(idx != 0 && idx != 6) list.add(new Point(i,j,idx)); //감시카메라의 경우 리스트 추가
            }
        }

        arr = new int[list.size()]; //순열결과를 담을 배열(감시카메라의 개수만큼)
        ans = Integer.MAX_VALUE; //최소값 비교를 위해 초기화
        Perm(0);
        System.out.println(ans);
    }

    private static void Perm(int cnt) { //중복순열
        if(cnt == list.size()) {
            //순열생성완료
            logic();
        } else {
            for(int i=0; i<4; i++) { //상하좌우 4개중에 하나를 선택
                arr[cnt] = i;
                Perm(cnt + 1);
            }
        }
    }

    private static void logic() {
        int[][] temp = new int[N][M];
        //깊은복사
        for(int i=0; i<N; i++) {
            temp[i] = map[i].clone();
        }
        int sum = 0;
        for(int i=0; i<list.size(); i++) {
            Point p = list.get(i); //감시카메라 한 개씩 꺼내서
            int dist = arr[i]; //방향
            int nx = p.x;
            int ny = p.y;
            switch (p.type) { //카메라의 종류에 따라 로직변경
                case 1:
                    while(true) {
                        nx += dx[dist];
                        ny += dy[dist];
                        if(nx<0 || ny<0 || nx>N-1 || ny>M-1 || temp[nx][ny] == 6) break; //맵밖으로 나가거나 벽을 만난 경우
                        if(temp[nx][ny] == 0) temp[nx][ny] = -1; //감시하는곳은 -1로
                    }
                    break;
                case 2:
                    while(true) {
                        nx += dx[dist];
                        ny += dy[dist];
                        if(nx<0 || ny<0 || nx>N-1 || ny>M-1 || temp[nx][ny] == 6) break; //맵밖으로 나가거나 벽을 만난 경우
                        if(temp[nx][ny] == 0) temp[nx][ny] = -1;
                    }
                    nx = p.x;
                    ny = p.y;
                    dist = (dist+2) % 4; //방향전환
                    while(true) {
                        nx += dx[dist];
                        ny += dy[dist];
                        if(nx<0 || ny<0 || nx>N-1 || ny>M-1 || temp[nx][ny] == 6) break; //맵밖으로 나가거나 벽을 만난 경우
                        if(temp[nx][ny] == 0) temp[nx][ny] = -1;
                    }
                    break;
                case 3:
                    while(true) {
                        nx += dx[dist];
                        ny += dy[dist];
                        if(nx<0 || ny<0 || nx>N-1 || ny>M-1 || temp[nx][ny] == 6) break; //맵밖으로 나가거나 벽을 만난 경우
                        if(temp[nx][ny] == 0) temp[nx][ny] = -1;
                    }
                    nx = p.x;
                    ny = p.y;
                    dist = (dist+1)%4; //방향전환
                    while(true) {
                        nx += dx[dist];
                        ny += dy[dist];
                        if(nx<0 || ny<0 || nx>N-1 || ny>M-1 || temp[nx][ny] == 6) break; //맵밖으로 나가거나 벽을 만난 경우
                        if(temp[nx][ny] == 0) temp[nx][ny] = -1;
                    }
                    break;
                case 4:
                    while(true) {
                        nx += dx[dist];
                        ny += dy[dist];
                        if(nx<0 || ny<0 || nx>N-1 || ny>M-1 || temp[nx][ny] == 6) break; //맵밖으로 나가거나 벽을 만난 경우
                        if(temp[nx][ny] == 0) temp[nx][ny] = -1;
                    }
                    nx = p.x;
                    ny = p.y;
                    dist = (dist+1)%4; //방향전환
                    while(true) {
                        nx += dx[dist];
                        ny += dy[dist];
                        if(nx<0 || ny<0 || nx>N-1 || ny>M-1 || temp[nx][ny] == 6) break; //맵밖으로 나가거나 벽을 만난 경우
                        if(temp[nx][ny] == 0) temp[nx][ny] = -1;
                    }
                    nx = p.x;
                    ny = p.y;
                    dist = (dist+2)%4; //방향전환
                    while(true) {
                        nx += dx[dist];
                        ny += dy[dist];
                        if(nx<0 || ny<0 || nx>N-1 || ny>M-1 || temp[nx][ny] == 6) break; //맵밖으로 나가거나 벽을 만난 경우
                        if(temp[nx][ny] == 0) temp[nx][ny] = -1;
                    }
                    break;
                case 5: //4방향 전부 탐색
                    while(true) {
                        nx += dx[0];
                        ny += dy[0];
                        if(nx<0 || ny<0 || nx>N-1 || ny>M-1 || temp[nx][ny] == 6) break; //맵밖으로 나가거나 벽을 만난 경우
                        if(temp[nx][ny] == 0) temp[nx][ny] = -1;
                    }
                    nx = p.x;
                    ny = p.y;
                    while(true) {
                        nx += dx[1];
                        ny += dy[1];
                        if(nx<0 || ny<0 || nx>N-1 || ny>M-1 || temp[nx][ny] == 6) break; //맵밖으로 나가거나 벽을 만난 경우
                        if(temp[nx][ny] == 0) temp[nx][ny] = -1;
                    }
                    nx = p.x;
                    ny = p.y;
                    while(true) {
                        nx += dx[2];
                        ny += dy[2];
                        if(nx<0 || ny<0 || nx>N-1 || ny>M-1 || temp[nx][ny] == 6) break; //맵밖으로 나가거나 벽을 만난 경우
                        if(temp[nx][ny] == 0) temp[nx][ny] = -1;
                    }
                    nx = p.x;
                    ny = p.y;
                    while(true) {
                        nx += dx[3];
                        ny += dy[3];
                        if(nx<0 || ny<0 || nx>N-1 || ny>M-1 || temp[nx][ny] == 6) break; //맵밖으로 나가거나 벽을 만난 경우
                        if(temp[nx][ny] == 0) temp[nx][ny] = -1;
                    }
                    break;
            }
        }

        for(int i=0; i<N; i++) { //최종적으로 안전지대의 개수체크
            for(int j=0; j<M; j++) {
                if(temp[i][j] == 0) sum++;
            }
        }
        ans = Math.min(ans, sum); //최소값 갱신
    }
}
