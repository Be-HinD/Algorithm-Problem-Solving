import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, ans;
    static int[] map;
    static boolean[] v; //방문배열
    static HashMap<Integer, Integer> hash; //사다리정보 해시맵
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[101]; //맵 초기화
        hash = new HashMap<>(); //해시 초기화

        for(int i=0; i<(N+M); i++) { //사다리 및 뱀 정보 입력
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[x] = 1; //입력값 변경
            hash.put(x, y); //해시 추가
        }
        ans = Integer.MAX_VALUE; //최소값 탐색을 위한 초기화
        v = new boolean[101]; //방문배열 초기화
        v[1] = true; //시작 지점 방문
        bfs();
        System.out.println(ans);
    }
    private static void bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{1, 0}); //시작지점, count

        while (!queue.isEmpty()) {
            int[] idx = queue.poll();
            for (int i = 1; i < 7; i++) {
                if ((idx[1] + 1) >= ans) break; //현재 결과값보다 크면 탈출
                int id = idx[0] + i; //id = 다음 좌표값

                if (v[id]) continue; //다음 좌표가 방문했던 지점일 경우

                if (id > 100) break; //다음 좌표가 100을 넘어갈 경우 이동x
                else if (id == 100) ans = Math.min(ans, idx[1] + 1); //도착지점에 도달한 경우 결과값 갱신

                if (map[id] == 1) { //다음좌표에 뱀 또는 사다리가 있는 경우
                    int next = hash.get(id);
                    queue.offer(new int[]{next, idx[1] + 1});
                    v[next] = true;
                } else {
                    queue.offer(new int[]{id, idx[1] + 1});
                    v[id] = true;
                }
            }
        }
    }
}
