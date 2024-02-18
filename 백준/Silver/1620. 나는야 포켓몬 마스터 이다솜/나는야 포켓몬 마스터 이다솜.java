import java.io.*;
import java.util.*;

//BOJ_1620 나는야 포켓몬 마스터 이다솜
public class Main {
    static int N, M;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Map<String, Integer> strList = new HashMap<>();
        Map<Integer, String> numList = new HashMap<>();

        for(int i=1; i<=N; i++) {
            String input = br.readLine();
            strList.put(input, i);
            numList.put(i, input);
        }

        for(int i=0; i<M; i++) {
            String input = br.readLine();
            if(isNum(input)) {
                sb.append(numList.get(Integer.parseInt(input))).append("\n");
            }
            else {
                sb.append(strList.get(input)).append("\n");
            }
        }

        System.out.println(sb);

    }

    private static boolean isNum(String idx) {
        return idx.matches("[+-]?\\d*(\\.\\d+)?");
    }
}