import java.io.*;
import java.util.*;

//BOJ_1920 수 찾기
public class Main {
    static int N, M;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuffer sb = new StringBuffer();

        N = Integer.parseInt(br.readLine()); //수열의 크기

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) arr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr);

        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
            int idx = Integer.parseInt(st.nextToken());
            sb.append(BinarySearch(idx)).append("\n");
        }

        System.out.print(sb);
    }

    private static int BinarySearch(int key) {
        int low = 0;
        int high = arr.length-1;

        while(low <= high) {
            final int mid = (low+high) / 2;

            if(arr[mid] < key) {
                low = mid + 1;
            }
            else if(arr[mid] > key) {
                high = mid - 1;
            }
            else return 1;
        }
        return 0;
    }
}