import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ_17472 다리 만들기 2
public class Main {
    static class Point {
        int v;
        int w;

        public Point(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
    static int N, M, P, res;
    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy= new int[]{0,0,1,-1};
    static int[][] map;
    static ArrayList<ArrayList<Point>> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        //맵 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int idx = Integer.parseInt(st.nextToken());
                if(idx == 0) map[i][j] = -1; //바다
                else map[i][j] = -2;
            }
        }
        
        //섬 구분짓기
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j] == -2) {
                    areaDivision(i,j,P++);
                }
            }
        }

        //각 섬들에 대해 다른 섬으로 갈 수 있는 최단 경로를 탐색 및 인접리스트에 추가
        //맵을 바꾸지 않고서도 인접리스트를 생성할 수 있음.
        //인접리스트를 통해 MST 수행
        //연결여부 확인 후 결과 출력
        list = new ArrayList<>();
        for(int i=0; i<P; i++) list.add(new ArrayList<>());


        for(int idx=0; idx<P; idx++) {
            //idx번째 섬들에 대해 BFS탐색 및 리스트 형성
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if(map[i][j] == idx) {
                        bfs(i,j, idx);
                    }
                }
            }
        }

        if(Prim()) System.out.println(res);
        else System.out.println(-1);

    }

    private static void areaDivision(int x, int y, int p) {
        //섬 구분짓는 BFS
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] v = new boolean[N][M];
        q.offer(new int[]{x,y});
        v[x][y] = true;
        map[x][y] = p;

        while(!q.isEmpty()) {
            int[] cur = q.poll();

            for(int i=0; i<4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if(nx<0 || ny<0 || nx>=N || ny>=M || map[nx][ny] == -1 || v[nx][ny]) continue;
                map[nx][ny] = p;
                v[nx][ny] = true;
                q.offer(new int[]{nx,ny});
            }
        }
    }

    private static void bfs(int x, int y, int islandNum) {
        //각 섬들의 좌표 x,y에서 다른 섬으로 갈 수 있는 최단경로 탐색
        Queue<int[]> q = new ArrayDeque<>();

        //초기진행 : x,y좌표에 대해 사방탐색 진행하여 바다인곳에 대한 큐 추가
        for(int i=0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || ny < 0 || nx >= N || ny >= M ) continue;
            if(map[nx][ny] == -1) {
                q.offer(new int[]{nx,ny,i, 1}); //좌표, 방향, 다리개수
            }
        }
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int dist = cur[2];

            int nx = cur[0] + dx[dist];
            int ny = cur[1] + dy[dist];
            if(nx<0 || ny<0 || nx>=N || ny>=M) continue;

            if(map[nx][ny] != -1) { //섬에 도착했다면
                if(cur[3] == 1) continue; //다리개수가 1개라면 종료 -> 모든 경로를 탐색해야 하기 때문에 종료시키면 안됨!
                if(islandNum != map[nx][ny]) { //같은 섬으로 이어져있지 않았다면 리스트에 추가만 하고 종료하면 안됨. 계속 탐색
                    list.get(islandNum).add(new Point(map[nx][ny], cur[3]));
                }
            } else { //바다라면 다리개수 증가
                q.offer(new int[]{nx,ny,dist,cur[3]+1});
            }
        }

    }

    private static boolean Prim() {
        boolean[] v = new boolean[P]; //섬 개수만큼 방문배열
        int[] dist = new int[P]; //섬 개수만큼 경로 저장 배열
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[0] = 0; //시작위치 초기화
        
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            //가중치 기준 재정의
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        
        pq.offer(new int[]{0,0}); //0번 섬부터 MST 시작

        while(!pq.isEmpty()) {
            int[] idx = pq.poll();
            int cur = idx[0];
            int w = idx[1];

            if(v[cur]) continue; //방문한 정점이라면
            v[cur] = true;
            res += w; //결과값 갱신

            for(Point p : list.get(cur)) {
                //cur번 섬에서 갈 수 있는 모든 p섬들에 대해
                if(!v[p.v] && dist[p.v] > p.w) {
                    //프림의 변형 : p.v번 섬에 도착하는 시간을 저장 후 최소시간 갱신
                    dist[p.v] = p.w;
                    pq.offer(new int[]{p.v, p.w});
                }
            }
        }

        //모든 섬들이 연결되어 있는지를 경로배열을 통해 확인
        boolean flag = true;
        for(int idx : dist) {
            if(idx == Integer.MAX_VALUE) {
                //한 섬이라도 이어지지 않은 섬이 있다면
                flag = false;
                break;
            }
        }
        return flag;
    }
}