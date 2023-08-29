import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main {
    static int N, ans;
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //집의 수
        ans = Integer.MAX_VALUE; //최소값 비교를 위해 초기화
        arr = new int[N][3]; //2차원 배열 생성
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<3; i++) {
            arr[0][i] = Integer.parseInt(st.nextToken()); //0번째 먼저 초기화
        }
        for(int i=1; i<N; i++) { //2번째집부터 RGB값을 입력받고 각각 RGB배열에 이전 조건에맞는 값과 비교하여 최소값을 갱신하여 넣어줌
            st = new StringTokenizer(br.readLine());
            int R = Integer.parseInt(st.nextToken());
            int G = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            arr[i][0] = Math.min(arr[i-1][1] + R, arr[i-1][2] + R);
            arr[i][1] = Math.min(arr[i-1][0] + G, arr[i-1][2] + G);
            arr[i][2] = Math.min(arr[i-1][0] + B, arr[i-1][1] + B);
        }
        //최종값 3개 중 최소값을 비교 후 결과 출력
        ans = arr[N-1][0] > arr[N-1][1] ? arr[N-1][1] > arr[N-1][2] ? arr[N-1][2] : arr[N-1][1] : arr[N-1][0] > arr[N-1][2] ? arr[N-1][2] : arr[N-1][0];
        System.out.println(ans);
    }
}
