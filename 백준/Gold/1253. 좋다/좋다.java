import java.io.*;
import java.util.*;

//BOJ_1253 좋다
public class Main {
    static int N, res;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) arr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr);

        for(int i=0; i<N; i++) {
            if(BinarySearch(i)) {
                res++;
            }
        }

        System.out.println(res);
    }

    private static boolean BinarySearch(int key) {
        int startPointer = 0;
        int endPointer = arr.length-1;

        if(key == 0) startPointer++;
        else if(key == arr.length-1) endPointer--;

        while(startPointer < endPointer) {
            final long mid = arr[startPointer] + arr[endPointer];

            if(mid > arr[key]) {
                endPointer--;
            }
            else if(mid < arr[key]) {
                startPointer++;
            }
            else {
                return true;
            }

            if(startPointer == key) startPointer++;
            if(endPointer == key) endPointer--;
        }

        return false;
    }
}