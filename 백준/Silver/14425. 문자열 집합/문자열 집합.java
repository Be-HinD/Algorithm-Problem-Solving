import java.io.*;
import java.util.*;

//BOJ_14425 문자열 집합
public class Main {
    static int N, M;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Set<String> set = new HashSet<>();
        for(int i=0; i<N; i++) {
            String input = br.readLine();
            set.add(input);
        }

        int res = 0;
        for(int i=0; i<M; i++) {
            String input = br.readLine();
            if(set.contains(input)) res++;
        }

        System.out.println(res);

    }
}