import java.io.*;
import java.util.*;

//BOJ_1929 소수 구하기
public class Main {
    static int N, M;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        boolean[] v = new boolean[M+1];
        v[0] = v[1] = true;

        for(int i=2; i<=Math.sqrt(v.length); i++) {
            if(v[i]) continue;
            for(int j= i*i; j < v.length; j+=i) {
                v[j] = true;
            }
        }

        for(int i=N; i<=M; i++) {
            if(!v[i]) sb.append(i).append("\n");
        }

        System.out.println(sb);
    }
}