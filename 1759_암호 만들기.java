import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static String[] arr;
    static ArrayList<String> list;
    static String[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new String[N];
        list = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
            list.add(st.nextToken());

        }
        Collections.sort(list);
        arr = new String[] {"a", "e", "i", "o", "u"};
        recursion(0, 0);
    }
    private static void recursion(int cnt, int start) {
        if(cnt == N) {
            int sum = 0;
            String output = "";
            //조합
            for(int i=0; i<visited.length; i++) {
                output = output + visited[i];
            }
            for(int i=0; i<arr.length; i++) {
                if(output.contains(arr[i])) {
                    sum++;
                }
            }
            if(sum >= 1) {
                if ((N - sum) < 2) {
                    return;
                } else {
                    System.out.println(output);
                }
            }
        }
        else {
            for(int i = start; i<M; i++) {
                visited[cnt] = list.get(i);
                recursion(cnt + 1, i + 1);

            }
        }
    }
}
