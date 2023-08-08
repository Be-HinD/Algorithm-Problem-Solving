import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int tc, N, M;
    static int[] arr;
    static int ans;
    static int sum;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        tc = Integer.parseInt(st.nextToken());
        for (int i = 0; i < tc; i++) {
            ans = 0; //값 초기화
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            arr = new int[N]; //봉지무게 배열
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) { //배열에 값 대입
                arr[j] = Integer.parseInt(st.nextToken());
            }

            for(int k=0; k<N-1; k++) { //반복문 조합 사용
                for(int l = k+1; l <N; l++) {
                    sum = arr[k] + arr[l];
                    if(sum <= M) ans = Math.max(ans, sum); //봉지 2개의 합이 제한M을 넘지 않을 때 기존값과 비교 후 갱신
                }
            }

            if(ans == 0) { //만약 봉지 조합이 하나라도 나오지 않았을 경우 -1
                sb.append("#" + (i+1) + " " + -1 + "\n");
                continue;
            }
            sb.append("#" + (i+1) + " " + ans + "\n");

        }
        System.out.println(sb);
    }
}
