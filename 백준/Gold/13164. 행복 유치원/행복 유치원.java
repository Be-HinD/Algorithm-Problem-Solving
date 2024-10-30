    import java.io.*;
    import java.util.*;

    //BOJ_13164
    public class Main {
        static int N, K, res;
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            if(N == 1) {
                System.out.println(0);
                return;
            }

            st = new StringTokenizer(br.readLine());
            
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            int prev = Integer.parseInt(st.nextToken());
            for(int i=0; i<N-1; i++) {
                int idx = Integer.parseInt(st.nextToken());
                pq.offer(idx-prev);
                prev = idx;
            }

            for(int i=0; i<N-K; i++) {
                res += pq.poll();
            }

            System.out.println(res);

        }
    }