import java.io.*;
import java.util.*;

//BOJ_1068 트리
public class Main {
    static int N, start, res;
    static List<List<Integer>> list;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        list = new ArrayList<>();
        for(int i=0; i<N; i++) list.add(new ArrayList<>());

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            int root = Integer.parseInt(st.nextToken());
            if(root == -1) {
                start = i;
                continue;
            }
            list.get(root).add(i);
        }


        int removeNode = Integer.parseInt(br.readLine());

        if(removeNode == start) {   //root 노드를 삭제할 경우
            System.out.println(res);
            return;
        }

        bfs(removeNode);

        System.out.println(res);

    }

    private static void bfs(int removeNode) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);

        while(!q.isEmpty()) {
            int cur = q.poll();

            boolean flag = false;
            for(int node : list.get(cur)) {
                if(node == removeNode) {;
                    if(list.get(cur).size() - 1 == 0) {
                        res++;
                        flag = true;
                        break;
                    }
                    continue;
                }
                q.offer(node);
                flag = true;
            }
            if(!flag) res++;
        }
    }
}