import java.io.*;
import java.util.*;

//BOJ_3079 입국심사
public class Main {
    static int N, M;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   //심사대 개수
        M = Integer.parseInt(st.nextToken());   //사람 수

        /**
         * (1 ≤ N ≤ 100,000, 1 ≤ M ≤ 1,000,000,000
         * 1 ≤ Tk ≤ 1,000,000,000
         * 시간제한 1초 == 100,000,000번
         * 최대 시간복잡도 : O(NlogN)
         * 각 사람들이 심사대를 모두 거치는 최소 값 탐색 == 이분탐색
         * LowerBound 사용 -> 특정 Key초에 모든 사람이 수용 가능한지 판단
         * 판단 로직 : ex) 28초 --> 7초 10초 6명 : 배열 돌면서 나머지 연산
         * **/

        arr = new int[N];
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);
        System.out.println(binarySearch());


    }


    static long binarySearch() {
        long low = 1;
        long high = Long.MAX_VALUE;
        while(low < high) {
            final long mid = low + (high - low) / 2;  //mid == 시간 초
            //mid초에 모든 사람이 수용 가능한지 판단.
            long cnt = M;
            for(int i=0; i<arr.length; i++) {
                cnt -= mid / arr[i];
                if(cnt <= 0) break;
            }

            if(cnt <= 0) {
                //현재 mid초가 수용 가능한 경우에는 더 아래 탐색
                high = mid;
            }
            else {
                //현재 mid초로 수용이 불가능할 경우 더 늘려야함.
                low = mid + 1;
            }
        }

        return high;
    }
}