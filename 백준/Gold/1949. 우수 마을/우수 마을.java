import java.io.*;
import java.util.*;

//BOJ_1949
public class Main {
    static int n;
    static int[] peopleArr;
    static int[][] dp;
    static boolean[] v;
    static List<List<Integer>> list;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        /**
         * 마을이 트리 구조로 이루어져 있음.
         * 인접한 두 마을이 선택되는 경우는 없어야 함.
         * 현재 정점을 기준으로 현재 정점을 일반 마을로 할지, 우수 마을로 할지 2분법
         * 현재 정점을 기준으로 서브 트리 인구 합의 최대값을 탐색
         * 서브 트리의 최적해를 부모 노드에서 활용
         * 인접 리스트 기준 O(N)
         * **/

        n = Integer.parseInt(br.readLine());

        peopleArr = new int[n+1];

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
        }

        dp = new int[n+1][2];
        dp[1][0] = 0;
        dp[1][1] = peopleArr[1];

        v = new boolean[n+1];

        dfs(1);

        System.out.println(Math.max(dp[1][0], dp[1][1]));
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