import java.io.*;
import java.util.*;

//BOJ_1715 카드 정렬하기
public class Main {
    static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }

        });

        for(int i=0; i<N; i++) {
            pq.offer(Integer.parseInt(br.readLine()));
        }

        int res = 0;
        while(pq.size() > 1) {
            int cur = pq.poll();
            int cur2 = pq.poll();

            int sum = cur + cur2;
            res += sum;

            pq.offer(sum);
        }

        System.out.println(res);

    }
}