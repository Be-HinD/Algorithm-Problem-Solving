    import java.io.*;
    import java.util.*;

    //BOJ_13164
    public class Main {
        static int N, K, res;
        static int[] arr;
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st;

            N = Integer.parseInt(br.readLine());
            K = Integer.parseInt(br.readLine());

            if(N == 1) {
                System.out.println(0);
                return;
            }

            arr = new int[N];

            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(arr);

            PriorityQueue<Integer> pq = new PriorityQueue<>();
            for(int i=1; i<N; i++) {
                pq.offer(arr[i] - arr[i-1]);
            }

            for(int i=0; i<N-K; i++) {
                res += pq.poll();
            }

            System.out.println(res);

        }
    }