import java.io.*;
import java.util.*;

//BOJ_14003 가장 긴 증가하는 부분 수열 5
public class Main {
    static int N, point;
    static int INF = 1000000000;
    static int[] arr, lis;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuffer sb = new StringBuffer();

        N = Integer.parseInt(br.readLine()); //수열의 크기

        arr = new int[N];
        lis = new int[N+1];
//        Arrays.fill(lis, INF);

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        point = 0;
        int[] v = new int[N+1];
        for(int i=0; i<N; i++) {

            if(lis[point] < arr[i]) {
                lis[++point] = arr[i];
                v[i] = point;
            }
            else {
                int idx = lowerBound( 0, point, arr[i]);
                lis[idx] = arr[i];
                v[i] = idx;
            }

        }

        sb.append(point).append("\n");

        Stack<Integer> stack = new Stack<>();
        for(int i= arr.length-1; i>=0; i--) {
            if(point == v[i]) {
                stack.push(arr[i]);
                point--;
            }
        }

        while(!stack.isEmpty()) sb.append(stack.pop() + " ");


        System.out.println(sb);
    }

    private static int lowerBound(int low, int high, int key) {
        while(low < high) {
            int mid = (low + high) / 2;

            if(key > lis[mid]) {
                low = mid + 1;
            }
            else {
                high = mid;
            }
        }
        return high;
    }
}