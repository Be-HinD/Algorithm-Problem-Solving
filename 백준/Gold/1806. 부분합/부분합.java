import java.io.*;
import java.util.StringTokenizer;

//BOJ_1806 부분합
public class Main {
    static int N, M;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   //수열의 개수
        M = Integer.parseInt(st.nextToken());   //타겟 넘버

        int[] arr = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int res = Integer.MAX_VALUE;
        int l,r,sum;
        l = r = 1;
        sum = 0;

        //같은 위치에서 시작

        while(true) {
            if(sum >= M) {
                res = Math.min(res, r-l);
                sum -= arr[l++];
            }
            else if(r==N+1) break;
            else {  //sum < M
                sum += arr[r++];
            }
        }

        if (res == Integer.MAX_VALUE) {
            System.out.println(0);
            return;
        }
        System.out.println(res);
    }
}