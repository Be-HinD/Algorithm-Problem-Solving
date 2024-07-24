import java.io.*;
import java.util.*;

//BOJ_1202 보석 도둑
public class Main {
    static int N, K;
    static long res;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   //보석 개수
        K = Integer.parseInt(st.nextToken());   //가방 개수

        /**
         * 물건들을 가치 기준 내림차순으로 pq에 삽입
         * 가방 무게 기준 오름차순
         * 무게가 낮은 가방에 최고가치 물건을 못담으면
         * **/

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0]) {
                    return o2[1] - o1[1];
                }
                return o1[0] - o2[0];
            }
        });

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            pq.offer(new int[]{m,v});
        }

        PriorityQueue<Integer> bag = new PriorityQueue<>();

        for(int i=0; i<K; i++) {
            //가방들의 무게
            bag.offer(Integer.parseInt(br.readLine()));
        }

        //현재 가방에 넣을 수 있는 최대가치 보석 탐색 로직
        PriorityQueue<int[]> temp = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];
            }
        });

        while(!bag.isEmpty()) {
            int idx = bag.poll();

            while(!pq.isEmpty()) {
                if(pq.peek()[0] <= idx) {
                    temp.offer(pq.poll());
                    continue;
                }
                break;
            }

            if(!temp.isEmpty()) {
                res += temp.poll()[1];
            }
        }

        System.out.println(res);

    }
}