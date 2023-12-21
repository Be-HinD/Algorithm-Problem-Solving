import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ_12865 평범한 배낭
public class Main {
    static int N, K, P;
    static int[][] dp, item;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        item = new int[100000][2];
        P = 1;
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken()); //무게
            int happy = Integer.parseInt(st.nextToken()); //만족도
            int cnt = Integer.parseInt(st.nextToken()); //가용 개수

            int iter = 1;
            while(iter <= cnt) {
                item[P][0] = w * iter;
                item[P][1] = happy * iter;
                cnt -= iter;
                iter *= 2;
                P++;
            }

            if(cnt != 0) {
                item[P][0] = w * cnt;
                item[P][1] = happy * cnt;
                P++;
            }
        }


        dp = new int[P][K+1]; //물건 종류 수, 최대무게


        //일반적인 0-1 냅색
        for(int i=1; i<P; i++) { //i는 물건의 번호라 생각
            for(int j=1; j<=K; j++) { //j는 1부터 최대무게 K까지
                if(item[i][0] <= j) { //현재 물건을 담을 수 있다면
                    if(dp[i-1][j] < item[i][1] + dp[i-1][j-item[i][0]]) {
                        dp[i][j] = item[i][1] + dp[i-1][j-item[i][0]];
                    }
                    else {
                        dp[i][j] = dp[i-1][j];
                    }
                }
                else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

//        for(int i=0; i<P; i++) System.out.println(Arrays.toString(dp[i]));
        System.out.println(dp[P-1][K]); //최대가치 출력
    }
}