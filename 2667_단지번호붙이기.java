import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main {
    static int N, cnt;
    static String input; //한줄입력
    static int map[][];
    static int[] dx = new int[] {-1,1,0,0}; //방향벡터
    static int[] dy = new int[] {0,0,-1,1};
    static ArrayList<Integer> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();
        N = Integer.parseInt(st.nextToken()); //행렬크기 입력

        map = new int[N][N]; //단지 맵 초기화 및 입력
        for(int i=0; i<N; i++) {
            input = br.readLine();
            for(int j=0; j<N; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }
        list = new ArrayList<>(); //리스트 초기화
        
        for(int i=0; i<N; i++) { //맵을 돌면서 1인 좌표를 찾으면 bfs
            for(int j=0; j<N; j++) {
                if(map[i][j] == 1) {
                    cnt = 0; //count변수 초기화
                    bfs(new int[]{i, j});
                    list.add(cnt); //결과값 정렬을위한 리스트에 추가
                }
            }
        }
        Collections.sort(list); //오름차순 정렬
        sb.append(list.size() + "\n"); //단지개수 출력
        for(int i=0; i<list.size(); i++) { //결과 StringBuilder에 추가
            sb.append(list.get(i) + "\n");
        }
        System.out.print(sb);
    }

    private static void bfs(int[] arr) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(arr);

        map[arr[0]][arr[1]] = 0; //방문체크배열 대신 0으로 변경
        cnt++;

        while(!queue.isEmpty()) {
            int[] idx = queue.poll();
            for(int i=0; i<4; i++) {
                int nx = idx[0] + dx[i];
                int ny = idx[1] + dy[i];

                if(nx<0 || ny<0 || nx>=N || ny>=N) continue;
                if(map[nx][ny] != 0) {
                    queue.offer(new int[]{nx,ny});
                    map[nx][ny] = 0;
                    cnt++;
                }
            }
        }
    }
}
