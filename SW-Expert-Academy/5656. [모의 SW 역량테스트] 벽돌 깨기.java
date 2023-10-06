import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static int tc, N, W, H, ans;
    static int cx;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        tc = Integer.parseInt(br.readLine());

        for (int t = 1; t <= tc; t++) { //testCase
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); //떨어뜨릴 벽돌 개수
            H = Integer.parseInt(st.nextToken()); //행
            W = Integer.parseInt(st.nextToken()); //열

            map = new int[W][H];
            for(int i=0; i<W; i++) { //맵 입력
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<H; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            //완탐 N번을 아무렇게나 다 던져봐야하고
            //완탐에 따른 동작과정을 시뮬레이션으로 진행하고 백트래킹
            //백트래킹은 맵을 새로 본따고 진행
            ans = Integer.MAX_VALUE; //결과값 초기화

            dfs(map, 0);

            if(ans<Integer.MAX_VALUE) System.out.println("#" + t + " " + ans); //다 던지고 벽돌이 남은 경우
            else System.out.println("#" + t + " " + 0); //맵에 벽돌이 남지 않는 경우

        }
    }

    private static void dfs(int[][] map, int condition) { //매개변수 : 카피맵, 열 크기, 부순 개수
        if(condition >= N) { //종료조건 결과값 비교
            int count = 0;
            for(int i=0; i<W; i++) { //남은 벽돌의 개수 측정
                for(int j=0; j<H; j++) {
                    if(map[i][j] != 0) count++;
                }
            }
            ans = Math.min(ans, count);
            return;
        }
        int[][] copy = new int[W][H];

        for(int i=0; i<H; i++) { //0행부터
            //남은 벽돌이 있는지 체크
            if(isValidate(map, i)) {
                //남은 벽돌이 있다면
                //맵 변환
                for(int j=0; j<W; j++) { //깊은 복사
                    copy[j] = map[j].clone();
                }

                drop(copy, cx, i, copy[cx][i]); //벽돌을 떨어뜨리기
                //재귀호출
                dfs(copy, condition+1);
            }
        }
    }

    private static boolean isValidate(int[][] map, int idx) { //idx열에 남은 벽돌이 있는지 체크하는 로직
        for(int i=0; i<W; i++) {
            if(map[i][idx] != 0) {
                cx = i;
                return true;
            }
        }
        return false;
    }

    private static void drop(int[][] map, int x, int y, int scope) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x,y,scope});
        map[x][y] = 0;

        while(!queue.isEmpty()) {
            int[] idx = queue.poll();
            int nx = idx[0];
            int ny = idx[1];
            //상
            for(int i=1; i<idx[2]; i++) { //퍼져야 하는 크기만큼 진행
                nx -= 1;
                if(nx<0 || nx>=W || map[nx][ny] == 0) continue;
                queue.offer(new int[]{nx,ny,map[nx][ny]}); //크기를 먼저 담고
                map[nx][ny] = 0; //맵 갱신
            }

            nx = idx[0]; //초기화
            //하
            for(int i=1; i<idx[2]; i++) { //퍼져야 하는 크기만큼 진행
                nx += 1;
                if(nx<0 || nx>=W || map[nx][ny] == 0) continue;
                queue.offer(new int[]{nx,ny,map[nx][ny]}); //크기를 먼저 담고
                map[nx][ny] = 0; //맵 갱신
            }

            nx = idx[0]; //초기화
            //좌
            for(int i=1; i<idx[2]; i++) { //퍼져야 하는 크기만큼 진행
                ny -= 1;
                if(ny<0 || ny>=H || map[nx][ny] == 0) continue;
                queue.offer(new int[]{nx,ny,map[nx][ny]}); //크기를 먼저 담고
                map[nx][ny] = 0; //맵 갱신
            }

            nx = idx[0]; //초기화
            ny = idx[1]; //초기화
            //우
            for(int i=1; i<idx[2]; i++) { //퍼져야 하는 크기만큼 진행
                ny += 1;
                if(ny<0 || ny>=H || map[nx][ny] == 0) continue;
                queue.offer(new int[]{nx,ny,map[nx][ny]}); //크기를 먼저 담고
                map[nx][ny] = 0; //맵 갱신
            }
        }
        //벽돌 떨어뜨리는 로직
        zerodrop(map);
    }

    private static void zerodrop(int[][] map) {
        for(int i=0; i<H; i++) { //0열부터
            for(int j=W-1; j>=0; j--) { //아래에서부터 찾아서
                if(map[j][i] == 0) { //빈칸이라면
                    for(int k=j-1; k>=0; k--) { //바로 위칸부터
                        if(map[k][i] != 0) { // 벽돌 있는 위치 찾고
                            map[j][i] = map[k][i]; //SWAP
                            map[k][i] = 0;
                            break; //break 필수 안하면 한자리에서 계속 SWAP됨.
                        }
                    }
                }
            }
        }
    }
}
