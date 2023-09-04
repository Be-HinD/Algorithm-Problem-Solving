import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;
public class Main {
    static int N, M, time; //가로, 세로, 걸린시간
    static int[][] map;
    static int[] dx = new int[] {-1,1,0,0}; //방향벡터
    static int[] dy = new int[] {0,0,-1,1}; //방향벡터
    static ArrayList<int[]> iceList; //빙산 위치 리스트

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        iceList = new ArrayList<>();
        for(int i=0; i<N; i++) { //맵 입력
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                int idx = Integer.parseInt(st.nextToken());
                map[i][j] = idx;
                if(idx != 0) iceList.add(new int[]{i,j}); //빙산 리스트 추가
            }
        }

        while(true) {
            isValidate(); //빙산 덩어리 확인
            iceBreak(); //빙산 녹이는 과정 +1년
            time++; //걸린 시간 증가
            if(iceList.isEmpty()) { //빙산이 다녹을 때 까지 한 덩어리일 경우
                System.out.println(0);
                break;
            }
        }

    }

    private static void iceBreak() {
        int[] arr = new int[iceList.size()]; //감소값 담아줄 배열
        for(int i=0; i<iceList.size(); i++) {
            int[] idx = iceList.get(i); //빙산 한 개씩 꺼내서
            int cnt = 0;
            for(int j=0; j<4; j++) { //4방향 탐색 0이 있는 위치의 개수 파악
                int nx = idx[0] + dx[j];
                int ny = idx[1] + dy[j];

                if(nx<0 || ny<0 || nx>N-1 || ny>M-1) continue;
                if(map[nx][ny] == 0) cnt++;
            }
            arr[i] = cnt;
        }
        int id = 0;
        for(int element : arr) { //빙산을 녹이면서
            int[] idx = iceList.get(id);
            int value = map[idx[0]][idx[1]] - element;
            if(value < 1) { //0이하로 감소할 경우 맵 값 갱신 및 빙산 리스트에서 제거
                map[idx[0]][idx[1]] = 0;
                iceList.remove(id);
            }
            else { //맵 값만 갱신 및 인덱스 포인터 증가
                map[idx[0]][idx[1]] = value;
                id++;
            }
        }
    }

    private static void isValidate() {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] v = new boolean[N][M];
        int cnt = 0;
        for(int[] element : iceList) { //모든 빙산에 대해서
            if(v[element[0]][element[1]]) continue;
            cnt++; //덩어리 개수 체크
            queue.offer(element);

            while(!queue.isEmpty()) { //BFS로 방문체크
                int[] item = queue.poll();

                for(int j=0; j<4; j++) {
                    int nx = item[0] + dx[j];
                    int ny = item[1] + dy[j];

                    if(nx<0 || ny<0 || nx>N-1 || ny>M-1 || map[nx][ny] == 0) continue;
                    if(!v[nx][ny]) {
                        v[nx][ny] = true;
                        queue.offer(new int[] {nx,ny});
                    }
                }
            }
        }
        if(cnt > 1) { //빙산이 두 덩어리 이상일 경우
            System.out.println(time);
            System.exit(0);
        }
    }
}
