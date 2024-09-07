import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//BOJ_19598 최소 회의실 개수
public class Main {
    static int N, res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());    //강의의 개수 (N <= 100,000)

        PriorityQueue<int[]> schedule = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            schedule.offer(new int[]{start, end});
        }

        PriorityQueue<Integer> room = new PriorityQueue<>();

        while (!schedule.isEmpty()) {
            int[] cur = schedule.poll();

            if(room.isEmpty()) {
                room.offer(cur[1]);
            }
            else {
                if(room.peek() > cur[0]) {
                    room.offer(cur[1]);
                }
                else {
                    room.poll();
                    room.offer(cur[1]);
                }
            }

            res = Math.max(res, room.size());
        }

        System.out.println(res);

    }
}