import java.io.*;
import java.util.*;

//BOJ_11403 경로 찾기
public class Main {
    static int N;
    static int[][] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        arr = new int[N][N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(bfs(i,j)) {
                    sb.append(1).append(" ");
                    continue;
                }
                sb.append(0).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);

    }

    private static boolean bfs(int x, int y) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(x);
        boolean[] v = new boolean[N];

        while(!q.isEmpty()) {
            int cur = q.poll();


            for(int i=0; i<N; i++) {
                if(arr[cur][i] == 1 && !v[i]) {
                    if(i == y) return true;

                    v[i] = true;
                    q.offer(i);
                }
            }
        }

        return false;
    }
}