import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;

//BOJ_1339
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        /**
         * 우선순위
         * 1. 자릿수가 높은 순
         * 2. 같은 자릿수라면 많이 나온 알파벳
         * **/

        TreeMap<Character, Integer> cntForPos = new TreeMap<>();

        for(int i=0; i<10; i++) {
            cntForPos.put((char) ('A' + i), 0);
        }
        
        int N = Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++) {
            String input = br.readLine();
            final int FIX = 10;
            int position = 0;
            for(int j=input.length()-1; j>=0; j--) {
                char idx = input.charAt(j);
                cntForPos.put(idx, cntForPos.getOrDefault(idx, 0) + (int) Math.pow(FIX, position++));
            }
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> {return o2 - o1;});
        for(int idx : cntForPos.values()) {
            if(idx == 0) continue;
            pq.offer(idx);
        }

        int res = 0;
        int value = 9;
        while(!pq.isEmpty()) {
            res += pq.poll() * value--;
        }

        System.out.println(res);
    }
}