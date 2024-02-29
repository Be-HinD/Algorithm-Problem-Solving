import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

//BOJ_2343 기타 레슨
public class Main {
    static int N, M, res;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   //강의 개수
        M = Integer.parseInt(st.nextToken());   //블루레이 개수

        //N개의 강의를 순차적으로 M개로 잘라야함
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        BinarySearch();
            //1 2 3 4 5 6 1200000

        System.out.println(res);
    }

    private static void BinarySearch() {
        int low = 0;
        int high = Integer.MAX_VALUE;

        while(low < high) {
            final int mid = (low + high) / 2;   //최소 크기

            int sum = 0;
            int cnt = 0;
            boolean flag = true;
            for(int i=0; i<N; i++) {
                sum += arr[i];
                if(arr[i] > mid) {
                    flag = false;
                    break;
                }
                if(sum > mid) {
                    sum = arr[i];
                    cnt++;
                    if(cnt >= M) {
                        flag = false;
                        break;
                    }
                }
            }

            if(flag) {
                res = mid;
                high = mid;
            }
            else {
                low = mid + 1;
            }
        }
    }
}