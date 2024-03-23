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
        for(int i=0; i<N; i++) {
            int sum = arr[i];
            if(sum == M) {
                res++;
                continue;
            }
            for(int j=i+1; j<N; j++) {
                sum += arr[j];
                if(sum == M) {
                    res++;
                    break;
                }
                else if(sum > M) break;

            }
        }

        System.out.println(res);
    }
}