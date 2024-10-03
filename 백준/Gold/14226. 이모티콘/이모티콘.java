import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

//BOJ_14226 이모티콘
public class Main {
    static int N, res;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        /**
         * 최악경우 == 999번 복사 후 붙여넣기
         * **/


        N = Integer.parseInt(br.readLine());
        res = 1000;
        bfs();

        System.out.println(res);

    }

    private static void bfs() {
        PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);

        q.offer(new int[]{1, 0, 0});    // { 현재 화면, 클립보드, 시간 }
        boolean[][] v = new boolean[2000][2000];

        while(!q.isEmpty()) {
            int[] cur = q.poll();

            if(cur[0] == N) {
                res = cur[2];
                return;
            }


            q.offer(new int[]{cur[0], cur[0], cur[2]+1});   //복사

            if(cur[0] > 0 && !v[cur[0]-1][cur[1]]) {    //삭제
                v[cur[0]-1][cur[1]] = true;
                q.offer(new int[]{cur[0] - 1, cur[1], cur[2] + 1});
            }
            if(cur[0]+cur[1] <= N && cur[1] > 0 && !v[cur[0]+cur[1]][cur[1]]) {    //붙여넣기
                v[cur[0]+cur[1]][cur[1]] = true;
                q.offer(new int[]{cur[0]+cur[1], cur[1], cur[2]+1});
            }
        }
    }
}