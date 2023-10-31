import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ_11967 불켜기
public class Main {
    static class Node {
        int x;
        int y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N, M, res;
    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};
    static int[][] map;
    static ArrayList<Node>[][] switches;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //행렬 크기
        M = Integer.parseInt(st.nextToken()); //입력 개수

        map = new int[N][N];
        map[0][0] = 1;

        //2차원 리스트행열 초기화
        switches = new ArrayList[N][N];
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                switches[i][j] = new ArrayList<>();
            }
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            switches[x][y].add(new Node(a,b)); //스위치 저장
        }

        bfs();
        bfs();

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(map[i][j] == 1) res++;
            }
        }

        System.out.println(res);

    }

    private static void bfs() {
        //1,1방에서 출발하여
        //스위치가 있는 방인지 체크
        //있다면 불 켬과 동시에 큐에 추가 혹은 반복
        //현재 방 주위 4방 탐색
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0,0});
        boolean[][] v = new boolean[N][N]; //전체 맵 방문배열
        boolean[][] sv = new boolean[N][N]; //스위치 방문배열
        v[0][0] = true;

        while(!q.isEmpty()) {
            int[] idx = q.poll();

            //현재 위치에 스위치가 있다면
            if(!switches[idx[0]][idx[1]].isEmpty()) {
                if(!sv[idx[0]][idx[1]]) {
                    sv[idx[0]][idx[1]] = true;
                    for (Node p : switches[idx[0]][idx[1]]) {
                        map[p.x][p.y] = 1; //불켜기
                        for(int i=0; i<4; i++) { //불 켠 지점을 기준으로 사방에 탐색했던 적이 있다면 큐에 추가
                            int nx = p.x + dx[i];
                            int ny = p.y + dy[i];
                            if(nx<0 || ny<0 || nx>=N || ny>=N || !v[nx][ny]) continue;
                            v[p.x][p.y] = true;
                            q.offer(new int[]{p.x,p.y});
                            break;
                        }
                    }
                }
            }

            //주변 불 켜진 지역 탐색
            for(int i=0; i<4; i++) {
                int nx = idx[0] + dx[i];
                int ny = idx[1] + dy[i];
                if(nx<0 || ny<0 || nx>=N || ny>=N || v[nx][ny]) continue;
                if(map[nx][ny] != 0) {
                    v[nx][ny] = true;
                    q.offer(new int[]{nx, ny});
                }
            }
        }
    }
}