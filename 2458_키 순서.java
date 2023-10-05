import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, cnt;
    static int[][] map, reverseMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N+1][N+1]; //입력은 1부터
        reverseMap = new int[N+1][N+1];

        int i, j;
        for(int m=0; m<M; ++m) {
            st = new StringTokenizer(br.readLine());
            i = Integer.parseInt(st.nextToken());
            j = Integer.parseInt(st.nextToken());
            map[i][j] = 1; //i키<j키 인접행렬
            reverseMap[j][i] = 1; //j키>i키 역인접행렬
        }
        int answer = 0; //결과값
        for(int k=1; k<=N; ++k) {
            cnt =0; //연결횟수 초기화
            dfs(k, map, new boolean[N+1]); //인접
            dfs(k, reverseMap, new boolean[N+1]); //역인접
            if(cnt == N-1) answer++; //연결횟수가 모든 노드라면
        }
        System.out.println(answer);
    }

    private static void dfs(int cur, int[][] map, boolean[] v) {
        v[cur] = true; //현재 방문 노드 체크
        for(int i=1; i<=N; i++) {
            if(map[cur][i] == 1 && !v[i]) { //연결되어있고, 방문하지 않은 정점에 대해
                cnt++; //연결횟수 증가
                dfs(i, map, v); //해당 정점으로부터 탐색 시작
            }
        }
    }
}
