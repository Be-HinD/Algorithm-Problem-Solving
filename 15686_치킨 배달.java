import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main {
    static int N, M, diff, Min;
    static int[][] map, copyMap;
    static int[] ans;
    static int[] dx = new int[]{1,-1,0,0}; //방향벡터
    static int[] dy = new int[]{0,0,1,-1};
    static ArrayList<int[]> homeList; //집 리스트
    static ArrayList<int[]> list; //치킨집 리스트
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        // r과 c는 1부터 시작한다. 512 MB
        map = new int[N][N];
        list = new ArrayList<>();
        homeList = new ArrayList<>();
        for(int i=0; i<N; i++) { //맵 입력
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                int idx = Integer.parseInt(st.nextToken());
                map[i][j] = idx;
                if(idx == 2) { //치킨집 좌표 추가
                    map[i][j] = 0;
                    list.add(new int[]{i,j});
                } else if(idx == 1) homeList.add(new int[]{i,j}); //집 좌표 추가
            }
        }
        Min = Integer.MAX_VALUE; //최소값 비교를 위한 초기화
        ans = new int[M];
        Combination(0, 0);
        System.out.println(Min);
    }
    private static void Combination(int cnt, int start) {
        if(cnt == ans.length) {
            //조합 완성
            copyMap = new int[N][N];
            for(int i=0; i<N; i++) { //깊은 복사 : M개의 치킨집만 맵에 설치하기 위해 초기화
                copyMap[i] = map[i].clone();
            }
            for(int idx : ans) { //치킨집 설치
                int[] point = list.get(idx);
                copyMap[point[0]][point[1]] = 2;
            }

            diff = 0; //매 조합 마다 거리값 비교를 위한 초기화
            for(int[] idx : homeList) { //각 집 마다의 치킨 거리값 구하기
                bfs(idx[0], idx[1]);
                if(diff >= Min) return; //최적화
            }
            Min = Math.min(Min, diff); //최소값 비교 및 갱신
        } else {
            for(int i=start; i<list.size(); i++) {
                ans[cnt] = i;
                Combination(cnt+1, i+1);
            }
        }
    }

    private static void bfs(int x, int y) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x,y,0});
        boolean[][] v = new boolean[N][N];
        v[x][y] = true;

        while(!queue.isEmpty()) {
            int[] idx = queue.poll();

            for(int i=0; i<4; i++) {
                int nx = idx[0] + dx[i];
                int ny = idx[1] + dy[i];
                if(nx<0 || ny<0 || nx>=N || ny>=N || v[nx][ny]) continue;
                if(copyMap[nx][ny] == 2) { //치킨집을 찾게되면 diff값 갱신 및 종료
                    diff += idx[2]+1;
                    return;
                }
                v[nx][ny] = true;
                queue.offer(new int[]{nx,ny,idx[2]+1});
            }
        }
    }
}
