import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//BOJ_1263
public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {return o2[1] - o1[1];});
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            pq.offer(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }


        int prev = Integer.MAX_VALUE;
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            if(cur[1] > prev) {
                prev = prev - cur[0];
            }
            else {
                prev = cur[1] - cur[0];
            }
        }

        System.out.println(prev < 0 ? -1 : prev);
    }
}
