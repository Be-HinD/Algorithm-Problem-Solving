import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int M, N, H, zerocnt; //zerocnt : 맵 전체의 1개수 파악용 변수
    static int[][][] map;
    static Queue<Point> queue; //탐색을 위한 큐
    static int[] dx = new int[]{0, 0, 1, -1}; //방향벡터
    static int[] dy = new int[]{1, -1, 0, 0}; //동서남북
    static int[] dz = new int[]{1, -1}; //상하벡터

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        queue = new ArrayDeque<>(); //큐 생성
        map = new int[H][N][M];

        //맵 입력
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < M; k++) {
                    int idx = Integer.parseInt(st.nextToken());
                    map[i][j][k] = idx;
                    if (idx == 1) queue.offer(new Point(j, k, i)); //익은 토마토의 위치 큐 추가
                    if (idx == 0) zerocnt++; //0의 개수 추가
                }
            }
        }
        int cnt = 0; //결과 변수 초기화
        while (true) { //탐색시작
            int size = queue.size(); //초기 큐의 사이즈를 저장

            for (int t = 0; t < size; t++) { //첫 탐색의 큐 사이즈만큼만 반복
                Point temp = queue.poll();

                for (int i = 0; i < 4; i++) { //4방탐색 먼저
                    int nx = temp.x + dx[i];
                    int ny = temp.y + dy[i];
                    if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue; //null 예외처리
                    if (map[temp.z][nx][ny] == 0) { //덜 익은 토마토일 경우
                        map[temp.z][nx][ny] = 1; //값 갱신
                        zerocnt--; //맵 전체의 0개수 1감소
                        queue.add(new Point(nx, ny, temp.z)); //방금 익은 토마토의 위치 큐 추가
                    }
                }
                for (int i = 0; i < 2; i++) { //z축 2방탐색
                    int nz = temp.z + dz[i];
                    if (nz < 0 || nz > map.length - 1) continue; //null 예외처리
                    if (map[nz][temp.x][temp.y] == 0) {
                        map[nz][temp.x][temp.y] = 1;
                        zerocnt--;
                        queue.add(new Point(temp.x, temp.y, nz));
                    }
                }
            }
            if (queue.isEmpty()) break; //큐가 빌 경우 종료
            cnt++; //시간 증감
        }
        if (zerocnt == 0) System.out.println(cnt); //덜익은 토마토가 전부 익었을 때
        else System.out.println(-1); //덜익은 토마토가 존재할 때
    }
}
class Point { //좌표값 객체
    int x;
    int y;
    int z;

    public Point(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
