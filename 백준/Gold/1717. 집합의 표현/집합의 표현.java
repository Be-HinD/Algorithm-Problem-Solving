import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ_17396
public class Main {
    static int N, M;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N+1];
        for(int i=0; i<=N; i++) arr[i] = i;

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(idx == 0) {
                union(a,b);
            }
            else {
                int aRoot = find(a);
                int bRoot = find(b);
                if(aRoot == bRoot) sb.append("YES").append("\n");
                else sb.append("NO").append("\n");
            }
        }

        System.out.println(sb);

    }

    private static int find(int x) {
        if(x == arr[x]) return arr[x]; //부모와 현재가 같을 때
        return arr[x] = find(arr[x]);    //부모를 찾을 때마다 최상위 부모로 항상 업데이트
    }

    private static void union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        arr[bRoot] = aRoot ;
    }
}