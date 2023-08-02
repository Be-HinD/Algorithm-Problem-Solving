import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point { //bfs에서 사용할 자료형 클래스 x,y : 좌표 timer : 경과시간
    int x, y, timer;
    Point(int x, int y, int timer){
        this.x = x;
        this.y = y;
        this.timer = timer;
    }
}
public class Solution{
    static int N; //배열크기
    static int[][] arr; //수영장 map
    static int st_x, st_y, de_x, de_y; //시작지점&도착지점 변수
    static int[] dx = new int[] {-1, 0, 1, 0}; //방향벡터
    static int[] dy = new int[] {0, 1, 0, -1}; //방향벡터
    static boolean[][] visited; //방문체크
    static int ans; //결과

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int tc = Integer.parseInt(st.nextToken());
        for(int i=0; i<tc;i++) {
            st = new StringTokenizer((br.readLine()));
            N = Integer.parseInt(st.nextToken());
            //수영장 map 초기화
            arr = new int[N][N];
            for(int k=0; k<N;k++){
                st = new StringTokenizer(br.readLine());
                for(int l=0; l<N;l++){
                    arr[k][l] = Integer.parseInt(st.nextToken());
                }
            }
            st = new StringTokenizer(br.readLine());
            st_x = Integer.parseInt(st.nextToken());
            st_y = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            de_x = Integer.parseInt(st.nextToken());
            de_y = Integer.parseInt(st.nextToken());
            visited = new boolean[N][N];
            boolean check = bfs(); //boolean 리턴타입으로 도착할 수 없는 경우 체크
            if(!check) ans = -1; //도착할 수 없는 경우
            System.out.printf("#%d %d",i+1, ans);
            System.out.println();
        }
    }
    static boolean bfs(){
        Queue<Point> q = new LinkedList<>(); //큐 선언
        visited[st_x][st_y] = true; //시작지점 방문체크
        q.offer(new Point(st_x,st_y, 0)); //큐에 시작지점 추가

        while(!q.isEmpty()){ //큐가 빌 때 까지
            Point p = q.poll(); //1. 큐 poll진행

            for (int i = 0; i < 4; i++) { //큐에서 꺼낸 인덱스로부터 사방탐색
                if (p.x + dx[i] < 0 || p.x + dx[i] > N - 1 || p.y + dy[i] < 0 || p.y + dy[i] > N - 1) continue; //Null 예외 처리
                if(p.x + dx[i] == de_x && p.y + dy[i] == de_y) { //다음 행선지가 도착지라면 시간 저장 && 탈출지점
                    ans = p.timer + 1;
                    return true;
                }
                if(arr[p.x + dx[i]][p.y + dy[i]] == 1 || visited[p.x + dx[i]][p.y + dy[i]]) continue; //다음 행선지에 장애물이 있거나 방문한 곳이라면 pass
                if (arr[p.x + dx[i]][p.y + dy[i]] == 2) { //행선지가 소용돌이라면
                    if (p.timer % 3 == 2) { //사라진 상태면 진행
                        visited[p.x + dx[i]][p.y + dy[i]] = true;
                        q.offer(new Point(p.x + dx[i], p.y + dy[i], p.timer + 1));
                    } else {
                        visited[p.x][p.y] = true;
                        q.offer(new Point(p.x, p.y, p.timer+ 1));
                    }
                } else{ //행선지가 일반바다며 방문하지 않았을 경우
                    {
                        visited[p.x + dx[i]][p.y + dy[i]] = true;
                        q.offer(new Point(p.x + dx[i], p.y + dy[i], p.timer+ 1));
                    }
                }
            }
        }
        return false;
    }
}
