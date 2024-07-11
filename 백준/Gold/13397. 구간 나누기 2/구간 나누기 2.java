import java.io.*;
import java.util.*;

//BOJ_13397 구간 나누기 2
public class Main {
    static int N, M, res;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   //입력 길이
        M = Integer.parseInt(st.nextToken());   //구간 개수

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        /**
         * M 만큼의 구간을 나누기만 하면 됨. (예로 길이가 1, 2000, 20 으로 나눠도 상관이없음. 연속되기만 하면)
         * 나눠진 구간에 대한 값은 최대값 - 최소값
         * 구간값 중 최대값을 구하고, 이 최대값이 최소가 되는 값을 탐색
         * 결과값 >> Key값
         * **/
        System.out.println(binarySearch());

    }
    static int binarySearch() {
        int low = 0;
        int high = Integer.MAX_VALUE;

        while(low < high) {
            final int mid = low + (high - low) / 2; //mid = 예측 최소 결과값

            //배열 순회로 mid가 되는지 안되는지 확인
            int max = 0;
            int min = Integer.MAX_VALUE;
            int maxDiff = 0;
            int cnt = 0;
            boolean flag = true;
            for(int i=0; i<arr.length; i++) {
                if(max < arr[i]) max = arr[i];
                if(min > arr[i]) min = arr[i];
                maxDiff = max - min;
                if(maxDiff > mid) {
                    cnt++;
                    max = maxDiff = 0;
                    min = Integer.MAX_VALUE;
                    i--;
                }
                if(cnt >= M) {
                    flag = !flag;
                    break;
                }
            }

            if(flag) {  //가능할 경우
                high = mid;
            }
            else {
                low = mid + 1;
            }

        }
        return high;
    }
}