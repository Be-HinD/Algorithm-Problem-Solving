import java.io.*;
import java.util.*;

//BOJ_14728 벼락치기
public class Main {
    static class PROBLEM implements Comparator<PROBLEM> {
        int time;
        int score;
        public PROBLEM(int time, int score) {
            this.time = time;
            this.score = score;
        }

        @Override
        public int compare(PROBLEM o1, PROBLEM o2) {
            return o1.time - o2.time;
        }
    }
    static int N, T;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   //단원 개수
        T = Integer.parseInt(st.nextToken());   //남은 시간

        PROBLEM[] arr = new PROBLEM[N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = new PROBLEM(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        int[][] dp = new int[T+1][N+1];
        for(int i=1; i<=N; i++) {
            PROBLEM problem = arr[i-1];
            for(int j=1; j<dp.length; j++) {
                if(problem.time <= j) {
                    dp[j][i] = Math.max(dp[j- problem.time][i-1] + problem.score, dp[j][i-1]);
                }
            }
        }

        int[] dps = new int[T+1];


        for(int i=0; i<N; i++) {
            PROBLEM problem = arr[i];
            for(int j=dps.length-1; j>=problem.time; j--) {
                dps[j] = Math.max(dps[j], dps[j-problem.time] + problem.score);
            }
        }
//        System.out.println(Arrays.toString(dps));

//        for(int i=0; i<T+1; i++) {
//            System.out.println(Arrays.toString(dp[i]));
//        }

//        System.out.println(dp[T][N]);

        System.out.println(dps[T]);

    }
}