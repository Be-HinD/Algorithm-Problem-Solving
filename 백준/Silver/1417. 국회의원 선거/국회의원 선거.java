import java.io.*;
import java.util.*;

//BOJ_1417 국회의원 선거
public class Main {
    static int N, res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());    //후보의 수

        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        int dasom = Integer.parseInt(br.readLine());

        for(int i=1; i<N; i++) {
            pq.offer(Integer.parseInt(br.readLine()));
        }

        if(pq.isEmpty() || pq.peek() < dasom) {
            System.out.println(res);
            return;
        }

        while(pq.peek() >= dasom) {
            pq.offer(pq.poll() - 1);
            dasom++;
            res++;
        }

        System.out.println(res);

    }
}
