import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int R, C, N;
    static String dist;
    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};
    static char[][] map;
    static boolean[][] v;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        for(int i=0; i<R; i++) {
            String input = br.readLine();
            for(int j=0; j<C; j++) {
                map[i][j] = input.charAt(j);
            }
        }

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            int height = R - Integer.parseInt(st.nextToken()); //입력 1 : 가장 바닥 => 맽 밑(R-1)에서 (입력값-1) 해준 값이 층을 나타냄.
            if(i%2 == 0) dist = "right"; //짝수면 왼쪽에서 오른쪽으로
            else dist = "left"; //홀수면 오른쪽에서 왼쪽으로
            remove(height, dist); //클러스터 삭제
            v = new boolean[R][C];
            bfs(); //이동해야할 클러스터가 있는지 탐색
        }

        for(int i=0; i<R; i++) { //최종 결과 출력
            for(int j=0; j<C; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }
    private static void remove(int height, String dist) {
        if(dist.equals("right")) { //왼쪽에서 오른쪽
            for(int i=0; i<C; i++) {
                if(map[height][i] == 'x') {
                    map[height][i] = '.';
                    return;
                }
            }
        } else { //오른쪽에서 왼쪽
            for(int i=C-1; i>=0; i--) {
                if(map[height][i] == 'x') {
                    map[height][i] = '.';
                    return;
                }
            }
        }
    }

    private static void bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        for(int i=0; i<C; i++) { //제일 아래 바닥에 있는 클러스터 큐에 추가
            if(map[R-1][i] == 'x') {
                v[R-1][i] = true;
                q.offer(new int[]{R-1,i});
            }
        }
        while(!q.isEmpty()) { //탐색하면서 바닥과 붙어있는(즉 이동하지 않는) 클러스터들의 방문체크 진행
            int[] idx = q.poll();

            for(int i=0; i<4; i++) {
                int nx = idx[0] + dx[i];
                int ny = idx[1] + dy[i];
                if(nx<0 || ny<0 || nx>=R || ny>=C || v[nx][ny] || map[nx][ny] == '.') continue;
                v[nx][ny] = true;
                q.offer(new int[]{nx,ny});
            }
        }

        //공중에 떠있는 (즉 이동해야하는) 클러스터들 탐색
        ArrayList<int[]> list = new ArrayList<>();
        for(int i=0; i<R; i++) { //밑에서부터 역순으로 담아야 값 갱신이 잘 됨.
            for(int j=0; j<C; j++) {
                if(map[i][j] == 'x' && !v[i][j]) { //미네랄이면서 방문하지 않은 곳
                    map[i][j] = '.'; //값 갱신
                    list.add(new int[]{i,j}); //리스트 추가
                }
            }
        }

        if(!list.isEmpty()) { //움직여야 할 클러스터가 있다면
            int p = 1; //내려갈 값을 담는 포인터
            while(true) { //1계단씩 늘려가며 체크
                //내려갈 수 있는지 체크 (최대한 내려가야함. 내려갈 수 있을만큼)
                boolean flag = false;
                for (int i = 0; i < list.size(); i++) {
                    int[] idx = list.get(i);
                    if((idx[0]+p) == R) { //맵 밖으로 나갈 경우
                        flag = true;
                        break;
                    }
                    if (map[idx[0] + p][idx[1]] == 'x') { //이동하지 못할 경우
                        flag = true;
                        break;
                    }
                }
                if(flag) {
                    p--; //포인터 감소 후 종료
                    break;
                }
                p++;
            }
            if(p == 0) return; //이동할 수 없는 경우

            //내려갈 수 있을 경우 포인터 값만큼 이동
            for (int i = 0; i < list.size(); i++) {
                int[] idx = list.get(i);
                map[idx[0] + p][idx[1]] = 'x';
            }
        }
    }
}
