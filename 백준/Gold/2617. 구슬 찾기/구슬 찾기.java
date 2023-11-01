import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ_11967 불켜기
public class Main {
    static int N, M, res;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //행렬 크기
        M = Integer.parseInt(st.nextToken()); //입력 개수

        map = new int[N][N];

        //플로이드 워셜 초기화 단계
        for(int i=0; i<N; i++) {
            Arrays.fill(map[i], Integer.MAX_VALUE);
        }

        for(int i=0; i<N; i++) { //자기 자신으로 가는 경로는 항상 존재
            map[i][i] = 1;
        }

        //입력
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            map[x][y] = 1; //x가 y보다 무겁다는 의미
        }

        Floyd();

        int mid = N/2; //해당 mid값을 초과하면 절대 중앙값이 될 수 없음을 의미

        for(int i=0; i<N; i++) { //각 정점들(i)에 대해서
            int leftCnt = 0; //i정점보다 작은 정점들의 개수
            int rightCnt = 0; //i정점보다 큰 정점들의 개수
            for(int j=0; j<N; j++) {
                if(i == j) continue;
                if(map[i][j] == 1) leftCnt++; //i정점이 j보다 크다면
                if(map[j][i] == 1) rightCnt++; //j정점이 i보다 크다면
            }
            if(leftCnt > mid || rightCnt > mid) res++; //결과값 갱신
        }

        System.out.println(res);
    }

    private static void Floyd() {
        for(int k=0; k<N; k++) { //각 정점들을 방문하는 경로에 대해서
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    //i에서 j로 가는 경로가 존재한다면 값 갱신
                    if(map[i][k] == 1 && map[k][j] == 1) map[i][j] = 1;
                }
            }
        }
    }
}