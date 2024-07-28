import java.io.*;
import java.util.*;

//BOJ_2776 암기왕
public class Main {
    static int T, N, M, res;
    static int[] arr1;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();


        T = Integer.parseInt(br.readLine());   //Test Case

        for(int tc=0; tc<T; tc++) {
            N = Integer.parseInt(br.readLine());
            arr1 = new int[N];
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++) {
                arr1[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(arr1);

            M = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());
            for(int i=0; i<M; i++) {
                int idx = Integer.parseInt(st.nextToken());
                if(binarySearch(idx)) {
                    sb.append(1).append("\n");
                }
                else sb.append(0).append("\n");
            }

        }

        System.out.println(sb);
    }

    static boolean binarySearch(int idx) {
        int low = 0;
        int high = arr1.length-1;

        while(low <= high) {
            final int mid = low + (high-low) / 2;
            if(arr1[mid] < idx) {
                low = mid + 1;
            }
            else if(arr1[mid] > idx) {
                high = mid - 1;
            }
            else {
                return true;
            }
        }
        return false;
    }
}