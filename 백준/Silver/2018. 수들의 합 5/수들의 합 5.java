import java.io.*;
import java.util.*;

//BOJ_2018 수들의 합 5
public class Main {
    static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(br.readLine());

        int res = 0;
        for(int i=1; i<=N; i++) {
            int sum = i;
            for(int j=i+1; j<=N; j++) {
                sum += j;
                if(sum > N) break;
                else if(sum == N) res++;
            }
        }
        System.out.println(res+1);

    }
}