import java.io.*;
import java.util.*;

//BOJ_2212
public class Main {
    static int N, K, res;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());   //센서 개수
        K = Integer.parseInt(br.readLine());   //집중국 개수

        int[] sencor = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            sencor[i] = Integer.parseInt(st.nextToken());
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(((o1, o2) -> {return o2 - o1;}));

        Arrays.sort(sencor);
        
        for(int i=N-1; i>0; i--) {
            pq.offer(sencor[i] - sencor[i-1]);
        }

        for(int i=0; i<K-1; i++) {
            pq.poll();
        }

        while(!pq.isEmpty()) {
            res += pq.poll();
        }

        System.out.println(res);

    }
}