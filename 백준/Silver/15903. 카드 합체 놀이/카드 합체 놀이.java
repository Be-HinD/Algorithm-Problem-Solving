import java.io.*;
import java.util.*;

//BOJ_15903 카드 합체 놀이
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        PriorityQueue<Long> pq = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            pq.offer((long) Integer.parseInt(st.nextToken()));
        }

        for(int i=0; i<M; i++) {
            long a = pq.poll();
            long b = pq.poll();
            long sum = a + b;
            pq.offer(sum);
            pq.offer(sum);
        }

        long res = 0;
        while(!pq.isEmpty()) {
            res += pq.poll();
        }

        System.out.println(res);
    }
}