import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ_17142 연구소 3
public class Main {
    static int N, M, res;
    static List<int[]> birusList = new ArrayList<>();
    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};
    static int[] combArr;
    static PriorityQueue<int[]> q;
    static boolean[][] v;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //맵 크기
        M = Integer.parseInt(st.nextToken()); //활성화 개수

        map = new int[N][N];
        for(int i=0; i<N; i++) { //맵 입력
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                int idx = Integer.parseInt(st.nextToken());
                if(idx == 2) { //바이러스 리스트 추가
                    birusList.add(new int[]{i,j});
                }
                map[i][j] = idx;
            }
        }

        combArr = new int[M];
        res = Integer.MAX_VALUE;
        Comb(0, 0);

        bfs(map);

        if(res < Integer.MAX_VALUE) System.out.println(res);
        else System.out.println(-1);
    }

    private static void Comb(int cnt, int start) {
        if(cnt == M) {
            //M개의 바이러스 선택
            //카피맵 생성
            int[][] copy = new int[N][N];
            for(int i=0; i<N; i++) copy[i] = map[i].clone();

            q = new PriorityQueue<>(new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[2] - o2[2];
                }
            });
            v = new boolean[N][N];
            
            for(int i=0; i<combArr.length; i++) {
                int[] idx = birusList.get(combArr[i]);
                copy[idx[0]][idx[1]] = 100; //활성화
                q.offer(new int[]{idx[0],idx[1], 0}); //좌표, 시간
                v[idx[0]][idx[1]] = true;
            }

            bfs(copy);

            return;
        }

        for(int i=start; i<birusList.size(); i++) {
            combArr[cnt] = i;
            Comb(cnt+1, i+1);
        }

    }

    private static void bfs(int[][] map) {

        int cnt = 0;
        while(!q.isEmpty()) {
            int[] cur = q.poll();

//            if(cur[2] >= res) return; //질문게시판 보고 찾았는데 왜 주석처리해야하지?

            if(map[cur[0]][cur[1]] != 2) cnt = Math.max(cnt, cur[2]);

            for(int i=0; i<4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if(nx<0 || ny<0 || nx>=N || ny>=N || v[nx][ny] || map[nx][ny] == 1) continue;

                if(map[nx][ny] == 2) {
                    //비활성 바이러스의 경우
                    q.offer(new int[]{nx,ny,cur[2]+1});
                    v[nx][ny] = true;
                    continue;
                }
                
                q.offer(new int[]{nx,ny,cur[2]+1});
                map[nx][ny] = 100; //완탐이 안될 경우를 위해 임의 값 갱신
                v[nx][ny] = true;


            }
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(map[i][j] == 0) return; //다 퍼지지않았다면 종료.
            }
        }
        res = Math.min(res, cnt);
    }
}