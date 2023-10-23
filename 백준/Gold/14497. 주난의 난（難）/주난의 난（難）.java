import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, res;
    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};
    static int[] point = new int[4];
    static char[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<4; i++) {
            point[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        map = new char[N][M];
        for(int i=0; i<N; i++) {
            String input = br.readLine();
            for(int j=0; j<M; j++) {
                map[i][j] = input.charAt(j);
            }
        }

        System.out.println(Dijkstra());
    }

    private static int Dijkstra() {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });
        int[][] dist = new int[N][M];
        for(int i=0; i<N; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        for(int i=0; i<4; i++) {
            int nx = point[0] + dx[i];
            int ny = point[1] + dy[i];
            if(nx<0 || ny<0 || nx>=N || ny>=M) continue;
            dist[nx][ny] = 1;
            pq.offer(new int[]{nx,ny,1}); //주난이 위치, 점프횟수, 방향
        }

        while(!pq.isEmpty()) {
            int[] idx = pq.poll();

            char flag = map[idx[0]][idx[1]]; //해당 위치의 값

            if(flag == '#') return idx[2];
            if(flag == '1') { //친구일 경우
                //해당 위치에서 다시 점프
                //사방탐색 및 해당 진행방향 변경
                for(int i=0; i<4; i++) {
                    int nx = idx[0] + dx[i];
                    int ny = idx[1] + dy[i];
                    if(nx<0 || ny<0 || nx>=N || ny>=M || idx[2]+1 >= dist[nx][ny]) continue;
                    dist[nx][ny] = idx[2]+1;
                    pq.offer(new int[]{nx,ny,idx[2]+1}); //주난이 위치, 점프횟수
                }
            } else { //빈 공간일 경우 같은 점프횟수로 갱신
                for(int i=0; i<4; i++) {
                    int nx = idx[0] + dx[i];
                    int ny = idx[1] + dy[i];
                    if (nx < 0 || ny < 0 || nx >= N || ny >= M || idx[2] >= dist[nx][ny]) continue;
                    dist[nx][ny] = idx[2];
                    pq.offer(new int[]{nx, ny, idx[2]}); //주난이 위치, 점프횟수
                }
            }
        }
        return -1;
    }
}