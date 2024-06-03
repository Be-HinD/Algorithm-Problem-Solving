import java.io.*;
import java.util.*;

//BOJ_2751 수 정렬하기 2
public class Main {
    static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for(int i=0; i<N; i++) {
            pq.offer(Integer.parseInt(br.readLine()));
        }
        
        while(!pq.isEmpty()) {
            sb.append(pq.poll()).append("\n");
        }

        System.out.println(sb);

    }
}