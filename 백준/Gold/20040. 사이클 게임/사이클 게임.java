import java.io.*;
import java.util.*;

//BOJ_20040
class Main {
    static int N, M, cnt;
    static int[] parent;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        /**
         * 접근법
         *
         * **/

        N = Integer.parseInt(st.nextToken()); //점의 개수 <= 500,000
        M = Integer.parseInt(st.nextToken()); //턴 <= 1,000,000

        parent = new int[N];
        for(int i=1; i<N; i++) parent[i] = i;

        for(int i=0; i<M; i++) { //1,000,000
            st = new StringTokenizer(br.readLine());
            //i번째 차례에 선택한 두 점
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            if(!union(s,e)) {
                System.out.println(i+1);
                return;
            }

        }
        System.out.println(0);

    }
    static boolean union(int a, int b) {
        int aP = find(a);
        int bP = find(b);
        if(aP == bP) return false;
        parent[bP] = aP;
        return true;
    }

    static int find(int x) {
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
}