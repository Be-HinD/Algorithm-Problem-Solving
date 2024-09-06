import java.io.*;
import java.util.*;

//BOJ_1374 강의실
public class Main {
    static int N, res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());    //강의의 개수 (N <= 100,000)

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            pq.offer(new int[]{start, end});
        }

        PriorityQueue<Integer> endPQ = new PriorityQueue<>();

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            
            if(endPQ.isEmpty()) {
                endPQ.offer(cur[1]);
            }
            else if(endPQ.peek() <= cur[0]) {
                endPQ.poll();
                endPQ.offer(cur[1]);
            }
            else {
                endPQ.offer(cur[1]);
            }

            res = Math.max(res, endPQ.size());

        }

        System.out.println(res);

    }
}