import java.io.*;
import java.util.Arrays;
import java.util.HashSet;

//BOJ_2295 세 수의 합
public class Main {
    static int N;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        HashSet<Integer> set = new HashSet<>();
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            set.add(arr[i]);
        }


        int[] twoSum = new int[(N * (N+1)) / 2];
        int p = 0;
        for(int i=0; i<N; i++) {
            for(int j=i; j<N; j++) {
                twoSum[p++] = arr[i] + arr[j];
            }
        }

        Arrays.sort(twoSum);

        int res = -1;
       for(int i=0; i<N; i++) {
           for(int j=0; j<N; j++) {
               if(BinarySearch(twoSum, arr[i] - arr[j])) {
                   res = Math.max(res, arr[i]);
               }
           }
       }

        System.out.println(res);

    }

    private static boolean BinarySearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;

        while(low <= high) {
            final int mid = (low + high) / 2;

            if(arr[mid] > key ) {
                high = mid - 1;
            }
            else if(arr[mid] < key){
                low = mid + 1;
            }
            else {
                return true;
            }
        }
        return false;
    }
}