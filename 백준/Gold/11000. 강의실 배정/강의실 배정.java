import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//BOJ_11000 강의실 배정
public class Main {
    static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N][2];
        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        int res = 0;
        //1 2 3
        //3 4 5
        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        //시작시간을 기준으로 오름차순 정렬
        //13,35 or 24

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(arr[0][1]);
        for(int i=1; i<N; i++) {
            int startTime = arr[i][0];
            int endTime = arr[i][1];

            if(pq.peek() <= startTime) {
                pq.poll();
            }
            pq.offer(endTime);
        }

        System.out.println(pq.size());
    }
}