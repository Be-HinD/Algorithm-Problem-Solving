import java.io.*;
import java.util.*;

//BOJ_3151 합이 0
public class Main {
    static int N;
    static long res;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);   //정렬

        //투 포인터로 두 수의 합을 구하고
        //0이 될 수 있는 마지막 수 N을 이분탐색을 통해 low+1, high-1 범위 내에서 탐색
        //중복값이 나올 수 있으니 lower ans, upper ans 차를 구하고 res+


        for(int i=0; i<N-2; i++) {
            for(int j=i+1; j<N-1; j++) {
                int sum = arr[i] + arr[j];

                int up = upper(j+1,arr.length,sum*-1);
                int low = lower(j+1,arr.length, sum*-1);

                res += up - low;
            }
        }

        System.out.println(res);
    }

    private static int lower(int low, int high, int key) {

        while(low < high) {
            final int mid = (low + high) >> 1;

            if(arr[mid] < key) {
                low = mid + 1;
            }
            else {
                high = mid;
            }
        }
        return low;
    }

    private static int upper(int low, int high, int key) {
        while(low < high) {
            final int mid = (low + high) >> 1;

            if(arr[mid] <= key) {
                low = mid + 1;
            }
            else {
                high = mid;
            }
        }
        return low;
    }
}