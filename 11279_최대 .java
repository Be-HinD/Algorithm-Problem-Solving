import java.io.*;
import java.util.*;

//BOJ_11279 최대 힙
public class Main {
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());   //연산의 개수

        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        for(int i=0; i<N; i++) {
            int idx = Integer.parseInt(br.readLine());
            if(idx == 0) {
                if(pq.size() > 0) {
                    sb.append(pq.poll()).append("\n");
                }
                else {
                    sb.append(0).append("\n");
                }
                continue;
            }
            pq.offer(idx);
        }
        System.out.println(sb);
    }
}
