import java.io.*;
import java.util.*;

//BOJ_21944 문제 추천 시스템 Version 2
public class Main {
    static class PROBLEM implements Comparable<PROBLEM>{
        int num;
        int level;
        int type;

        public PROBLEM(int num, int level, int type) {
            this.num = num;
            this.level = level;
            this.type = type;
        }

        @Override
        public int compareTo(PROBLEM o) {
            if(level == o.level) {
                return Integer.compare(num, o.num);
            }
            return Integer.compare(level, o.level);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            PROBLEM other = (PROBLEM) obj;
            if (type != other.type)
                return false;
            if (level != other.level)
                return false;
            if (num != other.num)
                return false;
            return true;
        }

    }
    static int N;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        //ceiling : 보다 작은거
        //floor : 보다 큰거

        //난이도가 낮은 순 (같다면 문제번호가 낮은 순)
        TreeSet<PROBLEM> tSet = new TreeSet<>();

        List<TreeSet<PROBLEM>> list = new ArrayList<>();

        for(int i=0; i<101; i++) list.add(new TreeSet<PROBLEM>());

        HashMap<Integer, int[]> map = new HashMap<>();

        N = Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int P = Integer.parseInt(st.nextToken());
            int L =Integer.parseInt(st.nextToken());
            int T = Integer.parseInt(st.nextToken());
            tSet.add(new PROBLEM(P,L,T));
            list.get(T).add(new PROBLEM(P,L,T));
            map.put(P, new int[]{L,T});
        }


        int Q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<Q; i++) {
            st = new StringTokenizer(br.readLine());

            String command = st.nextToken();

            if(command.equals("add")) {
                int P = Integer.parseInt(st.nextToken());
                int L =Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());
                tSet.add(new PROBLEM(P,L,T));
                list.get(T).add(new PROBLEM(P,L,T));
                map.put(P, new int[]{L,T});
            }
            else if(command.equals("solved")) {
                int P = Integer.parseInt(st.nextToken());
                //무조건 추천 문제 리스트에 있다는 조건
//                tSet.removeIf(idx -> idx.num == P);   //얘냐?
                int[] node = map.get(P);
                tSet.remove(new PROBLEM(P, node[0], node[1]));
                map.remove(P);
                list.get(node[1]).remove(new PROBLEM(P, node[0], node[1])); //이게 됨?
            }
            else if(command.equals("recommend")) {
                int G = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                if(x == 1) {
                    PROBLEM problem = list.get(G).last();
                    sb.append(problem.num).append("\n");
                }
                else {
                    PROBLEM problem = list.get(G).first();
                    sb.append(problem.num).append("\n");
                }
            }
            else if(command.equals("recommend2")) {
                int x = Integer.parseInt(st.nextToken());
                if(x == 1) {
                    PROBLEM problem = tSet.last();
                    sb.append(problem.num).append("\n");
                }
                else {
                    PROBLEM problem = tSet.first();
                    sb.append(problem.num).append("\n");
                }
            }
            else {
                int x = Integer.parseInt(st.nextToken());
                int L = Integer.parseInt(st.nextToken());
                if(x == 1) {
                    PROBLEM condition = new PROBLEM(0,L, 0);
                    PROBLEM problem = tSet.ceiling(condition);
                    if(problem == null) {
                        sb.append(-1).append("\n");
                        continue;
                    }
                    sb.append(problem.num).append("\n");
                }
                else {
                    PROBLEM condition = new PROBLEM(0,L, 0);
                    PROBLEM problem = tSet.lower(condition);    //floor였음
                    if(problem == null) {
                        sb.append(-1).append("\n");
                        continue;
                    }
                    sb.append(problem.num).append("\n");
                }
            }
        }
        System.out.println(sb);
    }
}