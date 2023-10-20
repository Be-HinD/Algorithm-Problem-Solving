import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, pointer;
    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};
    static int[] dist = new int[100055];
    static int[][] map, res;
    static ArrayList<int[]> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer(); //SB안쓰면 시간초과

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M]; //입력 맵
        res = new int[N][M]; //결과 맵

        for(int i=0; i<N; i++) { //입력
            String input = br.readLine();
            for(int j=0; j<M; j++) {
                int idx = input.charAt(j) - '0';
                if(idx == 1) list.add(new int[]{i,j}); //벽들의 위치리스트 추가
                map[i][j] = idx;
            }
        }

        pointer = 2; //영역 시작 인덱스
        for(int i=0; i<N; i++) { //영역 나누기
            for(int j=0; j<M; j++) {
                if(map[i][j] == 0) {
                    zeroBfs(i, j);
                }
            }
        }

        for(int[] idx : list) {
            boolean[] v = new boolean[pointer]; //같은 영역 체크 배열
            for(int i=0; i<4; i++) {
                int nx = idx[0] + dx[i];
                int ny = idx[1] + dy[i];
                if(nx<0 || ny<0 || nx>=N || ny>= M || v[map[nx][ny]]) continue;
                v[map[nx][ny]] = true;
                res[idx[0]][idx[1]] += dist[map[nx][ny]]; //영역의 값 추가
            }
            res[idx[0]][idx[1]]++;
            res[idx[0]][idx[1]] %= 10;
        }

        for(int i=0; i<N; i++) { //결과값 담기
            for(int j=0; j<M; j++) {
                sb.append(res[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void zeroBfs(int x, int y) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x,y});
        map[x][y] = pointer;
        int cnt = 1;

        while(!q.isEmpty()) { //인접 0에 대해 갱신 및 cnt증가
            int[] idx = q.poll();

            for(int i=0; i<4; i++) {
                int nx = idx[0] + dx[i];
                int ny = idx[1] + dy[i];
                if(nx<0 || ny<0 || nx>=N || ny>= M || map[nx][ny] != 0) continue;
                map[nx][ny] = pointer;
                q.offer(new int[]{nx, ny});
                cnt++;
            }
        }
        dist[pointer++] = cnt; //영역값 갱신
    }
}