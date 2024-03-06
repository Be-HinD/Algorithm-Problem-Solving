import java.io.*;
import java.util.StringTokenizer;
//BOJ_11501 주식
public class Main {
    static int T, N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        for(int tc=0; tc<T; tc++) {
            N = Integer.parseInt(br.readLine());
            int[] arr = new int[N];
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            int maxCoin = arr[N-1];
            long res = 0;
            for(int i=N-2; i>=0; i--) {
                if(arr[i] < maxCoin) {
                    res += maxCoin - arr[i];
                }
                maxCoin = Math.max(maxCoin, arr[i]);
            }
            sb.append(res).append("\n");
        }
        System.out.println(sb);
    }
}