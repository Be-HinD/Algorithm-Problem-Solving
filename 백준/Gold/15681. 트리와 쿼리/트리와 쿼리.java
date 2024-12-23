import java.io.*;
import java.util.*;

//BOJ_15681
public class Main {
    static class Tree {
        int v;
        Tree parent;
        List<Tree> children;
        public Tree(int value) {
            this.v = value;
            this.parent = null;
            this.children = new ArrayList<>();
        }

        public void addChild(Tree child) {
            this.children.add(child);
        }
    }
    static int N, R, Q;
    static List<List<Integer>> list;
    static int[] subTreeCnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   //정점의 수
        R = Integer.parseInt(st.nextToken());   //루트의 번호
        Q = Integer.parseInt(st.nextToken());   //쿼리의 수

        list = new ArrayList<>();
        for(int i=0; i<=N; i++) list.add(new ArrayList<>());
        for(int i=1; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            list.get(s).add(e);
            list.get(e).add(s);
        }

        subTreeCnt = new int[N+1];
        Tree root = new Tree(R);
        makeTree(root, null);
        getSubTreeCnt(root);

        StringBuilder sb = new StringBuilder();

        for(int i=0; i<Q; i++) {
            int u = Integer.parseInt(br.readLine());
            sb.append(subTreeCnt[u]).append("\n");
        }

        System.out.println(sb);

    }

    static void makeTree(Tree currentNode, Tree parent) {
        currentNode.parent = parent;

        for (int idx : list.get(currentNode.v)) {
            if (parent == null || idx != parent.v) {
                Tree childNode = new Tree(idx);
                currentNode.addChild(childNode);
                makeTree(childNode, currentNode);
            }
        }
    }

    static void getSubTreeCnt(Tree currentNode) {
        subTreeCnt[currentNode.v] = 1;
        for(Tree idx : currentNode.children) {
            getSubTreeCnt(idx);
            subTreeCnt[currentNode.v] += subTreeCnt[idx.v];
        }
    }
}