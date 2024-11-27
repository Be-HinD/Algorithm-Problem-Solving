import java.io.*;
import java.util.*;

//BOJ_19951
public class Main {
    static int N, M;
    static int[] arr, res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        /**
         * 키워드
         *
         * **/

        N = Integer.parseInt(st.nextToken()); //연병장의 크기
        M = Integer.parseInt(st.nextToken()); //조교의 수

        arr = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) arr[i] = Integer.parseInt(st.nextToken());

        int[] sum = new int[N+2];
        for(int i=1; i<=M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            sum[a] += k;
            sum[b+1] += -k;
        }

        for(int i=1; i<=N; i++) {
            sum[i] += sum[i-1];
        }

        for(int i=1; i<=N; i++) {
            arr[i] += sum[i];
        }
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=N; i++) {
            sb.append(arr[i]).append(" ");
        }

        System.out.println(sb);
    }
}