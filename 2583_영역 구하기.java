import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,M,K,x,y,xx,yy;
    static int[][] map;
    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};
    static ArrayList<Integer> list = new ArrayList<>(); //영역 개수 담을 리스트

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken()); //행열 입력이 거꾸로 들어오기 때문에 반대로 입력받음
        N =  Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M]; //입력값에 따라서 [7][5]크기의 맵으로 초기화

        for(int i=0; i<K; i++) { //맵 입력
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            xx = Integer.parseInt(st.nextToken());
            yy = Integer.parseInt(st.nextToken());
            for(int k=x; k<xx; k++) { //x,y좌표부터 xx,yy좌표까지 1로 입력
                for(int l=y; l<yy; l++) {
                    map[k][l] = 1;
                }
            }
        }

        int areaCnt = 0; //영역 개수
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j] == 0) { //완탐으로 0인곳부터 bfs로 영역탐색
                    bfs(i,j);
                    areaCnt++;
                }
            }
        }
        Collections.sort(list); //오름차순 정렬
        System.out.println(areaCnt);
        for(int i=0; i<list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
    }

    private static void bfs(int x, int y) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x,y});
        map[x][y] = 2; //방문체크용 값 변경
        int cnt = 1; //빈칸의 영역은 1부터
        while(!queue.isEmpty()) {
            int[] idx = queue.poll();
            for(int i=0; i<4; i++) {
                int nx = idx[0] + dx[i];
                int ny = idx[1] + dy[i];
                if(nx<0 || ny<0 || nx>=N || ny>=M || map[nx][ny] != 0) continue;
                map[nx][ny] = 2;
                queue.offer(new int[]{nx,ny});
                cnt++; //큐에 추가할 때마다 빈칸 영역 증가
            }
        }
        list.add(cnt); //최종적으로 구해진 빈칸 영역의 개수 리스트에 추가
    }
}
