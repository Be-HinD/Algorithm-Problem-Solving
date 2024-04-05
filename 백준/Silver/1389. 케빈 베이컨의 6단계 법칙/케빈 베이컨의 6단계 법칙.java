import java.io.*;
import java.util.*;

//BOJ_1389 케빈 베이컨의 6단계 법칙
public class Main {
    static final int INF = 987654321;
    static int N, M;
    static int[][] floyd;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        floyd = new int[N][N];


        /**
         * 전처리
         * **/

        for(int i=0; i<N; i++) {
            Arrays.fill(floyd[i], INF);
        }

        for(int i=0; i<N; i++) {
            floyd[i][i] = 0;
        }


        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            floyd[start][end] = 1;
            floyd[end][start] = 1;
        }

        /**
         * 플로이드 워셜
         * 최초 for문 : 거쳐가는 정점의 번호
         * 2,3 번째 for문 : 2 -> 3으로 가는 최단 경로 탐색
         * **/

        for(int k=0; k<N; k++) {
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    floyd[i][j] = Math.min(floyd[i][j], floyd[i][k] + floyd[k][j]);
                }
            }
        }

        int[] arr = new int[N];
        for(int i=0; i<N; i++) {
            int cnt = 0;
            for(int j=0; j<N; j++) {
                cnt += floyd[i][j];
            }
            arr[i] = cnt;
        }

        int res = 0;
        int temp = Integer.MAX_VALUE;
        for(int i=0; i<N; i++) {
            if(temp > arr[i]) {
                temp = arr[i];
                res = i;
            }
        }

        System.out.println(res + 1);
    }
}