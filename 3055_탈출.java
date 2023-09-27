import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int R, C, ans;
    static char[][] map;
    static int[] dx = new int[]{1,-1,0,0}; //방향벡터
    static int[] dy = new int[]{0,0,1,-1};
    static int[] start; //시작위치 배열
    static Queue<int[]> queue = new ArrayDeque<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        for(int i=0; i<R; i++) { //맵 입력
            String input = br.readLine();
            for(int j=0; j<C; j++) {
                char idx = input.charAt(j);
                if(idx == 'S') { //고슴도치 시작위치일 경우
                    start = new int[]{i,j};
                } else if(idx == '*') { //물이 차있는 경우
                    queue.offer(new int[]{i,j,0, 1}); //확산을 위한 큐 추가
                }
                map[i][j] = idx;
            }
        }

        queue.offer(new int[]{start[0], start[1], 0, 0}); //좌표,시간,플래그(비버인지 물인지)
        bfs();
        if(ans == 0) System.out.println("KAKTUS"); //경로가 없을 경우
        else System.out.println(ans);

    }
    private static void bfs() {
        while(!queue.isEmpty()) {
            int[] idx = queue.poll();

            for(int i=0; i<4; i++) {
                int nx = idx[0] + dx[i];
                int ny = idx[1] + dy[i];
                if(nx<0 || ny<0 || nx>=R || ny>=C || map[nx][ny] == 'X') continue; //맵밖으로 벗어날 경우 및 다음 이동예정인 좌표가 돌 일 경우
                if(idx[3] == 1) { //큐에서 꺼낸 값이 물일 경우
                    if(map[nx][ny] == 'D' || map[nx][ny] == '*') continue; //물의 확산은 비버소굴, 이미 차있는 경우에는 안됨
                    map[nx][ny] = '*'; //맵 값 갱신
                    queue.offer(new int[]{nx,ny,0,1}); //플래그 1로 큐에 추가
                } else { //비버일 경우 이동로직
                    if(map[nx][ny] == 'D') ans = idx[2]+1; //소굴로 도달할 경우
                    if(map[nx][ny] == '*' || map[nx][ny] == 'S') continue; //물이나 방문했던 곳을 만나면 탈출
                    map[nx][ny] = 'S'; //방문체크용 값 변경
                    queue.offer(new int[]{nx,ny,idx[2]+1,0}); //플래그 0으로 큐에 추가
                }
            }
        }
    }
}
