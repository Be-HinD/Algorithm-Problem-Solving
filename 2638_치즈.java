import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int R, C;
    static int[][] map;
    static int[] dx = new int[]{1,-1,0,0}; //방향벡터
    static int[] dy = new int[]{0,0,1,-1};
    static List<int[]> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        for(int i=0; i<R; i++) { //맵 입력
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<C; j++) {
                int idx = Integer.parseInt(st.nextToken());
                if(idx == 1) list.add(new int[]{i,j}); //치즈만 따로 리스트에 관리
                map[i][j] = idx;
            }
        }
        int ans = 0; //시간
        while(!list.isEmpty()) { //치즈가 없어질 때 까지
            bfs(); //실내 공기 값 갱신

            int[] arr = new int[list.size()];

            for(int i=0; i<list.size(); i++) { //치즈들에 대해 check함수를 통해 결과에 따라 녹일 치즈 선택
                int[] idx = list.get(i);
                if(!check(idx[0], idx[1])) { //찾을 때 마다 삭제 하면 안되고 삭제할 치즈 찾고 한번에 처리해야함.
                    arr[i] = 1; //인덱스 참조로 값 변경
                }
            }
            for(int i=0; i<arr.length; i++) {
                if(arr[i] == 1) { //제거해야할 치즈 인덱스들에 대해서 값 갱신
                    int[] idx = list.get(i);
                    map[idx[0]][idx[1]] = 2;
                }
            }
            ans++; //시간 증가
            list = new ArrayList<>(); //새로운 리스트 초기화
            for(int i=0; i<R; i++) { //맵 전체를 탐색하며 남은 치즈들에 대해 리스트 관리
                for(int j=0; j<C; j++) {
                    if(map[i][j] == 1) {
                        list.add(new int[]{i,j});
                    }
                }
            }
        }
        System.out.println(ans);
    }

    private static boolean check(int x, int y) {
        int cnt = 0;
        for(int i=0; i<4; i++) {
            int nx = x;
            int ny = y;
            for(int j=0; j<100; j++) {
                nx += dx[i];
                ny += dy[i];
                if(map[nx][ny] == 0) break;
                if (map[nx][ny] != 1) {
                    cnt++;
                    break;
                } else if(map[nx][ny] == 1) break;
            }
            if (cnt > 1) {
                return false;
            }
        }
        return true;
    }

    private static void bfs() { //0,0부터 탐색, 실내 공기의 값만 갱신
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0,0});
        boolean[][] v = new boolean[R][C];
        v[0][0] = true;
        map[0][0] = 2;
        while(!queue.isEmpty()) {
            int[] idx = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = idx[0] + dx[i];
                int ny = idx[1] + dy[i];
                if(nx<0 || ny<0 || nx>=R || ny>=C || map[nx][ny] == 1 || v[nx][ny]) continue; //전처리 : 맵 밖, 방문체크, 치즈일 경우
                map[nx][ny] = 2; //실내 공기들에 대해 임의 값 2로 갱신
                v[nx][ny] = true;
                queue.offer(new int[]{nx,ny});
            }
        }
    }
}
