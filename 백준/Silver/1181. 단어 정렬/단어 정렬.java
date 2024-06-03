import java.io.*;
import java.util.*;

//BOJ_1181 단어 정렬
public class Main {
    static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        PriorityQueue<String> pq = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(o1.length() == o2.length()) {
                    return o1.compareTo(o2);    //사전순
                }
                return o1.length() - o2.length();
            }
        });
        Set<String> set = new HashSet<>();

        for(int i=0; i<N; i++) {
            String idx = br.readLine();
            if(!set.contains(idx)) {
                pq.offer(idx);
                set.add(idx);
            }
        }

        while(!pq.isEmpty()) {
            sb.append(pq.poll()).append("\n");
        }

        System.out.println(sb);

    }
}