import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, D, res;
    static int[] combiAns; //조합 결과배열
    static int[] dx = new int[]{0,-1,1,0}; //왼쪽부터 탐색
    static int[] dy = new int[]{-1,0,0,1};
    static int[][] map, copyMap, attackPoint; //맵, 복사맵, 공격할 좌표
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken())+1; //행(궁수를 둘 자리 +1)
        M = Integer.parseInt(st.nextToken()); //열
        D = Integer.parseInt(st.nextToken()); //공격 거리

        map = new int[N][M]; //맵 입력
        for(int i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        combiAns = new int[3];
        Comb(0, 0);
        System.out.println(res);
    }
    private static void Comb(int cnt, int start) {
        if(cnt == 3) {//궁수 3명
            //조합 완성
            copyMap = new int[N][M];
            for(int i=0; i<N; i++) {
                copyMap[i] = map[i].clone(); //깊은 복사
            }
            for(int i=0; i< combiAns.length; i++) {
                copyMap[N-1][combiAns[i]] = 2; //궁수 배치
            }
            attackPoint = new int[3][2]; //궁수 3명이 쏠 좌표를 저장하는 배열
            //시뮬레이션 수행
            logic();
        } else {
            for(int i=start; i<M; i++) {
                combiAns[cnt] = i;
                Comb(cnt+1, i+1);
            }
        }
    }
    private static void logic() {
        int cnt = 0; //잡은 적의 개수 카운트 변수
        while(true) {
            //3명의 궁수가 쏠 자리를 저장
            for(int i=0; i<3; i++) {
                int[] idx = new int[]{N-1, combiAns[i]}; //궁수의 좌표
                attackPoint[i] = bfs(idx[0]-1, idx[1]); //타겟 적의 좌표 탐색
            }
            //한번에 제거, cnt 체크
            for(int i=0; i<3; i++) {
                int[] idx = attackPoint[i];
                if(idx[0] == -1) continue; //공격할 적을 찾지 못한 경우
                if(copyMap[idx[0]][idx[1]] == 1) {
                    copyMap[idx[0]][idx[1]] = 0;
                    cnt++;
                }
            }
            //적 아래로 한칸씩 Move
            for(int i=N-1; i>=0; i--) {
                for(int j=0; j<M; j++) {
                    if(copyMap[i][j] == 1) {
                        copyMap[i][j] = 0;
                        int nx = i+1;
                        if(nx != N-1) {
                            copyMap[nx][j] = 1;
                        }
                    }
                }
            }
            //적 남아있는지 확인
            boolean flag = false;
            for(int i=0; i<N-1; i++) {
                for(int j=0; j<M; j++) {
                    if(copyMap[i][j] == 1) {
                        flag = true;
                        break;
                    }
                }
                if(flag) break;
            }
            if(!flag) {
                res = Math.max(res, cnt); //최대값 갱신
                break;
            }
        }
    }

    private static int[] bfs(int x, int y) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] v = new boolean[N-1][M];
        if(copyMap[x][y] == 1) { //시작 지점에 적이 있다면
            return new int[]{x,y};
        }
        v[x][y] = true;
        q.offer(new int[]{x,y, 1});
        while(!q.isEmpty()) {
            int[] idx = q.poll();
            if(idx[2] > D-1) continue; //공격 범위를 초과할 경우
            for(int i=0; i<4; i++) {
                int nx = idx[0] + dx[i];
                int ny = idx[1] + dy[i];

                if(nx<0 || ny<0 || nx>=N-1 || ny>=M || v[nx][ny]) continue;
                if(copyMap[nx][ny] == 1) { //적을 찾을 경우
                    return new int[]{nx,ny, idx[2]+1};
                } else { //적을 찾지 못할 경우
                    v[nx][ny] = true;
                    q.offer(new int[]{nx,ny, idx[2]+1});
                }
            }
        }
        return new int[]{-1,-1};
    }
}