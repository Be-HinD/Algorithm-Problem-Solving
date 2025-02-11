import java.io.*;
import java.util.*;

//BOJ_13975
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int i = 0; i < T; i++) {
            int num = Integer.parseInt(br.readLine());

            Queue<Long> pq = new PriorityQueue<>();
            String[] data = br.readLine().split(" ");

            for(int j = 0; j < num; j++) {
                pq.offer(Long.parseLong(data[j]));
            }

            long total = 0;
            while(pq.size() > 1) {
                long num1 = pq.poll();
                long num2 = pq.poll();
                long sum = num1 + num2;

                total += sum;
                pq.offer(sum);
            }
            System.out.println(total);
        }

    }
}