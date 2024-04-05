import java.io.*;
import java.util.*;

//BOJ_1010 다리 놓기
public class Main {
    static long res;
    static int T, N, M;
    static int[][] dp = new int[30][30];    //최대값
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for(int tc=0; tc<T; tc++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());   //서쪽 사이트 개수
            M = Integer.parseInt(st.nextToken());   //동쪽 사이트 개수
            res = 0;
            /**
             * 접근법
             * DP 메모이제이션 활용
             * 동쪽의 사이트 개수에서 서쪽 사이트 개수만큼 조합을 돌리는게 정석 풀이라고 생각함.
             * 시간초가 0.5초이므로 조합으로 돌릴 경우 시간초과
             * DP를 활용한 풀이가 필수
             * 조합 -> DP로 풀어내는 점화식??
             * DFS 조합식에 메모이제이션을 활용하라는 풀이 참고
             * dp[M][N] = M과 N이 주어졌을 경우의 조합 결과
             * **/
            System.out.println(Comb(M,N));

        }
    }
    private static int Comb(int n, int r) {
        if(dp[n][r] > 0) {  //메모이제이션 되어있는 값이 있다면 return
            return dp[n][r];
        }
        if(r == 0 || r == n) {  //경우의 수가 1이기에
            return dp[n][r] = 1;
        }
        //메모이제이션이 안된 경우로 핵심 로직
        //
        return dp[n][r] = Comb(n-1, r-1) + Comb(n-1, r);
    }
}