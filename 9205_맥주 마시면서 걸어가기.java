import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int T, N, sx, sy, ex, ey;
    static List<int[]> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) { //TestCase
            N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            sx = Integer.parseInt(st.nextToken());
            sy = Integer.parseInt(st.nextToken());

            list = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                list.add(new int[]{x, y}); //편의점들에 대한 리스트
            }
            st = new StringTokenizer(br.readLine());
            ex = Integer.parseInt(st.nextToken());
            ey = Integer.parseInt(st.nextToken());

            if (bfs()) {
                sb.append("happy" + "\n");
            } else {
                sb.append("sad" + "\n");
            }
        }
        System.out.println(sb);
    }

    private static boolean bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{sx, sy});
        boolean[] v = new boolean[N];

        while (!queue.isEmpty()) {
            int[] idx = queue.poll();
            int x = idx[0];
            int y = idx[1];
            int dis = Math.abs(x - ex) + Math.abs(y - ey); //도착지점과의 거리차
            if (dis <= 1000) return true; //도착할 수 있는 경우

            for (int i = 0; i < list.size(); i++) { //방문하지 않은 편의점에 대해서
                idx = list.get(i);
                if (v[i]) continue;
                int nx = idx[0];
                int ny = idx[1];
                if ((Math.abs(x - nx) + Math.abs(y - ny)) < 1001) { //방문가능한 거리일 경우
                    v[i] = true;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
        return false;
    }
}
