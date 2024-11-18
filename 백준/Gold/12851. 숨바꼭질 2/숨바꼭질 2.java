import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, K, ans, cnt;
    static int[] v = new int[100055]; //방문 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        ans = Integer.MAX_VALUE;
        if(N == K) { //수빈이와 동생이 같은 위치에 있을 경우 전처리
            System.out.println(0);
            System.out.println(1);
            return;
        }
        bfs();

        System.out.println(v[K]);
        System.out.println(cnt);
    }

    private static void bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{N, 0});

        while(!queue.isEmpty()) {
            int[] idx = queue.poll();
            if(idx[1] >= ans) continue; //백트래킹 : 구해진 시간을 넘어설 경우

            int point = idx[0] * 2; //순간이동
            if(point>100000);
            else {
                if (v[point] != 0 && v[point] < (idx[1] + 1)) ; //방문한 적이 있는데 현재 시간이 더 크다면 비교할 필요 없음.
                else {
                    if (point == K) { //동생위치인지 비교
                        if (v[point] == ans) cnt++;
                        else cnt = 1;
                        ans = Math.min(ans, idx[1] + 1);
                    }
                    v[point] = idx[1] + 1;
                    queue.offer(new int[]{point, idx[1] + 1});
                }
            }

            point = idx[0] - 1; //-1
            if(point<0 || point>100000);
            else {
                if (v[point] != 0 && v[point] < (idx[1] + 1)) ;
                else {
                    if (point == K) {
                        if (v[point] == ans) cnt++;
                        else cnt = 1;
                        ans = Math.min(ans, idx[1] + 1);
                    }
                    v[point] = idx[1] + 1;
                    queue.offer(new int[]{point, idx[1] + 1});
                }
            }

            point = idx[0] + 1; //+1
            if(point>100000);
            else {
                if (v[point] != 0 && v[point] < (idx[1] + 1)) ;
                else {
                    if (point == K) {
                        if (v[point] == ans) cnt++;
                        else cnt = 1;
                        ans = Math.min(ans, idx[1] + 1);
                    }
                    v[point] = idx[1] + 1;
                    queue.offer(new int[]{point, idx[1] + 1});
                }
            }
        }
    }
}