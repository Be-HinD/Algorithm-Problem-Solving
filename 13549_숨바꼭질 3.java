import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, K, ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        ans = Integer.MAX_VALUE;
        if(N == K) { //수빈이와 동생이 같은 위치에 있을 경우 전처리
            System.out.println(0);
            return;
        }
        bfs();
        System.out.println(ans);
    }

    private static void bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{N, 0});
        boolean[] v = new boolean[100055]; //방문 배열
        v[N] = true;

        while(!queue.isEmpty()) {
            int[] idx = queue.poll();
            if(idx[1] >= ans) continue; //백트래킹 : 구해진 시간을 넘어설 경우

            int point = idx[0] * 2; //순간이동
            if(point>100000 || v[point]); //예외처리
            else {
                if(point == K) { //동생위치인지 비교
                    ans = Math.min(ans, idx[1]);
                }
                v[point] = true;
                queue.offer(new int[]{point, idx[1]});
            }

            point = idx[0] - 1; //-1
            if(point<0 || point>100000 || v[point]);
            else {
                if(point == K) {
                    ans = Math.min(ans, idx[1] + 1);
                }
                v[point] = true;
                queue.offer(new int[]{point, idx[1] + 1});
            }

            point = idx[0] + 1; //+1
            if(point>100000 || v[point]);
            else {
                if(point == K) {
                    ans = Math.min(ans, idx[1] + 1);
                }
                v[point] = true;
                queue.offer(new int[]{point, idx[1] + 1});
            }
        }
    }
}
