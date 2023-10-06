import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int[] arr;
    static StringBuilder sb = new StringBuilder(); //안쓰면 9% 시간초과
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken() );
        M = Integer.parseInt(st.nextToken() );

        arr = new int[M];
        Perm(0);
        System.out.println(sb);
    }

    private static void Perm(int cnt) {
        if(cnt == M) {
            //출력
            for(int i=0; i<arr.length; i++) {
                sb.append(arr[i] + " ");
            }
            sb.append("\n");
        } else {
            for(int i=1; i<=N; i++) { //중복순열 : visited X
                arr[cnt] = i;
                Perm(cnt + 1);
            }
        }
    }
}
