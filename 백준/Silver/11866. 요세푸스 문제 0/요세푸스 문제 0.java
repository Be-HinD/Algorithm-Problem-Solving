import java.io.*;
import java.util.*;

public class Main {
    static int N, M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            list.add(i);
        }
        sb.append("<");

        int idx = 0;
        while (!list.isEmpty()) {
            idx = (idx + M - 1) % list.size();
            sb.append(list.remove(idx)).append(", ");
        }

        sb.setLength(sb.length() - 2); // Remove last ", "
        sb.append(">");

        System.out.println(sb);
    }
}