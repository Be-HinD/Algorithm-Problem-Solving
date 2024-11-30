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
         * 키워드
         * 임의의 두 도시 사이에 길이 있을 수도, 없을 수도 있다.
         * 가능한지 여부를 판별
         * 같은 도시를 여러 번 방문하는 것도 가능
         * 접근법
         * 유니온파인드가 트리 구조에서 노드의 최종 루트 부모를 판단하는것.
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
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a,b);
        }

        for(int i=1; i<=N; i++) find(i);

        List<List<Integer>> list = new ArrayList<>();
        for(int i=0; i<=N; i++) list.add(new ArrayList<>());

        for(int i=1; i<=N; i++) list.get(parent[i]).add(i);

        for(int i=1; i<=N; i++) {
            if(list.get(i).isEmpty()) continue;
            int min = Integer.MAX_VALUE;
            for(int idx : list.get(i)) {
                min = Math.min(min, cost[idx]);
            }
            if(min != Integer.MAX_VALUE) res += min;
        }

        System.out.println(res <= K ? res : "Oh no");

    }

    static void union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        parent[bRoot] = aRoot;
    }

    static int find(int x) {
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
}