import java.io.*;
import java.util.*;

//BOJ_13904 과제
public class Main {
    static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());   //보석 개수

        /**
         * 현재 날짜를 카운팅하면서
         * PQ에는 점수가 높은 순으로 삽입.
         *
         * **/

        // 마감일이 빠른 순으로
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
            int d = Integer.parseInt(st.nextToken());   //마감일까지 남은 일수
            int w = Integer.parseInt(st.nextToken());   //과제 점수
            pq.offer(new int[]{d,w});
        }

        // 점수 오름차순 정렬
        PriorityQueue<int[]> res = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            if(res.size() == cur[0]) {
                if(res.peek()[1] < cur[1]) {
                    res.poll();
                    res.offer(cur);
                }
            }
            else res.offer(cur);
        }

        int ans = 0;
        while(!res.isEmpty()) {
            ans += res.poll()[1];
        }

        System.out.println(ans);

    }
}