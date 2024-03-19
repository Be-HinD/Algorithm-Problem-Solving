import java.io.*;
import java.util.*;

//BOJ_21939 문제 추천 시스템 Version 1
public class Main {
    static class PROBLEM {
        int num;
        int level;

        public PROBLEM(int num, int level) {
            this.num = num;
            this.level = level;
        }
    }
    static int N;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        N = Integer.parseInt(br.readLine());

        PriorityQueue<PROBLEM> highPQ = new PriorityQueue<>(new Comparator<PROBLEM>() {
            @Override
            public int compare(PROBLEM o1, PROBLEM o2) {
                if(o1.level == o2.level) {
                    return o2.num - o1.num;
                }
                return o2.level - o1.level;
            }
        });

        PriorityQueue<PROBLEM> lowPQ = new PriorityQueue<>(new Comparator<PROBLEM>() {
            @Override
            public int compare(PROBLEM o1, PROBLEM o2) {
                if(o1.level == o2.level) {
                    return o1.num - o2.num;
                }
                return o1.level - o2.level;
            }
        });


        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int P = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            lowPQ.add(new PROBLEM(P, L));
            highPQ.add(new PROBLEM(P, L));
            map.put(P, L);
        }

        int Q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<Q; i++) {
            st = new StringTokenizer(br.readLine());
            String query = st.nextToken();
            if(query.equals("add")) {
                int P = Integer.parseInt(st.nextToken());
                int L = Integer.parseInt(st.nextToken());
                lowPQ.add(new PROBLEM(P, L));
                highPQ.add(new PROBLEM(P, L));
                map.put(P,L);
                continue;
            }
            if(query.equals("recommend")) {
                int command = Integer.parseInt(st.nextToken());
                if(command == 1) {
                    PROBLEM prob = highPQ.peek();
                    while(!map.containsKey(prob.num) || map.get(prob.num) != prob.level) {
                        highPQ.poll();
                        prob = highPQ.peek();
                    }
                    sb.append(prob.num).append("\n");
                }
                else {
                    PROBLEM prob = lowPQ.peek();
                    while(!map.containsKey(prob.num) || map.get(prob.num) != prob.level) {
                        lowPQ.poll();
                        prob = lowPQ.peek();
                    }
                    sb.append(prob.num).append("\n");
                }
                continue;
            }
            if(query.equals("solved")) {
                int probNum = Integer.parseInt(st.nextToken());
                map.remove(probNum);
            }
        }

        System.out.println(sb);
    }
}