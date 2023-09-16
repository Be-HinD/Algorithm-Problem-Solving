import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main {
    static int N, M, Min;
    static int[][] map;
    static int[] ans;
    static int[] dx = new int[] {-1,1,0,0};
    static int[] dy = new int[] {0,0,-1,1};
    static Queue<int[]> queue;
    static ArrayList<int[]> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        list = new ArrayList<>(); //바이러스 놓을 수 있는 좌표값 리스트
        for(int i=0; i<N; i++) { //맵 입력
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                int idx = Integer.parseInt(st.nextToken());
                map[i][j] = idx;
                if(idx == 2)  { //바이러스를 놓을 수 있는 위치에 대해서
                    map[i][j] = 0; //0으로 갱신
                    list.add(new int[] {i,j}); //리스트에 추가
                }

            }
        }
        ans = new int[M];
        Min = Integer.MAX_VALUE; //최소값 비교를 위한 초기화
        Combination(0, 0);
        if(Min == Integer.MAX_VALUE) System.out.println(-1); //바이러스를 전부 퍼트릴 수 없는 경우
        else System.out.println(Min);
    }

    private static void Combination(int cnt, int start) {
        if(cnt == M) {
            //조합 완성
            int[][] copyMap = new int[N][N];
            for(int i=0; i<N; i++) { //깊은 복사
                copyMap[i] = map[i].clone();
            }
            queue = new ArrayDeque<>();
            for(int i=0; i<M; i++) { //완성된 조합에 따라 M개의 바이러스 설치
                int[] idx = list.get(ans[i]);
                queue.offer(new int[] {idx[0], idx[1], 0});
                copyMap[idx[0]][idx[1]] = 2;
            }
            bfs(copyMap); //bfs로 바이러스 확산
        } else {
            for(int i=start; i<list.size(); i++) {
                ans[cnt] = i;
                Combination(cnt + 1, i + 1);
            }
        }
    }

    private static void bfs(int[][] map) {
        int cnt = 0; //시간비교를 위한 변수 초기화

        while(!queue.isEmpty()) {
            int[] item = queue.poll();
            cnt = Math.max(cnt, item[2]); //퍼트리는 시간 갱신
            for(int j=0; j<4; j++) {
                int nx = item[0] + dx[j];
                int ny = item[1] + dy[j];
                if(nx<0 || ny<0 || nx>N-1 || ny>N-1) continue;
                if(map[nx][ny] == 0) { //확산 가능할 때
                    map[nx][ny] = 2;
                    queue.offer(new int[]{nx,ny, item[2] + 1}); //시간 증가
                }
            }
        }
        for(int i=0; i<N; i++) { //바이러스가 전부 퍼지지 않았다면 값 갱신 x
            for(int j=0; j<N; j++) {
                if(map[i][j] == 0) return;
            }
        }
        Min = Math.min(Min, cnt); //최소값 갱신
    }
}
