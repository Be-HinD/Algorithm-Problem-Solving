import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main {
    static int N, L, R, sum, cnt;
    static ArrayList<int[]> list;
    static int[][] map, copyMap;
    static int[] dx = new int[]{1,-1,0,0}; //방향벡터
    static int[] dy = new int[]{0,0,1,-1};
    static boolean[][] v; //탐색 방문배열
    static boolean flag; //국경이동 유무
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N]; //맵 입력
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        while(true) {
            v = new boolean[N][N]; //방문배열 초기화
            flag = false; //플래그 초기화
            copyMap = new int[N][N];
            for(int i=0; i<N; i++) { //깊은 복사
                copyMap[i] = map[i].clone(); //원본 맵에서 모든 국경에 대해 탐색 후 한번에 인구 이동을 진행 해야 하기 때문에 카피 배열 사용
            }
            for (int i = 0; i < N; i++) { //맵 전체 탐색
                for (int j = 0; j < N; j++) {

                    list = new ArrayList<>();
                    list.add(new int[]{i, j, copyMap[i][j]}); //시작지점 추가
                    sum = copyMap[i][j]; //인구이동을 위한 sum변수 초기화
                    bfs(i, j, sum);
                }
            }
            if(!flag) break; //인구이동 불가능 할 경우
            cnt++; //시간증가
        }
        System.out.println(cnt);
    }
    private static void bfs(int x, int y, int nums) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x,y, nums});
        v[x][y] = true;

        while(!queue.isEmpty()) {
            int[] idx = queue.poll();

            for(int i=0; i<4; i++) {
                int nx = idx[0] + dx[i];
                int ny = idx[1] + dy[i];
                if(nx<0 || ny<0 || nx>N-1 || ny>N-1 || v[nx][ny]) continue;
                if(Math.abs(idx[2] - copyMap[nx][ny]) >= L) { //L이상
                    if(Math.abs(idx[2] - copyMap[nx][ny]) <= R) { //R이하
                        list.add(new int[]{nx,ny});
                        sum += copyMap[nx][ny];
                        queue.offer(new int[]{nx,ny, copyMap[nx][ny]});
                        v[nx][ny] = true;
                    }
                }
            }
        }
        if(list.size() > 1) { //리스트 개수가 2이상이라면 국경이동 가능
            flag = true;
            int spread = sum / list.size();
            for (int[] idx : list) { //인구수 갱신
                map[idx[0]][idx[1]] = spread; //값을 바꾸는 건 원본 맵으로
            }
        }
    }
}
