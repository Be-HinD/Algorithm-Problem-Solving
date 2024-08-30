import java.io.*;
import java.util.*;

//BOJ_2109 순회강연
public class Main {
    static int N, res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        PriorityQueue<int[]> list = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            list.offer(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }

        while(!list.isEmpty()) {
            pq.offer(list.peek()[0]);
            if(pq.size() > list.peek()[1]) {
                pq.poll();
            }
            list.poll();
        }

        while(!pq.isEmpty()) {
            res += pq.poll();
        }

        System.out.println(res);

    }
}