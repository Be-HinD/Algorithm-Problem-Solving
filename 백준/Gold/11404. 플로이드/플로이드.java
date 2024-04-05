import java.io.*;
import java.util.*;

//BOJ_11404 플로이드
public class Main {
    static final int INF = 987654321;
    static int N, M;
    static int[][] floyd;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        floyd = new int[N][N];

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
            int weight = Integer.parseInt(st.nextToken());

            floyd[start][end] = Math.min(floyd[start][end], weight);
        }


        /**
         * 플로이드 워셜 3중 for문
         * **/

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                for(int k=0; k<N; k++) {
                    floyd[j][k] = Math.min(floyd[j][k], floyd[j][i] + floyd[i][k]);
                }
            }
        }



        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(floyd[i][j] == INF) {
                    sb.append(0).append(' ');
                    continue;
                }
                sb.append(floyd[i][j]).append(' ');
            }
            sb.append("\n");
        }

        System.out.println(sb);

    }
}