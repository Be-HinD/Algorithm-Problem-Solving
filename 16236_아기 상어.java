import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N; //배열 크기
    static int[][] map; //물고기 정보맵
    static int pp_x, pp_y, pp_size, cnt; //상어 정보(좌표,크기,먹은회수)
    static int[][] posible_map; //먹을 수 있는 물고기 상태맵
    static int[] dx = new int[] {-1, 0, 0, 1}; //방향벡터
    static int[] dy = new int[] {0, -1, 1, 0}; //방향벡터
    static int time; //answer
    static int x,y; //bfs로 이동시킬 좌표
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) { //2차원배열 입력
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 9) {  //상어의 좌표 경우 정보입력
                    map[i][j] = 0;
                    pp_x = i;
                    pp_y = j;
                    pp_size = 2;
                }
            }
        }

        while(true) { //탈출조건은 먹을 수 있는 물고기의 좌표를 찾지 못했을 경우
            posible_map = new int[N][N];
            //먹을 수 있는 물고기의 좌표 초기화
            x = Integer.MAX_VALUE;
            y = Integer.MAX_VALUE;
            
            bfs();
            
            if(x == Integer.MAX_VALUE) break; //탈출조건 (bfs에서 찾지못해 갱신못했을 경우)
            
            //else 먹을 수 있는 물고기가 있을 경우 -> 상어 정보 갱신
            time = posible_map[x][y];
            pp_x = x;
            pp_y = y;
            map[x][y] = 0;
            cnt++; //먹은 물고기 수 증가
            if(cnt == pp_size) { pp_size++; cnt = 0; } //상어 크기 증가 조건
        }

        System.out.println(time);
    }


    static void bfs() {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(pp_x, pp_y, time));
        int temp = Integer.MAX_VALUE; //도착시간 비교용
        
        while(!queue.isEmpty()) {
            Point p = queue.poll();
            if(map[p.x][p.y] > 0 && map[p.x][p.y] < pp_size){ //먹을 수 있는 곳이라면
                posible_map[p.x][p.y] = p.timer; //현재좌표에 도착시간 갱신
                if(temp == p.timer) { //이전에 갱신된 도착시간과 같다면
                    if (x == p.x) { //위 아래 확인
                        //같을 시 좌 우 확인
                        if (p.y < y) {
                            x = p.x;
                            y = p.y;
                        }
                    } else if (p.x < x) { //현재 물고기가 이전 물고기보다 도착시간이 같고 더 위에 있을 경우 갱신
                        x = p.x;
                        y = p.y;
                    }
                } else if(temp > p.timer){ //도착시간이 더 짧을 경우 갱신
                    x = p.x;
                    y = p.y;
                    temp = posible_map[p.x][p.y];
                }
            }
            for (int i = 0; i < 4; i++) { //큐에서 꺼낸 인덱스로부터 사방탐색 북 서 동 남 (방향은 상관없음)
                if (p.x + dx[i] < 0 || p.x + dx[i] > N - 1 || p.y + dy[i] < 0 || p.y + dy[i] > N - 1) continue; //Null 예외 처리
                if(map[p.x + dx[i]][p.y + dy[i]] <= pp_size && posible_map[p.x + dx[i]][p.y + dy[i]] == 0){ //상어가 지나갈 수 있는 공간이라면 (물고기크기비교 && visited 체크)
                    posible_map[p.x + dx[i]][p.y + dy[i]] = 1; //visited용 쓰레기값 1로 대신 사용
                    queue.offer(new Point(p.x + dx[i], p.y + dy[i], p.timer + 1)); //탐색을 위한 큐 추가
                }
            }
        }
    }
}

class Point { //bfs를 위한 객체 (좌표, 걸린시간 증가용)
    int x;
    int y;
    int timer;
    public Point(int x, int y, int timer) {
        this.x = x;
        this.y = y;
        this.timer = timer;
    }
}
