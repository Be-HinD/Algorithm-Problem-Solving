import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static int[] arr;   //선택된 값 저장
    static boolean[] v; //선택된 수 기록
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[m];
        v = new boolean[n+1];

        perm(0);
    }

    static void perm(int depth) {
        if(depth == m) {
            for(int idx : arr) {
                System.out.print(idx + " ");
            }
            return;
        }

        for(int i=1; i<=n; i++) {
            if(!v[i]) {
                v[i] = true;
                arr[depth] = i;
                perm(depth + 1);
                v[i] = false;
            }
        }
    }
}