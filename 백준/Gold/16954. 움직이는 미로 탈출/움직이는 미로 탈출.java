import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int[] dx = new int[]{1,-1,0,0,-1,1,-1,1,0}; //인접 및 제자리 경우 방향벡터
    static int[] dy = new int[]{0,0,1,-1,-1,1,1,-1,0};
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st;

        //행열 길이는 고정 길이
        N = 8;
        M = 8;

        map = new char[N][M];
        for(int i=0; i<N; i++) { //맵 입력
            String input = br.readLine();
            for(int j=0; j<M; j++) {
                map[i][j] = input.charAt(j);
            }
        }

        if(bfs(N-1)) System.out.println(1);
        else System.out.println(0);

    }

    private static boolean bfs(int x) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x,0,0}); //좌표 및 시간

        while(!q.isEmpty()) {
            int[] idx = q.poll();
            //도착했을 경우
            if(idx[0] == N-1 && idx[1] == M-1) {
                return true;
            }

            int time = idx[2]; //현재시간

            for(int i=0; i<9; i++) {
                int nx = idx[0] + dx[i];
                int ny = idx[1] + dy[i];
                if(nx<0 || ny<0 || nx>=N || ny>=M) continue; //맵 밖 예외처리

                char[][] copy = new char[N][M];
                //깊은 복사
                for(int j=0; j<N; j++) {
                    copy[j] = map[j].clone();
                }
                //time이 0이면 굳이 내릴 필요 없음.
                if(time != 0) {
                    copy = wallDown(copy, time);
                }
                
                //방문배열을 쓰지않기 때문에 무한루프 방지를 위한 로직 필요
                //매번 맵 전체에 벽이 없는 경우를 체크
                boolean nonFlag = true;
                boolean inFlag = true;
                for(int k=0; k<N; k++) {
                    for(int l=0; l<M; l++) {
                        if(copy[k][l] == '#') {
                            nonFlag = false;
                            inFlag = false;
                            break;
                        }
                    }
                    if(!inFlag) break;
                }
                if(nonFlag) return true; //맵 전체에 벽이 없다면
                
                
                if(copy[nx][ny] == '#' || copy[idx[0]][idx[1]] == '#') continue; //현재위치, 다음위치가 벽일 경우 못지나감
                
                q.offer(new int[]{nx,ny,time+1});
            }
        }
        return false;
    }

    private static char[][] wallDown(char[][] map, int time) {
        //해당 시간대에 해당하는 맵 생성 로직
        char[][] copy = new char[N][M];
        for(int i=0; i<N; i++) Arrays.fill(copy[i], '.');
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j] == '#') { //벽일 경우
                    if((i+time) > 7) map[i][j] = '.'; //맵 밖일 경우
                    else {
                        map[i][j] = '.';
                        copy[i+time][j] = '#';
                    }
                }
            }
        }
        return copy;
    }
}