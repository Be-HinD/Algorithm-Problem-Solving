import java.io.*;
import java.util.*;

//BOJ_1717 집합의 표현
public class Main {
    static int N, M;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N+1];
        for(int i=0; i<=N; i++) {
            arr[i] = i;
        }


        for(int i=0; i<M; i++) {
            String[] input = br.readLine().split(" ");

            if(input[0].equals("0")) {
                //합집합
                int a = Integer.parseInt(input[1]);
                int b = Integer.parseInt(input[2]);
                union(a, b);
            }

            else {
                //isTrue?
                int a = Integer.parseInt(input[1]);
                int b = Integer.parseInt(input[2]);
                int aParent = findParent(a);
                int bParent = findParent(b);
                if(aParent == bParent) {
                    sb.append("YES").append("\n");
                    continue;
                }
                sb.append("NO").append("\n");
            }
        }
        System.out.println(sb);
    }

    private static void union(int a, int b) {
        int ap = findParent(a);
        int bp = findParent(b);
        arr[bp] = ap;
    }

    private static int findParent(int x) {
        if(x == arr[x]) return arr[x];
        else return arr[x] = findParent(arr[x]);    //부모를 찾을 때마다 최상위 부모로 항상 업데이트
    }
}