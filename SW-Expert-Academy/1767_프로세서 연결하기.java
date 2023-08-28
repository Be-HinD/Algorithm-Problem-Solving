import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {

    static int N, max, totalCnt, min, map[][];
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static ArrayList<int[]> list; //코어리스트
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        //1. 코어리스트 생성(가장자리 코어 제외)
        //2. 부분집합으로 전원을 연결할 코어들을 정의
        //선택 : 4방향 시도 (해당 방향으로 전선놓기가 가능한지 판단)
        //비선택 : 처리 x
        //전선놓기가 가능하다면 디버깅고려 2차원입력에 0과 1이아닌 다른 값으로 흔적을 남김(백트래킹)
        //기저조건 : 연결된 코어개수, 전선길이 비교

        int tc = Integer.parseInt(st.nextToken());
        for(int t=1; t<=tc; t++) {
            N = Integer.parseInt(br.readLine()); //멕시노스 크기
            map = new int[N][N];
            max = 0;
            min = Integer.MIN_VALUE;
            totalCnt = 0; //연결해야하는 코어 개수
            list = new ArrayList<int[]>(); //연결해야하는 코어 리스트

            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    //가장자리 코어 제외
                    if((i == N-1 || i == 0 || j == N-1 || j ==0) && map[i][j] == 1) continue;
                    if(map[i][j] == 1) { //코어
                        list.add(new int[] {i,j});
                        totalCnt++;
                    }
                }
            }
            go(0, 0);
            System.out.println("#" + t + " " + min);
        }
    }
    //코어를 선택(4방향 시도) / 비선택
    private static void go(int index, int coreCnt) { //index : 고려해야할 코어의 index, coreCnt : 연결된 코어 개수
        //가지치기 : 현재까지 연결된 코어 수 + 남은 코어 수 < 임시 최대 코어 연결 수
        if(coreCnt+(totalCnt-index) < max) return;
        //기저조건 처리
        if(index == totalCnt) {
            int res = getLength(); //놓아진 전선의 길이의 합
            if(coreCnt > max) { //최적해 갱신
                max = coreCnt;
                min = res;
            } else if(max == coreCnt) { //코어개수가 같다면 전선길이 비교 후 최적해 갱신
                if(min > res) min = res;
            }
            return;
        }
        int[] cur = list.get(index);
        int r = cur[0];
        int c = cur[1];
        //현재 코어 선택 (4방향 시도)
        for (int d=0; d<4; d++) {
            //현재 코어의 위치에서 해당 방향으로 전선 놓기가 가능한지 체크
            if(!isAvailable(r, c, d)) continue;
            //전선 놓기가 가능하다면 전선 놓기
            setStatus(r, c, d, 2);
            go(index + 1, coreCnt + 1); //다음 코어 진행
            setStatus(r, c, d, 0); //백트래킹(전선 지우기)
        }
        //현재 코어 비선택
        go(index +1, coreCnt);
    }
    //현재 코어로부터 전선을 놓을 수 있는지 판단해주는 메서드
    private static boolean isAvailable(int r, int c, int d) {
        int nr = r;
        int nc = c;

        while(true) {
            nr += dx[d];
            nc += dy[d];
            if(nr<0 || nr>=N || nc<0 || nc>=N) break;
            if(map[nr][nc] == 1 || map[nr][nc] == 2) return false;
        }
        return true;
    }
    //전선을 놓거나 제거하는 메서드
    private static void setStatus(int r, int c, int d, int status) {
        int nr = r;
        int nc = c;

        while(true) {
            nr += dx[d];
            nc += dy[d];
            if(nr<0 || nr>=N || nc<0 || nc>=N) break;
            map[nr][nc] = status;
        }
    }
    //현재 맵상태에서 전선의 길이를 리턴하는 메서드
    private static int getLength() {
        int lCnt = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(map[i][j] == 2) lCnt++;
            }
        }
        return lCnt;
    }
}
