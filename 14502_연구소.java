import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main {
    static int N, M, Max; //가로, 세로, 최대값
    static int[][] map;
    static int[] ans; //조합을 위한 배열
    static int[] dx = new int[] {-1,1,0,0}; //방향벡터
    static int[] dy = new int[] {0,0,-1,1};
    static ArrayList<int[]> list; //바이러스 리스트
    static ArrayList<int[]> zeroList; //빈 칸 리스트 : 3개의 벽을 세울 조합을 위한 리스트

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        list = new ArrayList<>(); //바이러스 리스트 초기화
        zeroList = new ArrayList<>(); //빈칸 리스트 초기화
        for(int i=0; i<N; i++) { //맵 입력
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                int idx = Integer.parseInt(st.nextToken());
                map[i][j] = idx;
                if(idx == 2) list.add(new int[] {i,j}); //바이러스 경우
                if(idx == 0) zeroList.add(new int[] {i,j}); //빈 칸 경우
            }
        }
        ans = new int[3]; //조합배열
        Combination(0, 0); //로직
        System.out.println(Max);
    }

    private static void Combination(int cnt, int start) {
        if(cnt == 3) {
            //조합 완성
            int[][] copyMap = new int[N][M];
            for(int i=0; i<N; i++) { //깊은 복사
                copyMap[i] = map[i].clone();
            }
            for(int i=0; i<3; i++) { //완성된 조합에 따라 3개의 벽 설치
                int[] idx = zeroList.get(ans[i]);
                copyMap[idx[0]][idx[1]] = 1;
            }
            bfs(copyMap); //bfs로 바이러스 확산
        } else {
            for(int i=start; i<zeroList.size(); i++) {
                ans[cnt] = i;
                Combination(cnt + 1, i + 1);
            }
        }
    }

    private static void bfs(int[][] map) {
        Queue<int[]> queue = new ArrayDeque<>();
        for(int[] idx : list) { //바이러스 리스트에서 좌표 한개씩 꺼내서 bfs
            queue.offer(idx);

            while(!queue.isEmpty()) {
                int[] item = queue.poll();

                for(int j=0; j<4; j++) {
                    int nx = item[0] + dx[j];
                    int ny = item[1] + dy[j];
                    if(nx<0 || ny<0 || nx>N-1 || ny>M-1) continue;
                    if(map[nx][ny] == 0) { //확산 가능할 때
                        map[nx][ny] = 2;
                        queue.offer(new int[]{nx,ny});
                    }
                }
            }
        }
        int cnt = 0;
        //안전영역 크기 체크
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j] == 0) cnt++;
            }
        }
        Max = Math.max(Max, cnt); //비교 갱신
    }
}
