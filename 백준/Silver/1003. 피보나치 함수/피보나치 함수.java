import java.io.*;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

//BOJ_1003 피보나치 함수
public class Main {
    static int T;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        int[] zeroDP = new int[41];
        zeroDP[0] = 1;
        zeroDP[1] = 0;
        zeroDP[2] = 1;
        zeroDP[3] = 1;

        int[] oneDP = new int[41];
        oneDP[1] = 1;
        oneDP[2] = 1;
        oneDP[3] = 2;

        for(int i=4; i<41; i++) {
            zeroDP[i] = zeroDP[i-1] + zeroDP[i-2];
            oneDP[i] = oneDP[i-1] + oneDP[i-2];
        }

        for(int tc=0; tc<T; tc++) {
            int N = Integer.parseInt(br.readLine());

            sb.append(zeroDP[N]).append(" ").append(oneDP[N]).append("\n");
        }
        
        System.out.println(sb);

    }
}