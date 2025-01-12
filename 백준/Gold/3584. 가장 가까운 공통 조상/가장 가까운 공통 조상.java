import java.io.*;
import java.util.*;

//3584
public class Main {
    static int T, N, res;
    static List<List<Integer>> list;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        /**
         * 접근법
         *
         * **/

        T = Integer.parseInt(br.readLine());
        while(T-- > 0) {
            N = Integer.parseInt(br.readLine());

            list = new ArrayList<>();
            for(int i=0; i<=N; i++) list.add(new ArrayList<>());

            for(int i=0; i<N-1; i++) {
                st = new StringTokenizer(br.readLine());

                int parent = Integer.parseInt(st.nextToken());
                int child = Integer.parseInt(st.nextToken());
                list.get(child).add(parent);
            }

            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            Set<Integer> aParent = new HashSet<>();
            aParent.add(a);
            int cur = a;
            while(!list.get(cur).isEmpty()) {
                cur = list.get(cur).get(0);
                aParent.add(cur);
            }

            cur = b;
            while(!list.get(cur).isEmpty()) {
                cur = list.get(cur).get(0);
                if(aParent.contains(cur)) {
                    res = cur;
                    break;
                }
                aParent.add(cur);
            }

            sb.append(res).append("\n");
        }

        System.out.println(sb);

    }
}