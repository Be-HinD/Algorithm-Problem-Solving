import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static int N, res;
    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};
    static int[][] map;
    static boolean[][] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine()); //정점 개수

        map = new int[N][N];
        for(int i=0; i<N; i++) { //맵 입력
            String input = br.readLine();
            for(int j=0; j<N; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }

        v = new boolean[N][N];

        System.out.println(bfs());

    }

    private static int bfs() {
        //벽 부순 개수를 기준으로 정렬 재정의
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });
        
        pq.offer(new int[]{0,0,0}); //시작좌표, 벽 부순 개수
        v[0][0] = true;

        while(!pq.isEmpty()) {
            int[] idx = pq.poll();
            
            if(idx[0] == N-1 && idx[1] == N-1) return idx[2]; //도착 경우 벽 부순 개수 return
            
            for(int i=0; i<4; i++) {
                int nx = idx[0] + dx[i];
                int ny = idx[1] + dy[i];
                if(nx<0 || ny<0 || nx>=N || ny>=N || v[nx][ny]) continue;


                if(map[nx][ny] == 0) { //벽이라면
                    pq.offer(new int[]{nx,ny,idx[2]+1});
                    v[nx][ny] = true;
                    continue;
                }
                pq.offer(new int[]{nx,ny,idx[2]});
                v[nx][ny] = true;
            }
        }
        return -1;
    }
}