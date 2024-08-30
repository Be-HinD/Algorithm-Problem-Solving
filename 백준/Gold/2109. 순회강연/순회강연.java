import java.io.*;
import java.util.*;

//BOJ_2109 순회강연
public class Main {
    static int N, res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                }
                return o2[0] - o1[0];   //페이 기준 내림차순
            }
        });


        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            pq.offer(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }

        boolean[] check = new boolean[10001];

        while(!pq.isEmpty()) {
            if(!check[pq.peek()[1]]) {
                check[pq.peek()[1]] = true;
                res += pq.poll()[0];
            }
            else {
                int[] cur = pq.poll();
                for(int i=cur[1]; i>0; i--) {
                    if(!check[i]) {
                        check[i] = true;
                        res += cur[0];
                        break;
                    }
                }
            }
        }

        System.out.println(res);

    }
}