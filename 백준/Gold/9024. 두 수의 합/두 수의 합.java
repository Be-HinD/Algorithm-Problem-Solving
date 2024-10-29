import java.io.*;
import java.util.*;

//BOJ_9094
public class Main {
    static int T, N, K, abs, min, res;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        while(T-- != 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());   //타겟넘버

            arr = new int[N];
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(arr);

            int l = 0;
            int r = N-1;

            min = Integer.MAX_VALUE;

            while(l != r) { //절댓값 차이 탐색
                int sum = arr[l] + arr[r];
                abs = Math.abs(K - sum);
                if(sum > K) {
                    r--;
                }
                else {
                    l++;
                }

                min = Math.min(min, abs);
            }
            res = 0;

            l = 0;
            r = N-1;

            while(l != r) {
                int sum = arr[l] + arr[r];
                abs = Math.abs(K - sum);

                if(abs == min) {
                    res++;
                }

                if(sum > K) {
                    r--;
                }
                else {
                    l++;
                }

            }



            sb.append(res).append("\n");

        }

        System.out.println(sb);

    }
}