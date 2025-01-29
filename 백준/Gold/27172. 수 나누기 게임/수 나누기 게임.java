import java.io.*;
import java.util.*;

//BOJ_27172
public class Main {
    static int N;
    static boolean[] card;
    static int[] list, res;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());   // 카드 개수

        card = new boolean[1000055];
        list = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            int idx = Integer.parseInt(st.nextToken());
            card[idx] = true;
            list[i] = idx;
        }

        res = new int[1000055];
        for(int idx : list) {
            for(int i=idx*2; i<1000001; i+=idx) {
                if(card[i]) {
                    res[idx]++;
                    res[i]--;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int idx : list) {
            sb.append(res[idx]).append(" ");
        }

        System.out.println(sb);

    }
}