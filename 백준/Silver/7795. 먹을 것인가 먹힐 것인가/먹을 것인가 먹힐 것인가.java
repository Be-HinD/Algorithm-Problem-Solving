import java.io.*;
import java.util.*;

//BOJ_7795 먹을 것인가 먹힐 것인가
public class Main {
    static int T, N, M;
    static int[] A, B;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        for(int tc=0; tc<T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            A = new int[N];
            B = new int[M];
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++) {
                A[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for(int i=0; i<M; i++) {
                B[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(B);

            int res = 0;
            for(int i=0; i<N; i++) {
                int cnt = BinarySearch(B, A[i]);
                res += cnt;
            }
            sb.append(res).append("\n");
        }
        System.out.println(sb);
    }

    static int BinarySearch(int[] arr, int key) {

        int low = 0;
        int high = arr.length;

        while(low < high) {
            final int mid = (low + high) / 2;
            if(arr[mid] < key) {
                low = mid + 1;
            }
            else {
                high = mid;
            }
        }
        return low;
    }
}