import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ_21758
public class Main {
    static int N, res;
    static int[] arr, sum;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N+2];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        //누적합 계산
        sum = new int[N+1];
        sum[1] = arr[1];
        for(int i=2; i<=N; i++) {
            sum[i] = arr[i] + sum[i-1];
        }

        for(int i=2; i<N; i++) {
            eat(1, i, N);
        }

        System.out.println(res);

    }

    private static void eat(int i, int j, int k) {

        //i가 벌통
        int bul1 = sum[j-1] - sum[i-1];
        int bul2 = sum[k-1] - sum[i-1] - arr[j];
        int resI = bul1 + bul2;

        //j가 벌통
        bul1 = sum[j] - sum[i];
        bul2 = sum[k-1] - sum[j-1];
        int resJ = bul1 + bul2;

        //k가 벌통
        bul1 = sum[k] - sum[i] - arr[j];
        bul2 = sum[k] - sum[j];
        int resK = bul1 + bul2;

        res = Math.max(res, resI);
        res = Math.max(res, resJ);
        res = Math.max(res, resK);

    }
}