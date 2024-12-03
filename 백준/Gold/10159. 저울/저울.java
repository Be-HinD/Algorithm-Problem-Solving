import java.io.*;
import java.util.*;

//BOJ_10159
class Main {
    static int N, M, K, res;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        /**
         * 접근법
         *
         * **/

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        int[][] map = new int[N+1][N+1];
        for(int i=0; i<=N; i++) {
            map[i][i] = 1;
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            map[s][e] = 1;
            map[e][s] = -1;
        }

        for(int k=1; k<=N; k++) {
            for(int i=1; i<=N; i++) {
                for(int j=1; j<=N; j++) {
                    if(map[i][k] == 1 && map[k][j] ==1) {
                        map[i][j] =1;
                    }

                    if(map[i][k] == -1 && map[k][j] == -1) {
                        map[i][j] = -1;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i=1; i<=N; i++) {
            int cnt = 0;
            for(int j=1; j<=N; j++) {
                if(map[i][j] != 0) cnt++;
            }
            sb.append(N-cnt).append("\n");
        }

        System.out.println(sb);
    }
}