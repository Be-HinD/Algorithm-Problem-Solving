import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int[] arr;
    static StringBuffer sb = new StringBuffer();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());;

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[M];

        Perm(0, 1);
        System.out.println(sb);
    }

    private static void Perm(int cnt, int start) { //start로 비내림차순 생성
        if(cnt == M) {
            //M개의 수열을 골랐을 경우
            for(int i=0; i<M; i++) {
                sb.append(arr[i] + " ");
            }
            sb.append("\n");
        } else {
            for(int i=start; i<=N; i++) { //start부터
                arr[cnt] = i;
                Perm(cnt+1, i);
            }
        }
    }
}
