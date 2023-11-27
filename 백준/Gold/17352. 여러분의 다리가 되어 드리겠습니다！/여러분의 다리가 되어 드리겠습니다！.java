import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ_17352 여러분의 다리가 되어 드리겠습니다!
public class Main {
    static int N;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        parent = new int[N + 1];

        //부모노드 초기화
        for(int i=1; i<=N; i++) {
            parent[i] = i;
        }

        for(int i=0; i<N-2; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            union(x,y);
        }

        for(int i=1; i< parent.length; i++) {
            if(parent[i] == i) { //아직 유니온되지 않은 집합을 찾을 때
                System.out.print(i + " ");
            }
        }
        
    }

    private static int find(int x) { //x노드의 부모를 찾아서...
        if(x == parent[x]) return x; //부모노드가 자기자신이라면
        return parent[x] = find(parent[x]); //현재 x노드의 부모의 부모를 찾기위해 재귀호출
    }

    private static boolean union(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);
        if(xRoot == yRoot) return false; //같은 집합에 속해있는 경우

        parent[yRoot] = xRoot;
        return true;
    }
}