import java.io.*;
import java.util.*;

//BOJ_2075 N번째 큰 수
public class Main {
    static int N, res;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());   //행열 크기

        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                pq.offer(Integer.parseInt(st.nextToken()));
            }
        }

        for(int i=0; i<N-1; i++) {
            pq.poll();
        }

        System.out.println(pq.poll());
    }
}