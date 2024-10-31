import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

//BOJ_2579 계단 오르기
public class Main {
    static int N, D, res;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        Map<Integer, List<int[]>> map = new HashMap<>();

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            if(e-s <= cost || e > D) continue;

            if(!map.containsKey(e)) {
                map.put(e, new ArrayList<>());
            }
            map.get(e).add(new int[]{s, cost});
        }

        int[] dp = new int[D+1];
        Arrays.fill(dp, 100000);
        dp[0] = 0;

        for(int i=1; i<dp.length; i++) {

            if(map.containsKey(i)) {
                for(int[] cur : map.get(i)) {
                    dp[i] = Math.min(dp[i], dp[cur[0]] + cur[1]);
                }
            }
            dp[i] = Math.min(dp[i-1] + 1, dp[i]);

        }

//        System.out.println(Arrays.toString(dp));

        System.out.println(dp[D]);
    }
}