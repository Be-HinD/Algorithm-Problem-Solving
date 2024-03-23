import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

//BOJ_2003 수들의 합 2
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
        int res = 0;

        int l,r,sum;
        l = r = sum = 0;

        //같은 위치에서 시작

        while(true) {
            if(sum >= M) {
                sum -= arr[l++];
            }
            else if(r==N) break;
            else {  //sum < M
                sum += arr[r++];
            }

            if(sum == M) {
                res++;
            }
        }

        System.out.println(res);
    }
}