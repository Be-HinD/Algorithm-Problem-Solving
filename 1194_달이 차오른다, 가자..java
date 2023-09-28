import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Point {
        //좌표, 이동거리, 열쇠
        int x;
        int y;
        int cnt;
        int key;
        Point(int x, int y, int cnt, int key) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.key = key;
        }
    }
    static int R, C;
    static char[][] map;
    static int[] dx = new int[]{1,-1,0,0}; //방향벡터
    static int[] dy = new int[]{0,0,1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        Point start = null;
        for(int i=0; i<R; i++) { //맵 입력
            String input = br.readLine();
            for(int j=0; j<C; j++) {
                char idx = input.charAt(j);
                if(idx == '0') { //시작지점 추가
                    start = new Point(i,j,0,0);
                }
                map[i][j] = idx;
            }
        }
        System.out.println(bfs(start));
    }
    private static int bfs(Point start) {
        Queue<Point> queue = new ArrayDeque<>();
        //방문 배열 64만큼 선언한 이유 : 6개의 key를 가지는 조합의 경우의 수가 64개 : 즉 키 보유현황에 따라 관리를 하기 위함
        boolean[][][] v = new boolean[64][R][C];
        v[0][start.x][start.y] = true;
        queue.offer(start);

        while(!queue.isEmpty()) { //큐마다 맵 갱신 및 열쇠획득 배열관리 해주는게 포인트, 다른 힙에서는막히고, 열쇠찾는 경우의 힙에서는 열리고
            Point idx = queue.poll();
            if(map[idx.x][idx.y] == '1') return idx.cnt; //탈출지점 도착 경우
            for(int i=0; i<4; i++) {
                int nx = idx.x + dx[i];
                int ny = idx.y + dy[i];

                if(nx<0 || ny<0 || nx>=R || ny>=C || map[nx][ny] == '#' || v[idx.key][nx][ny]) continue; //예외처리 (맵 밖, 벽, 방문여부)

                // 맵의 값과 아스키코드를 통해 비교
                if(map[nx][ny] >= 'a' && map[nx][ny] <= 'f') {
                    // 열쇠를 만난 경우
                    int nextKey = 1 << (map[nx][ny] - 'a'); //만약 c를 먹을 경우 <<2를 통해 000100이 됨
                    nextKey = idx.key | nextKey; //or연산을 통해서 보유중인 원래 키는 1로 갱신되고 새로운 nextKey의 위치도 1이 됨
                    v[nextKey][nx][ny] = true; //해당 key 경우의 수 방문 배열을 갱신
                    queue.offer(new Point(nx,ny,idx.cnt+1,nextKey)); //새로운 key값으로 큐에 추가
                } else if(map[nx][ny] >= 'A' && map[nx][ny] <= 'F') {
                    // 문을 만난 경우
                    if((idx.key & 1<<(map[nx][ny] - 'A')) == (int)Math.pow(2, map[nx][ny] - 'A')) {
                        //idx.key & 1<<(map[nx][ny]-'A') : 현재 보유중인 열쇠 비트값과 and연산을 통해서 현재 문의 값만 남김
                        //ex : 111000(D,E,F 보유) -> 001000(문이 D일 경우 D만 1이 되도록)
                        //(int)Math.pow(2, map[nx][ny] - 'A') : 현재 만난 문의 제곱값 => 즉 001000을 만들어주는 구문
                        v[idx.key][nx][ny] = true;
                        queue.offer(new Point(nx,ny,idx.cnt+1,idx.key));
                    }
                } else {
                    //빈 공간을 만난 경우
                    v[idx.key][nx][ny] = true;
                    queue.offer(new Point(nx,ny,idx.cnt+1,idx.key));
                }
            }
        }
        return -1; //탈출 포인트를 도달하지 못한 경우
    }
}
