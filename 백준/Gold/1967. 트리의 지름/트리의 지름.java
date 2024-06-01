import java.io.*;
import java.util.*;

//BOJ_1967 트리의 지름
public class Main {
    static class Node {
        int v;
        int w;
        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
    static List<List<Node>> list;
    static Node[] parentArr; //부모 노드의 정보를 기록하는 배열
    static int N, res;
    static boolean[] v;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        //어케풀어야됌?
        //dfs?
        /**
         * 제일 긴 가중치로 연결하면되나?
         * 트리에서 어떤 두 노드를 선택해서 양쪽으로 쫙 당길 때, 가장 길게 늘어나는 경우
         * 1. 각 정점에서 순회 시작(dfs)
         * 2. 한쪽 깊이로 탐색 (visited 체크)
         * 3. 더 이상 탐색할 수 없는 곳까지 탐색완료한 경우 res 최대값 갱신?
         * 노드 개수 10,000 간선 10,000 - 100,000,000 (1억번?)
         * 일단 내 풀이로 풀려면 부모, 자식 둘 다 탐색해야함.
         *
         * **/

        N = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        for(int i=0; i<=N; i++) list.add(new ArrayList<>());
        parentArr = new Node[N+1];

        for(int i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int current = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            Node parentNode = new Node(parent, weight);
            Node childNode = new Node(current, weight);
            parentArr[current] = parentNode;
            list.get(parent).add(childNode);    //단방향
        }


        for(int i=1; i<=N; i++) {
            v = new boolean[N+1];
            dfs(i, 0);
        }

        System.out.println(res);
    }

    static void dfs(int node, int sum) {

        //기저조건 : leaf Node 방문 시? 다음 탐색 가능한 곳이 없을 경우 최대값 비교
        v[node] = true;
        // 자식 노드 탐색
        for(Node n : list.get(node)) {
            if(!v[n.v]) {
                dfs(n.v, sum + n.w);
            }
        }

        // 부모 노드 탐색
        if(parentArr[node] != null && !v[parentArr[node].v]) {
            dfs(parentArr[node].v, sum + parentArr[node].w);
        }

        v[node] = false;
        //탐색 종료 시
        res = Math.max(res, sum);
    }
}