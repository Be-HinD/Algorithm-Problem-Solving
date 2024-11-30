import java.io.*;
import java.util.*;

//BOJ_1976
class Main {
    static int N, M;
    static int[] parent;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        /**
         * 키워드
         * 임의의 두 도시 사이에 길이 있을 수도, 없을 수도 있다.
         * 가능한지 여부를 판별
         * 같은 도시를 여러 번 방문하는 것도 가능
         * 접근법
         * 유니온파인드가 트리 구조에서 노드의 최종 루트 부모를 판단하는것.
         * **/

        N = Integer.parseInt(br.readLine());    //도시 수
        M = Integer.parseInt(br.readLine());    //간선 수

        parent = new int[N];
        for(int i=0; i<parent.length; i++) parent[i] = i;

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                int isConnect = Integer.parseInt(st.nextToken());
                if(isConnect == 0) continue;
                union(i, j);
            }
        }

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[M];
        for(int i=0; i<M; i++) {
            arr[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        int idx = find(arr[0]);
        for(int i=1; i<arr.length; i++) {
            if(idx != find(arr[i])) {
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");


    }

    static void union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        parent[bRoot] = aRoot;
    }

    static int find(int x) {
        if(parent[x] == x) return parent[x];

        return parent[x] = find(parent[x]); //좌표 압축
    }
}