import java.io.*;
import java.util.*;

//3584
public class Main {
    static int T, N, res;
    static List<List<Integer>> list;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        /**
         * 접근법
         * 두 노드의 겹치는 부모노드 탐색
         * List로 트리 구성 + HashSet으로 공통 부모 탐색
         * 검증
         * 1. 두 노드의 공통 부모가 없을 경우 -> 트리 구조상 불가능
         * 2. 한 노드가 나머지 노드의 부모가 되는 경우 -> 자기자신을 포함하여 Set추가
         * **/

        T = Integer.parseInt(br.readLine());
        while(T-- > 0) {
            N = Integer.parseInt(br.readLine());

            list = new ArrayList<>();
            for(int i=0; i<=N; i++) list.add(new ArrayList<>());

            for(int i=0; i<N-1; i++) {
                st = new StringTokenizer(br.readLine());

                int parent = Integer.parseInt(st.nextToken());
                int child = Integer.parseInt(st.nextToken());
                list.get(child).add(parent);
            }

            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            Set<Integer> aParent = new HashSet<>();
            aParent.add(a);
            while(!list.get(a).isEmpty()) {
                a = list.get(a).get(0);
                aParent.add(a);
            }
            
            while(!list.get(b).isEmpty()) {
                b = list.get(b).get(0);
                if(aParent.contains(b)) {
                    res = b;
                    break;
                }
                aParent.add(b);
            }

            sb.append(res).append("\n");
        }

        System.out.println(sb);

    }
}