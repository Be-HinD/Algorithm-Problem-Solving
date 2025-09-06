import java.io.*;
import java.util.*;

public class Main {
    static int n, q;
    static int[][] map;
    static boolean[][] v;
    static int[] dx = new int[]{0,0,1,-1};
    static int[] dy = new int[]{1,-1,0,0};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        n = (int) Math.pow(2, n);
        map = new int[n][n];

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<q; i++) {
            int l = Integer.parseInt(st.nextToken());
            logic(l);
        }

        // 전체 얼음의 합 계산
        int res = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                res += map[i][j];
            }
        }

        // 가장 큰 덩어리 크기 계산
        v = new boolean[n][n];
        int maxIce = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(!v[i][j] && map[i][j] > 0) {
                    maxIce = Math.max(maxIce, bfs(i, j));
                }
            }
        }

        System.out.println(res);
        System.out.println(maxIce);
    }

    static void logic(int L) {
        // 1단계: 격자 회전
        if(L > 0) { // L=0이면 회전할 필요 없음
            int size = (int) Math.pow(2, L);
            for(int i=0; i<n; i+=size) {
                for(int j=0; j<n; j+=size) {
                    rotate(i, j, size);
                }
            }
        }

        // 2단계: 얼음 감소 (동시에 처리하기 위해 임시 배열 사용)
        int[][] temp = new int[n][n];
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                temp[i][j] = map[i][j];
                if(map[i][j] > 0) {
                    int cnt = 0;
                    for(int d=0; d<4; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];
                        if(nx>=0 && ny>=0 && nx<n && ny<n && map[nx][ny] > 0) {
                            cnt++;
                        }
                    }
                    if(cnt < 3) {
                        temp[i][j] = Math.max(0, map[i][j] - 1);
                    }
                }
            }
        }
        map = temp;
    }

    // 간단한 90도 회전 함수
    static void rotate(int startX, int startY, int size) {
        int[][] temp = new int[size][size];
        
        // 원본 데이터를 temp에 복사
        for(int i=0; i<size; i++) {
            for(int j=0; j<size; j++) {
                temp[i][j] = map[startX + i][startY + j];
            }
        }
        
        // 90도 회전하여 다시 map에 저장
        for(int i=0; i<size; i++) {
            for(int j=0; j<size; j++) {
                map[startX + i][startY + j] = temp[size-1-j][i];
            }
        }
    }

    static int bfs(int x, int y) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x, y});
        v[x][y] = true;

        int cnt = 0;
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            cnt++;

            for(int d=0; d<4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];
                if(nx>=0 && ny>=0 && nx<n && ny<n && !v[nx][ny] && map[nx][ny] > 0) {
                    q.offer(new int[]{nx, ny});
                    v[nx][ny] = true;
                }
            }
        }
        return cnt;
    }
}