import java.io.*;
import java.util.*;

//BOJ_2230
public class Main {
    static int N, M, res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        /**
         * 키워드
         * 수열에서 두 수를 골랐을 때(같은 수일 수도 있다),
         * 차이가 M 이상이면서 제일 작은 경우
         * 접근법
         * N <= 100,000 -> O(NlogN)까지 가능
         * 음수는 나오지않음. -> 나옴 조건에 절댓값
         * 2개의 중복조합
         * 정렬 -> 투포인터(l,r)
         * **/

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken()); //목표값

        int[] arr = new int[N];
        for(int i=0; i<N; i++) arr[i] = Integer.parseInt(br.readLine());

        Arrays.sort(arr);

        //투포인터로 2개의 모든 중복조합을 비교?

        res = 2000000000;

        for(int i=0; i<N; i++) {
            int ans = lower(i, arr);
            int list = Math.abs(arr[ans] - arr[i]);
            if(list >= M) {
                res = Math.min(res, list);
            }
        }

        System.out.println(res);
    }

    static int lower(int idx, int[] arr) {
        int low = idx;
        int high = N-1;

        while(low < high) {
            final int mid = low + (high-low) / 2;
            int sum = Math.abs(arr[idx]-arr[mid]);
            if(sum >= M) {
                high = mid;
            }
            else {
                low = mid + 1;
            }
        }

        return high;
    }
}