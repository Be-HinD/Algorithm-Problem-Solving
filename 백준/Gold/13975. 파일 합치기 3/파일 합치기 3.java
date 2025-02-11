import java.io.*;
import java.util.*;

//BOJ_13975
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while(T-->0) {
            int k = Integer.parseInt(br.readLine());
            String[] arr = br.readLine().split(" ");

            PriorityQueue<Long> pq = new PriorityQueue<>();
            for(String idx : arr) pq.offer(Long.parseLong(idx));

            long res = 0;
            while(pq.size() != 1) {
                long a = pq.poll();
                long b = pq.poll();
                res += a+b;
                pq.offer(a+b);
            }

            sb.append(res).append("\n");

        }

        System.out.println(sb);

    }
}