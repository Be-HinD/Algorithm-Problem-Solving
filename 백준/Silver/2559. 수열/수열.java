import java.io.*;
import java.util.StringTokenizer;

//BOJ_2559 수열
public class Main {
    static int N, M;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   //수열의 개수
        M = Integer.parseInt(st.nextToken());   //타겟 넘버

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        int totalValue = 0;
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            totalValue += arr[i];
        }

        int res = Integer.MIN_VALUE;
        if(N==M) {
            System.out.println(totalValue);
            return;
        }
        for(int i=0; i<=N-M; i++) {
            int sum = 0;
            for(int j=0; j<M; j++) {
                sum += arr[i+j];
            }
            res = Math.max(res, sum);
        }

        System.out.println(res);
    }
}