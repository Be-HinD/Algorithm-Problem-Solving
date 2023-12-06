import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ_12865 평범한 배낭
public class Main {
    static int N, K;
    static int[][] dp, item;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        item = new int[N+1][2];
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            item[i][0] = Integer.parseInt(st.nextToken()); //무게
            item[i][1] = Integer.parseInt(st.nextToken()); //가치
        }

        dp = new int[N+1][K+1];

        for(int i=1; i<=N; i++) { //i는 물건의 번호라 생각
            for(int j=1; j<=K; j++) { //j는 1부터 최대무게 K까지
                if(item[i][0] <= j) { //현재 물건을 담을 수 있다면
                    //이전 가치보다 현재 물건의 가치가 높을 경우
                    if(item[i][1] + dp[i-1][j-item[i][0]] > dp[i-1][j]) {
                        dp[i][j] = item[i][1] + dp[i-1][j-item[i][0]]; //이전 가치에서 이전 물건의 무게만큼 뺀 dp배열을 참조.
                    }
                    else dp[i][j] = dp[i-1][j]; //이전 가치 그대로 사용
                }
                else { //담을 수 없을 경우 이전 가치를 그대로 사용
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        System.out.println(dp[N][K]); //최대가치 출력
    }
}