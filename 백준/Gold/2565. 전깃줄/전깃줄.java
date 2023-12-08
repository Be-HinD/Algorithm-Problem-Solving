import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ_2565 전깃줄
public class Main {
    static int N;
    static int[]dp;
    static int[][] lis;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        lis = new int[N][2];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            lis[i][0] = Integer.parseInt(st.nextToken());
            lis[i][1] = Integer.parseInt(st.nextToken());
        }

        //A전봇대를 기준으로 오름차순 정렬
        Arrays.sort(lis, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        dp = new int[N];
        for(int i=0; i<N; i++) {
            dp[i] = 1; //i번째 전봇대의 lis는 1부터 시작
            for(int j=0; j<i; j++) {
                //i번째 A전봇대와 연결된 B전봇대의 값보다 j번째가 더 작을 경우
                if(lis[j][1] < lis[i][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int max = 0;
        for(int i = 0; i < N; i++) {
            max = Math.max(max, dp[i]);
        }
        System.out.println(N - max);

    }
}