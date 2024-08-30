import java.io.*;
import java.util.*;

//BOJ_2109 순회강연
public class Main {
    static int N, res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        List<int[]> list = new ArrayList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            list.add(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }

        Collections.sort(list, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        for(int i=0; i<list.size(); i++) {
            pq.offer(list.get(i)[0]);
            if(pq.size() > list.get(i)[1]) {
                pq.poll();
            }
        }

        while(!pq.isEmpty()) {
            res += pq.poll();
        }

        System.out.println(res);

    }
}