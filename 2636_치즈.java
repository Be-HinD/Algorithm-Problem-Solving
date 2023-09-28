import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int R, C, cnt, ans, cheeseCnt;
    static int[][] map;
    static int[] dx = new int[]{1,-1,0,0}; //방향벡터
    static int[] dy = new int[]{0,0,1,-1};
    static List<int[]> list = new ArrayList<>();
    static List<Integer> cntList = new ArrayList<>();

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

        if(!list.isEmpty()) cheeseCnt = list.size(); //치즈가 1개 이상 있을 경우 치즈개수 저장

        while(!list.isEmpty()) {
            bfs(); //0,0기준 치즈 바깥 공기들에 대해 값 갱신

            int[] deleteList = new int[list.size()]; //제거할 치즈를 관리할 배열
            for(int i=0; i<list.size(); i++) { //찾을 때 마다 값 변경하면 안됨.
                int[] idx = list.get(i);
                if(!check(idx[0], idx[1])) { //실외공기와 맞닿아 있는 치즈들에 대해
                    deleteList[i] = 1;
                }
            }

            for(int i=0; i<deleteList.length; i++) { //일괄변경
                if(deleteList[i] == 1) { //제거할 치즈라면
                    int[] idx = list.get(i);
                    map[idx[0]][idx[1]] = 2; //실외공기와 같은 값으로 변경
                }
            }

            list = new ArrayList<>();
            cnt = 0; //값 초기화
            for(int i=0; i<R; i++) {
                for(int j=0; j<C; j++) {
                    if(map[i][j] == 1) {
                        cnt++;
                        list.add(new int[]{i,j}); //치즈에 대한 새로운 리스트 관리
                    }
                }
            }
            cntList.add(cnt); //치즈개수 관리
            ans++; //결과시간 증가
        }

        //2초 이상 걸릴 경우 이전 치즈개수를 참조, 1초에 끝이 났다면 처음에 저장해놓은 값 출력
        if(cntList.size() > 1) cheeseCnt = cntList.get(cntList.size() - 2);
        System.out.println(ans + "\n" + cheeseCnt); //걸린 시간 및 이전 치즈개수 출력
    }

    private static boolean check(int x, int y) { //치즈좌표에 대해 해당 치즈가 제거되는지 체크하는 메서드
        for(int i=0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(map[nx][ny] == 2) return false; //주변 4방 중 실외공기가 한개라도 맞닿아 있다면
        }
        return true;
    }

    private static void bfs() { //실외 공기에 대한 값 변경을 위한 너비탐색
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0,0});
        boolean[][] v = new boolean[R][C];
        v[0][0] = true;

        while(!queue.isEmpty()) {
            int[] idx = queue.poll();

            for(int i=0; i<4; i++) {
                int nx = idx[0] + dx[i];
                int ny = idx[1] + dy[i];
                if(nx<0 || ny<0 || nx>=R || ny>=C || map[nx][ny] == 1 || v[nx][ny]) continue; //전처리 : 맵 밖, 치즈, 방문체크
                map[nx][ny] = 2;
                v[nx][ny] = true;
                queue.offer(new int[]{nx,ny});
            }
        }
    }
}
