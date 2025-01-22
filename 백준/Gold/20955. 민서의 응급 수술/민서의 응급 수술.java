import java.io.*;
import java.util.*;

// BOJ_20955
public class Main {
    static int N, M;
    static int[] parent;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   // 뉴런의 개수
        M = Integer.parseInt(st.nextToken());   // 시냅스의 개수

        /**
         * 연결되지 않은 두 뉴런을 연결하거나 이미 연결된 두 뉴런의 연결을 끊는다. -> 싸이클이 발생할 수 있다는 걸 암시...
         * 트리의 형태로 만들어야 한다. << 싸이클이 없고, 루트 노드는 1개만(나머지 노드들은 루트를 가리키고 있어야 함.)
         * **/

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) parent[i] = i;

        int redundantEdges = 0; // 추가로 제거해야 할 간선 수
        for (int i = 0; i < M; i++) {    // 시냅스로 연결된 두 뉴런의 번호 u, v
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            if (find(u) == find(v)) {
                // 이미 같은 집합에 속해 있다면 사이클이 발생한 간선
                redundantEdges++;
            } else {
                union(u, v);
            }
        }

        // 두 정점이 같은 루트를 가리키고 있는데 연결하려면 싸이클이 발생할 수 있다.

        // 연결 요소의 개수를 계산
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i <= N; i++) {
            set.add(find(i)); // find 호출로 루트를 갱신
        }

        int components = set.size(); // 연결 요소의 개수
        // 트리가 되기 위해 필요한 연결 작업의 수 = (연결 요소 - 1)
        int requiredEdges = components - 1;

        System.out.println(requiredEdges + redundantEdges);
    }

    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]); // 경로 압축
    }

    static void union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot != bRoot) {
            parent[aRoot] = bRoot; // 하나의 집합으로 병합
        }
    }
}