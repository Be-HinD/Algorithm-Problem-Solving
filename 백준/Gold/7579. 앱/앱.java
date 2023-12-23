import java.io.*;
import java.util.*;

//BOJ_7579 앱
public class Main {
    static int N, M, res;
    static int[][] item;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //앱의 개수
        M = Integer.parseInt(st.nextToken()); //필요한 바이트 수

        item = new int[N][2];

        //필요한 바이트 수 입력
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            item[i][0] = Integer.parseInt(st.nextToken());
        }

        //비용 입력
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            item[i][1] = Integer.parseInt(st.nextToken());
        }

        //기존의 0-1 배낭 문제
        // -> 각 물건들을 비교해서 해당 물건을 넣었을 때가 최적이라면 값 갱신
        // 최적이 아니라면 이전 값을 재사용

        //접근법
        //구하고자 하는 바이트 M까지 각 바이트를 채우기 위한 최소비용을 구하는 것을 부분 문제로 두고 풀이.
        //각 앱들을 통해 확보할 수 있는 바이트 수와 최소값을 구하고 그 중 최소해를 찾는 풀이.

        //새로운 접근법 찾아서..
        //기존의 0-1과 달리 해당문제는 10일 때 해당 물건을 넣을 수 있는게 아니라 0~해당 물건까지의 값이 동일해지는 게문제
        //0-1처럼 풀기 위해서 변형이 조금 필요한데,

        //메모리의 값 자체가 최대 천만까지 나옴으로 행이나 열로 둘 수 없으니 cost를 행으로 두고 배열의 값을 메모리로 두고 풀이.
        //각 코스트 별로
        //cost i를 만족할 수 있는 최대 byte 수를 원소값으로

        int[] dp = new int[10001]; //결과로 나올 수 있는 최대 cost는 100(최대 cost) * 100(N)
//        for(int i=1; i<10001; i++) {
//            dp[i] = dp[i-1];
//            for(int j=0; j<N; j++) {
//                int cost = item[j][1];
//                if(i >= cost) { //현재 아이템의 코스트를 뺀 값이 0이상일 경우
//                    dp[i] = Math.max(dp[i], dp[i-item[j][1]] + item[j][0]);
//                }
//            }
//        }

//        for(int i=0; i<N; i++) {
//            for(int j=item[i][1]; j<=10000; j++) { //처음 접근은 j를 0부터 끝까지 비교하면서 이전 값을 재활용하는 것이였는데, 이러면 부분문제 해가 이상
//                dp[j] = Math.max(dp[j], dp[j - item[i][1]] + item[i][0]);
//            }
//        }

        for(int i=0; i<N; i++) {
            for(int j=10000; j>=item[i][1]; j--) { //처음 접근은 j를 0부터 끝까지 비교하면서 이전 값을 재활용하는 것이였는데, 이러면 부분문제 해가 이상
                dp[j] = Math.max(dp[j], dp[j - item[i][1]] + item[i][0]);
            }
//            System.out.println(Arrays.toString(dp));
        }

        for(int i=0; i<=dp.length; i++) {
            if(dp[i] >= M) {
                res = i;
                break;
            }
        }

        System.out.println(res);

    }
}