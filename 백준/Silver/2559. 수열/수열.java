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
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int res = Integer.MAX_VALUE;
        int sum = 0;
        //최초합
        for(int i=0; i<M; i++) {
            sum += arr[i];
        }

        res = sum;

        int l = 0;
        int r = M;
        while(r <= N-1) {
            sum -= arr[l++];
            sum += arr[r++];
            res = Math.max(res, sum);
        }
        System.out.println(res);
    }
}