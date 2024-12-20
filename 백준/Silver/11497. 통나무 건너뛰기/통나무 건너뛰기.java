import java.io.*;
import java.util.*;

//BOJ_11497
class Main {
    static int T, N, res;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        /**
         * 배열로 높이값이 들어옴
         * 원형큐 느낌으로 풀이해야함
         * 중간이 제일 큰 값, 좌우로 한개씩 줄여나가기
         * **/

        T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N];

            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(arr);

            res = 0;

            PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);

            int idx = N-1;
            while(idx-2 >= 0) {
                pq.offer(arr[idx] - arr[idx-2]);
                idx -= 2;
            }
            pq.offer(arr[N-1] - arr[N-2]);
            idx = N-2;
            while(idx-2 >= 0) {
                pq.offer(arr[idx] - arr[idx-2]);
                idx -= 2;
            }

            pq.offer(Math.abs(arr[0] - arr[1]));
            sb.append(pq.poll()).append("\n");

        }

        System.out.println(sb);
    }
}