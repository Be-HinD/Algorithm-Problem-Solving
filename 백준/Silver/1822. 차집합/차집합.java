import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

//BOJ_1822 차집합
public class Main {
    static int N, M;
    static int[] arrA, arrB;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   //단원 개수
        M = Integer.parseInt(st.nextToken());   //남은 시간


        arrA = new int[N];
        arrB = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arrA[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
            arrB[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arrA);
        Arrays.sort(arrB);

        StringBuilder sb = new StringBuilder();
        int res = 0;
        for(int i=0; i<N; i++) {
            int idx = BinarySearch(arrA[i]);
            if(idx == -1) {
                sb.append(arrA[i]).append(" ");
                res++;
            }
        }
        System.out.println(res);
        System.out.println(sb);

    }

    private static int BinarySearch(int key) {
        int low = 0;
        int high = arrB.length-1;

        while(low <= high) {
            final int mid = (low + high) >> 1;

            if(arrB[mid] > key) {
                high = mid -1;
            }
            else if(arrB[mid] < key) {
                low = mid + 1;
            }
            else {
                return arrB[mid];
            }
        }

        return -1;
    }
}