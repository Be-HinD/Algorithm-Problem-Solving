import java.io.*;
import java.util.*;

//BOJ_5567
public class Main {
    static int N, M, res;
    static List<List<Integer>> list = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        for(int i=0; i<=N; i++) list.add(new ArrayList<>());

        while(M-->0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list.get(x).add(y);
            list.get(y).add(x);
        }

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{1,0});
        boolean[] v = new boolean[N+1];
        v[1] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();

            if(cur[1] > 2) continue;
            res++;

            for(int idx : list.get(cur[0])) {
                if(v[idx]) continue;
                q.offer(new int[]{idx, cur[1]+1});
                v[idx] = true;
            }
        }

        System.out.println(res-1);  //자기자신 제외

    }
}