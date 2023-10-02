import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int L, R, C, ans;
    static int[] escapePoint;
    static char[][][] map;
    static int[] dx = new int[]{1,-1,0,0,0,0};
    static int[] dy = new int[]{0,0,1,-1,0,0};
    static int[] dz = new int[]{0,0,0,0,1,-1};
    static Queue<int[]> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            ans = 0; //결과값 초기화
            queue = new ArrayDeque<>(); //큐 초기화
            StringTokenizer st = new StringTokenizer(br.readLine());

            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            if(L == 0 && R == 0 && C == 0) { //종료 조건
                return;
            }

            map = new char[L][R][C];

            for (int i = 0; i < L; i++) {
                for (int j = 0; j < R; j++) {
                    String input = br.readLine();
                    for (int k = 0; k < C; k++) {
                        char idx = input.charAt(k);
                        if (idx == 'S') queue.offer(new int[]{i,j,k, 0}); //시작지점 큐 추가
                        else if(idx == 'E') escapePoint = new int[]{i,j,k}; //도착지점 배열 저장
                        map[i][j][k] = idx;
                    }
                }
                br.readLine(); //빈 줄 버리기
            }

            bfs();

            if(ans > 0 ) System.out.println("Escaped in " + ans + " minute(s).");
            else System.out.println("Trapped!");
        }
    }

    private static void bfs() {
        while(!queue.isEmpty()) {
            int[] idx = queue.poll();
            if(idx[0] == escapePoint[0] && idx[1] == escapePoint[1] && idx[2] == escapePoint[2]) { //도착지점의 좌표와 비교
                ans = idx[3];
                return;
            }
            for(int i=0; i<6; i++) {
                int nz = idx[0] + dz[i];
                int nx = idx[1] + dx[i];
                int ny = idx[2] + dy[i];

                if(nx<0 || ny<0 || nz<0 || nx>=R || ny>=C || nz >=L) continue;
                if(map[nz][nx][ny] == '.' || map[nz][nx][ny] == 'E') { //빈 칸이거나 도착지점일 경우에만 방문
                    map[nz][nx][ny] = 'S'; //방문체크
                    queue.offer(new int[]{nz,nx,ny,idx[3]+1});
                }
            }
        }
    }
}
