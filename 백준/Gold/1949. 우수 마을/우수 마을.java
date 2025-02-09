import java.io.*;
import java.util.*;

//BOJ_1949
public class Main {
    static int n, res;
    static int[] peopleArr, edgeCnt;
    static int[][] dp;
    static boolean[] v;
    static List<List<Integer>> list;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        /**
         * n == 10,000
         * dfs = N^2 -> 시간제한 2초 가능
         * **/

        n = Integer.parseInt(br.readLine());

        peopleArr = new int[n+1];
        edgeCnt = new int[n+1];

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++) peopleArr[i] = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        for(int i=0; i<=n; i++) list.add(new ArrayList<>());

        for(int i=0; i<n-1; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list.get(x).add(y);
            list.get(y).add(x);
            edgeCnt[x]++;
            edgeCnt[y]++;
        }

        int root = 0;
        for(int i=1; i<=n; i++) {
            if(edgeCnt[i] == 1) {
                root = i;
                break;
            }
        }

        dp = new int[n+1][2];
        dp[root][0] = 0;
        dp[root][1] = peopleArr[root];

        v = new boolean[n+1];

        dfs(root);

        System.out.println(Math.max(dp[root][0], dp[root][1]));
    }

    static void dfs(int node) {
        v[node] = true;

        dp[node][0] = 0;    //현재 마을이 일반 마을이라면
        dp[node][1] = peopleArr[node];  //현재 마을이 우수 마을이라면

        for(int nxt : list.get(node)) {
            if(v[nxt]) continue;
            dfs(nxt);

            dp[node][0] += Math.max(dp[nxt][0], dp[nxt][1]);    //다음 마을은 우수 마을일수도, 일반 마을일수도 있다.
            dp[node][1] += dp[nxt][0];  //현재 마을이 우수 마을이라면 다음 마을은 무조건 일반 마을이 되어야한다.
        }
    }
}
