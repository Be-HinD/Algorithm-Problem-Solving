import java.io.*;
import java.util.*;

//BOJ_7785
class Main {
    static int N, res;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        N = Integer.parseInt(br.readLine());

        Set<String> people = new TreeSet<>(Collections.reverseOrder());

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            String status = st.nextToken();
            if("enter".equals(status)) {
                people.add(name);
            }
            else {
                people.remove(name);
            }
        }

        StringBuilder sb = new StringBuilder();
        for(String name : people) {
            sb.append(name).append("\n");
        }

        System.out.println(sb);

    }
}