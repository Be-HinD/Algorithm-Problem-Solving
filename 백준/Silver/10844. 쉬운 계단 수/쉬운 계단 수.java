import java.io.*;

//BOJ_10844 쉬운 계단 수
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        /*
        풀이 해설
        주어지는 N의 자리수에서 계단 수가 될 수 있는 경우의 수를 탐색하는 문제
        예 : INPUT == 1, {1}, {2} ... && INPUT == 2, {1, 2}, {2,3} ...
        1의 자리수를 가지는 경우는 1 ~ 9 까지 1개의 경우의 수 고정 == 초기화
        2의 자리수부터 탐색 시작
        dp[i][j] == i는 자리수의 번호를 뜻하고, 자리수 i에 j값이 들어갈 경우의 수
        예 : dp[2][2] == 2번째 자리수(== {?, 2}) 가 2로 끝나는 N번째 계단의 수의 개수
        예를 들어, 숫자 1234에서:
        1번째 자리수는 1
        2번째 자리수는 2
        3번째 자리수는 3
        4번째 자리수는 4
        접근법
        1. 1의 자리수가 1~9일때는 모두 1개의 경우의 수를 가지니 초기화
        2. 2번째 자리수의 값이 변경될때는 값이 달라짐.
        3. i번째 자리수가 0일 때는 앞에자리수에는 1밖에 오질 못하니 dp[i][0] = dp[i-1][1];
        4. i번째 자리수가 9일 때는 앞에자리수에는 8밖에 오질 못하니 dp[i][9] = dp[i-1][8];
        5. i번째 자리수가 2~8 사이일때는 앞에 자리수에는 j+1, j-1이 올 수 있으므로 dp[i-1][j+1] + dp[i-1][j-1];
        요약하자면
        dp[i][j]는 i번째 자리수에 j라는 값이 올 때 경우의 수를 나타내고,
        dp[i-1]이라는 이전 값을 활용하여 경우의 수를 구한다.
        요약 예시
        dp[2][2] = {?,2} = {1,2} or {3,2} = dp[i-1][2+1] + dp[i-1][2-1] = {3} + {1}
        * */
        long[][] dp = new long[N+1][10];

        int mod = 1000000000;
        
        for(int i=1; i<10; i++) {
            dp[1][i] = 1;
        }

        //두 번째 자리수부터 탐색 (뒤에서부터 탐색)
        for(int i=2; i<=N; i++) {

            for(int j=0; j<10; j++) {

                if(j==0) dp[i][j] = dp[i-1][1] % mod; //j==0, 0번째 자리수의 경우 이전 자리수의 첫번째 자리수
                else if(j==9) dp[i][j] = dp[i-1][8] % mod;    //j==9, 이전 자리수는 8만 가능
                else dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1]) % mod;  //이전 자리수(dp[i-1])의 +1, -1의 합

            }
        }

        long res = 0;
        for(int i=0; i<10; i++) res += dp[N][i];

        System.out.println(res % mod);
    }
}