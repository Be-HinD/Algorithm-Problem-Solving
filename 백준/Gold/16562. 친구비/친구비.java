import java.io.*;
import java.util.*;

//BOJ_16562
class Main {
    static int N, M, K, res;
    static int[] parent, cost;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        /**
         * 접근법
         * 유니온파인드 문제
         * **/

        N = Integer.parseInt(st.nextToken());   //학생 수
        M = Integer.parseInt(st.nextToken());   //친구관계 수 (간선)
        K = Integer.parseInt(st.nextToken());   //가지고 있는 돈

        cost = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) cost[i] = Integer.parseInt(st.nextToken());

        parent = new int[N+1];
        for(int i=0; i<=N; i++) parent[i] = i;

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            union(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        }

        int[] price = new int[N+1];
        Arrays.fill(price, Integer.MAX_VALUE);
        for(int i=1; i<=N; i++) {
            price[find(i)] = Math.min(price[find(i)], cost[i]); //i의 부모
        }

        for(int i=1; i<=N; i++) res += price[i] != Integer.MAX_VALUE ? price[i] : 0;

        System.out.println(res <= K ? res : "Oh no");

    }
    static void union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        parent[bRoot] = aRoot;
    }
    static int find(int x) {
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]); //좌표압축 필요없는 문제
    }
}