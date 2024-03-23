import java.io.*;
import java.util.*;

//BOJ_2143 두 배열의 합
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());    //타겟 넘버
        int aN = Integer.parseInt(br.readLine());    //A배열 길이

        int[] a = new int[aN];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<aN; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        int bN = Integer.parseInt(br.readLine());
        int[] b = new int[bN];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<bN; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }

        //A의 모든 구간합을 배열로 만들고 O(2N)
        //T에서 B의 구간합 sum을 뺀 x를 이진탐색 upper - lower 로 찾기 O(NlogN)
        //res += 이진탐색 결과
        //N = 1000, O(1000log1000)
        long[] secionSum = new long[500600];
        Arrays.fill(secionSum, Integer.MAX_VALUE);
        int p = 0;
        for(int i=0; i<aN; i++) {
            long sum = 0;
            for(int j=i; j<aN; j++) {
                sum += a[j];
                secionSum[p++] = sum;
            }
        }

        Arrays.sort(secionSum);

        long res = 0;
        for(int i=0; i<bN; i++) {
            long sum = 0;
            for(int j=i; j<bN; j++) {
                sum += b[j];
                res += upper(secionSum, T-sum,p) - lower(secionSum, T-sum,p);
            }
        }
        System.out.println(res);

    }

    private static int upper(long[] arr, long key,int high) {
        int low = 0;

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

    private static int lower(long[] arr, long key, int high) {
        int low = 0;

        while(low < high) {
            final int mid = (low + high) >> 1;

            if(arr[mid] >= key) {
                high = mid;
            }
            else {
                low = mid + 1;
            }

        }
        return low;
    }
}