import java.io.*;
import java.util.*;

//BOJ_30894 유령의 집 탈출하기
public class Main {
    static int N, M, inx, iny, outx, outy, res;
    static int[] dx = new int[]{0,1,0,-1};      //(0 : 오른쪽, 1 : 아래, 2 : 왼쪽, 3 : 위)
    static int[] dy = new int[]{1,0,-1,0};
    static char[][] map;
    static boolean[][][] v;
    static List<int[]> list = new ArrayList();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        inx = Integer.parseInt(st.nextToken()) - 1;
        iny = Integer.parseInt(st.nextToken()) - 1;
        outx = Integer.parseInt(st.nextToken()) - 1;
        outy = Integer.parseInt(st.nextToken()) - 1;

        map = new char[N][M];
        for(int i=0; i<N; i++) {
            String input = br.readLine();
            for(int j=0; j<M; j++) {
                char idx = input.charAt(j);
                map[i][j] = idx;
                if(idx != '#' && idx != '.') list.add(new int[]{i, j, idx - '0'});
            }
        }


        v = new boolean[4][N][M];       //3차원 방문체크 배열 (유령이 바라보는 4방향에 대해 방문체크 진행)

        /*
        * 전처리 과정
        * 맵관리를 유령이 바라보는 4방향에 대해 3차원 배열로 관리하게 된다면
        * 각 초마다 유령이 바라볼 수 있는 곳들에 대해 미리 방문체크를 해놓는다면
        * BFS로직에서 O(1)의 시간으로 석준이가 해당 방향으로 갈 수 있는지 체크가 가능.
        * */
        for(int i=0; i<4; i++) {
            for(int[] ghost : list) {
                int dist = (ghost[2] + (i % 4)) % 4;        // 현재 초에 대한 유령의 방향
                detactSeokjun(ghost[0], ghost[1], dist, i);
            }
        }

        bfs();


        if(res != 0) System.out.println(res);
        else System.out.println("GG");


    }

    private static void bfs() {
        Queue<int[]> q = new ArrayDeque<>();

        q.offer(new int[]{inx, iny, 0});
        v[0][inx][iny] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int vIdx = (cur[2] + 1) % 4;      //방문배열 포인터 : 다음 Depth에 대한 포인터


            if(cur[0] == outx && cur[1] == outy) {      //도착지점에 도착했을 경우
                res = cur[2];
                break;
            }

            if(!v[vIdx][cur[0]][cur[1]]) {
                q.offer(new int[]{cur[0], cur[1], cur[2] + 1});     //제자리
            }

            for(int i=0; i<4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if(!rangeValidate(nx, ny)) continue;

                if(map[nx][ny] == '.') {
                    if(!v[vIdx][nx][ny]) {
                        q.offer(new int[]{nx, ny, cur[2] + 1});
                        v[vIdx][nx][ny] = true;
                    }
                }

            }
        }

    }

    private static boolean rangeValidate(int x, int y) {


        if(x<0 || y<0 || x>=N || y>=M) return false;
        return true;
    }

    /*
    * 입력 : 유령의 좌표, 방향, 석준이의 좌표, 방문체크 포인터
    * 로직 : 각 유령이 바라볼 수 있는 공간에 대해 방문체크 진행
    * */
    private static void detactSeokjun(int x, int y, int d, int p) {


        while(true) {
            x += dx[d];
            y += dy[d];

            if(!rangeValidate(x,y)) break;

            if(map[x][y] == '.') v[p][x][y] = true;
            else break;
            
        }
        
        
    }
}