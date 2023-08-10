import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static int np[];
    static int N, M, K, r, c, s;
    static int[][] map, temp, init;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        HashMap<Integer, Point> hash = new HashMap<Integer, Point>();
        N = Integer.parseInt(st.nextToken()); //배열 N
        M = Integer.parseInt(st.nextToken()); //배열 M
        K = Integer.parseInt(st.nextToken()); //연산개수 K
        //배열 입력
        map = new int[N][M];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //연산 입력
        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            s = Integer.parseInt(st.nextToken());
            Point p = new Point(r,c,s); //객체로 연산 좌표값 저장
            hash.put(i, p); //해시로 순열을 사용할 수 있도록 생성
        }
        //np 초기화 후 순열 찾기 (기존 r,c,s에 대한 좌표값을 객체로 저장해놓았기 때문에 임의값으로 순열 탐색 후 해시의 인덱스값으로 진행)
        np = new int[K];
        for(int i=0; i<np.length; i++) {
            np[i] = i;
        }
        //K번 연산 후 다음 순열 연산 진행 전 map을 되돌려줄 초기값을 임시배열에 저장
        init = new int[N][M];
        //깊은 복사
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                init[i][j] = map[i][j];
            }
        }

        int Min = Integer.MAX_VALUE; //최소값 초기화
        do{
            //깊은 복사
            for(int k = 0; k <N; k++) {
                for(int l=0; l<M; l++) {
                    map[k][l] = init[k][l];
                }
            }
            for(int i=0; i<np.length; i++) { //순열을 찾을 때마다 연산 실행 K번 실행
                calculator(hash.get(np[i]));
            }
            for(int i=0; i<N; i++) { //K번의 연산이 끝난 후 나온 결과에서 최소값 비교 후 갱신
                int res = 0;
                for(int j=0; j<M; j++) {
                    res += map[i][j];
                }
                Min = Min > res ? res : Min; //최소값 비교구문
            }
            //처리
        } while (np(np));

        //결과출력
        System.out.println(Min);
    }

    private static void calculator(Point p) {
        //백트래킹을 위한 temp 배열선언
        temp = new int[N][M];
        //깊은 복사
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                temp[i][j] = map[i][j];
            }
        }
        //몇번 반복할건지 탐색
        int iter = (p.end_x - p.start_x)/2;
        int cnt = 0;
        //배열 돌리는 연산
        while(cnt != iter) { //연산을 위한 좌표의 크기만큼 반복적으로 안까지 연산
            //윗줄 자리교체
            for (int i = p.start_y+1+cnt; i < p.end_y+1-cnt; i++) {
                temp[p.start_x + cnt][i] = map[p.start_x + cnt][i-1];
            }
            //오른쪽 세로 자리교체
            for (int i = p.start_x + 1+cnt; i < p.end_x+1-cnt; i++) {
                temp[i][p.end_y-cnt] = map[i-1][p.end_y-cnt];
            }
            //밑줄 자리교체
            for (int i = p.end_y - 1-cnt; i > p.start_y-1+cnt; i--) {
                temp[p.end_x-cnt][i] = map[p.end_x-cnt][i + 1];
            }
            //왼쪽 세로 자리교체
            for (int i = p.end_x - 1-cnt; i > p.start_x-1 +cnt; i--) {
                temp[i][p.start_y+cnt] = map[i + 1][p.start_y+cnt];
            }
            cnt++; //자리바꿈 및 범위조절을 위한 변수
        }
        //연산 후 기존 map의 참조값 변경
        map = temp;
    }

    private static boolean np(int[] p) { // p : 다음 순열을 원하는 기존 순열의 배열
        //1. 꼭대기 찾기
        int N = p.length;
        int i = N-1;
        while(i>0 && p[i-1] >=p[i]) i--;

        if(i==0) return false;

        //2. 꼭대기 직전(i-1)위치에 교환할 한단계 큰 수를 뒤쪽부터 찾기
        int j = N-1;
        while(p[i-1] >= p[j]) j--;

        //3. 꼭대기 직전(i-1)위치의 수와 찾은 j를 교환
        swap(p, i-1, j);

        //4. 꼭대기부터 맨뒤까지를 오름차순 형태로 뒤집기
        int k = N-1;
        while(i<k) {
            swap(p, i++, k--);
        }

        return true;
    }

    private static void swap(int[] p, int a, int b) {
        int temp = p[a];
        p[a] = p[b];
        p[b] = temp;
    }

}
class Point { //좌표값 저장을 위한 Point클래스
    int start_x;
    int start_y;
    int end_x;
    int end_y;
    Point(int r, int c, int s) { //배열의 시작이 1,1이 아닌 0,0이 시작이기 때문에 -1연산 수행
        this.start_x = (r-s) -1;
        this.start_y = (c-s) -1;
        this.end_x = (r+s) -1;
        this.end_y = (c+s) -1;
    }
}
