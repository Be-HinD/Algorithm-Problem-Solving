import java.io.*;
import java.util.*;

//BOJ_1764 듣보잡
public class Main {
    static int N, M;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        HashSet<String> hear = new HashSet<>();
        for(int i=0; i<N; i++) {
            hear.add(br.readLine());
        }

        PriorityQueue<String> res = new PriorityQueue<>();

        for(int j=0; j<M; j++) {
            String input = br.readLine();
            if(hear.contains(input)) {
                res.offer(input);
            }
        }

        System.out.println(res.size());

        while(!res.isEmpty()) {
            sb.append(res.poll()).append("\n");
        }

        System.out.println(sb);
    }   
}