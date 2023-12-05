import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ_16920 확장 게임
public class Main {
    static int N, M, P;
    static int[] res;
    static int[] dist;
    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};
    static char[][] map;
    static ArrayList<ArrayList<int[]>> casttleList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken()); //플레이어 수

        res = new int[P+1];

        casttleList = new ArrayList<>();
        for(int i=0; i<=P; i++) casttleList.add(new ArrayList<>());


        //이동 가능거리 Si 입력
        dist = new int[P+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=P; i++) {
            dist[i] = Integer.parseInt(st.nextToken());
        }

        //맵 입력
        map = new char[N][M];
        for(int i=0; i<N; i++) {
            String input = br.readLine();
            for(int j=0; j<M; j++) {
                char idx = input.charAt(j);
                map[i][j] = idx;
                if(idx != '.' && idx != '#') {
                    //입력이 성일 경우 각 플레이어 별 Queue에 추가
                    int player = idx - '0';
                    casttleList.get(player).add(new int[]{i,j, 0});
                    res[player]++;
                }
            }
        }

        while(true) {
            int sum = 0;
            for(int i=1; i<=P; i++) {
                bfs(i);
                sum += casttleList.get(i).size();
            }
            if(sum == 0) break; //확장된 칸이 없을 경우
        }

        for(int i=1; i<=P; i++) {
            System.out.print(res[i] + " "); //결과값 출력
        }
    }

    private static void bfs(int player) {
        Queue<int[]> q = new ArrayDeque<>();
        
        for(int[] idx : casttleList.get(player)) {
            q.offer(idx);
        }
        casttleList.get(player).clear(); //다음 턴에서 큐에 추가할 좌표를 최소화(하지않을 경우 70% 시간초과 발생)

        while(!q.isEmpty()) {
            int[] idx = q.poll();
            if(idx[2] == dist[player]) {
                //다음 턴에 진행되어야 할 좌표들을 추가
                casttleList.get(player).add(new int[]{idx[0],idx[1],0});
                continue;
            }

            for(int i=0; i<4; i++) {
                int nx = idx[0] + dx[i];
                int ny = idx[1] + dy[i];
                if(nx<0 || ny<0 || nx>=N || ny>=M) continue;
                if(map[nx][ny] == '.') { //확장 가능할 경우에만
                    map[nx][ny] = '#'; //방문체크
                    q.offer(new int[]{nx,ny, idx[2]+1});
                    res[player]++;
                }
            }
        }
    }
}