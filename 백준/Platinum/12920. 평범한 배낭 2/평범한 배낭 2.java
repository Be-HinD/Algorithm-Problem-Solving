import java.io.*;
import java.util.*;

//BOJ_12920 평범한 배낭 2
public class Main {
    static int N, M;
    static int[] dp;
    static int[][] cnt, item;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); //물건 개수
        int M = Integer.parseInt(st.nextToken()); //배낭 무게

        item = new int[N+1][3];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            item[i][0] = Integer.parseInt(st.nextToken());
            item[i][1] = Integer.parseInt(st.nextToken());
            item[i][2] = Integer.parseInt(st.nextToken());
        }

        dp = new int[M+1]; //최대무게만큼 초기화
        cnt = new int[N+1][M+1];

        for(int i=1; i<=M; i++) { //dp배열 채우기 시작
            
            int max = dp[i] = dp[i-1]; //이전 값 채워놓기; //현재 i무게에 대한 최대값 초기화
            int maxIdx = -1;


            //해당 i의 무게에서 각 물건들에 대해 최대 만족도 조사
            for(int j=0; j<N; j++) {
                if(i>=item[j][0]) { //해당 물건을 놓을 수 있을 때
                    if(max<dp[i-item[j][0]]+item[j][1] && cnt[j][i-item[j][0]] < item[j][2]) { //현재 j물건을 택했을 때 만족도가 최대값이고, 아직 전부 소진되어있지 않은 상태라면
                        max = dp[i-item[j][0]] + item[j][1]; //max값 갱신
                        maxIdx = j;
                    }
                }
            }

            if(maxIdx != -1) { //이전 무게에서 보다 최대값이 갱신이 된다면
                for(int j=0; j<N; j++) {
                    cnt[j][i] = cnt[j][i-item[maxIdx][0]];
                }
                cnt[maxIdx][i]++;
                dp[i] = max;
            }
            else {
                for(int j=0; j<=N; j++) {
                    cnt[j][i] = cnt[j][i-1];
                }
            }

        }

        System.out.println(dp[M]);

    }
}