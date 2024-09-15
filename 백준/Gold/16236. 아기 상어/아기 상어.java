import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main {
    static int N;
    static int[][] map;
    static int pp_x, pp_y, pp_size;
    static int cnt;
    static int[][] posible_map;
    static int[] dx = new int[] {-1, 0, 0, 1}; //방향벡터
    static int[] dy = new int[] {0, -1, 1, 0}; //방향벡터
    static int time;
    static int x,y;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 9) {  //상어의 좌표 경우 객체생성
                    map[i][j] = 0;
                    pp_x = i;
                    pp_y = j;
                    pp_size = 2;
                }
            }
        }
        Point p = new Point(0, 0, 0);
        while(true) {
            posible_map = new int[N][N];
            x = Integer.MAX_VALUE;
            y = Integer.MAX_VALUE;
            bfs();
            if(x == Integer.MAX_VALUE) break;
            time = posible_map[x][y]; //경과시간 저장
            pp_x = x;
            pp_y = y;
            map[x][y] = 0;
            cnt++; //먹은 물고기 수 증가
            if(cnt == pp_size) { pp_size++; cnt = 0; }
        }
        System.out.println(time);
    }
    static void bfs() {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(pp_x, pp_y, time));
        int temp = Integer.MAX_VALUE;
        while(!queue.isEmpty()) {
            Point p = queue.poll(); // 맨 앞의 큐를 꺼내고
            if(map[p.x][p.y] > 0 && map[p.x][p.y] < pp_size){ //먹을 수 있는 곳이라면
                posible_map[p.x][p.y] = p.timer;
                //위 아래 확인
                if(temp == p.timer) { //시간이 같다면
                    if (x == p.x) {
                        //같을 시 좌 우 확인
                        if (p.y < y) {
                            x = p.x;
                            y = p.y;
                        }
                    } else if (p.x < x) {
                        x = p.x;
                        y = p.y;
                    }
                } else if(temp > p.timer){
                    x = p.x;
                    y = p.y;
                    temp = posible_map[p.x][p.y];
                }
            }
            for (int i = 0; i < 4; i++) { //큐에서 꺼낸 인덱스로부터 사방탐색 북 서 동 남
                if (p.x + dx[i] < 0 || p.x + dx[i] > N - 1 || p.y + dy[i] < 0 || p.y + dy[i] > N - 1) continue; //Null 예외 처리
                if(map[p.x + dx[i]][p.y + dy[i]] <= pp_size && posible_map[p.x + dx[i]][p.y + dy[i]] == 0){ //상어가 지나갈 수 있는 공간이라면
                    posible_map[p.x + dx[i]][p.y + dy[i]] = 1;
                    queue.offer(new Point(p.x + dx[i], p.y + dy[i], p.timer + 1)); //탐색을 위한 큐 추가
                }
                //4방탐색을 돌면서 해야할 조건들을 추가**
            }
        }
    }
}
class Point {
    int x;
    int y;
    int timer;
    public Point(int x, int y, int timer) {
        this.x = x;
        this.y = y;
        this.timer = timer;
    }
}