import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, res;
    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st;

        //행열 길이는 고정 길이
        N = 12;
        M = 6;

        map = new char[N][M];
        for(int i=0; i<N; i++) { //맵 입력
            String input = br.readLine();
            for(int j=0; j<M; j++) {
                map[i][j] = input.charAt(j);
            }
        }

        while(true) {
            //연쇄가 있는지 탐색 및 제거
            boolean isDelete = false;
            for(int i=0; i<N; i++) {
                for(int j=0; j<M; j++) {
                    if(map[i][j] != '.') {
                        //뿌요가 있는 위치에서 인접 뿌요 개수 탐색
                        boolean flag = bfs(i,j, map[i][j]);
                        //4개 이상 인접해있다면 뿌요 제거
                        if(flag) {
                            isDelete = true;
                            deleteBfs(i,j,map[i][j]);
                        }
                    }
                }
            }


            //연쇄가 있었다면 중력 작용 및 로직 반복
            if(isDelete) {
                res++;
                gravity(map);
            }
            //연쇄가 없었다면 연쇄횟수 출력 및 종료
            else {
                System.out.println(res);
                return;
            }


        }

    }

    private static boolean bfs(int x, int y, char type) {
        //인접 뿌요 탐색 BFS
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] v = new boolean[N][M];
        q.offer(new int[]{x,y});
        v[x][y] = true;

        int cnt = 1;
        while(!q.isEmpty()) {
            int[] idx = q.poll();

            for(int i=0; i<4; i++) {
                int nx = idx[0] + dx[i];
                int ny = idx[1] + dy[i];
                if(nx<0 || ny<0 || nx>=N || ny>=M || v[nx][ny]) continue;
                if(map[nx][ny] == type) {
                    q.offer(new int[]{nx,ny});
                    v[nx][ny] = true;
                    cnt++;
                }
            }
        }
        return cnt > 3;
    }

    private static void deleteBfs(int x, int y, char type) {
        //인접 뿌요 제거 BFS
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] v = new boolean[N][M];
        q.offer(new int[]{x,y});
        v[x][y] = true;
        map[x][y] = '.';
        while(!q.isEmpty()) {
            int[] idx = q.poll();

            for(int i=0; i<4; i++) {
                int nx = idx[0] + dx[i];
                int ny = idx[1] + dy[i];
                if(nx<0 || ny<0 || nx>=N || ny>=M || v[nx][ny]) continue;
                if(map[nx][ny] == type) {
                    q.offer(new int[]{nx,ny});
                    v[nx][ny] = true;
                    map[nx][ny] = '.';
                }
            }
        }


    }

    private static void gravity(char[][] map) {
        //Queue를 활용해서 한 열마다 체크 및 세팅
        Queue<Character> q;

        for(int i=0; i<M; i++) { //열만큼
            q = new ArrayDeque<>();
            //아래서부터 탐색해서 값들을 큐에 추가
            for(int j=N-1; j>=0; j--) { //행만큼
                if(map[j][i] != '.') {
                    q.offer(map[j][i]);
                    map[j][i] = '.';
                }
            }
            //아래서부터 세팅
            int row = N-1;
            while(!q.isEmpty()) {
                char idx = q.poll();
                map[row--][i] = idx;
            }
        }



    }
}