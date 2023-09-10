import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, K, L, time, cnt, baamx, baamy, rearx, reary, rearDist;
    static int[][] map;
    static String dist;
    static int[] dx = new int[] {-1,0,1,0}; //방향벡터
    static int[] dy = new int[] {0,1,0,-1}; //시계방향
    static StringTokenizer st;
    static BufferedReader br;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        map = new int[N][N];
        for(int i=0; i<K; i++) { //맵 입력
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1; //**문제에서 1행 1열이 시작위치기 때문에 -1**
            int y = Integer.parseInt(st.nextToken()) - 1; //**문제에서 1행 1열이 시작위치기 때문에 -1**
            map[x][y] = 1; //사과 위치 지정
        }
        map[0][0] = 2; //뱀의 시작위치
        L = Integer.parseInt(br.readLine()); //방향전환 횟수
        st = new StringTokenizer(br.readLine());
        time = Integer.parseInt(st.nextToken()); //방향전환할 시간

        //뱀의 머리위치와 꼬리위치 지정
        baamx = 0;
        baamy = 0;
        rearx = 0;
        reary = 0;
        logic();
        System.out.println(cnt +1); //1초부터 시작이라서 +1
    }

    private static void logic() throws IOException {
        int vector = 1; //처음 진행방향은 오른쪽
        int idx = 1; //지금껏 방향전환한 횟수
        Queue<Integer> queue = new ArrayDeque(); //꼬리를 줄일 때 사용할 벡터를 담는 큐
        while (true) {
            //기저조건
            if(idx < L+1){ //방향전환 횟수를 끝냈다면 비교x
                if (cnt == time) { //방향회전할 시간이라면
                    dist = st.nextToken(); //벡터값 입력
                    if (dist.equals("D")) { //왼쪽 : C, 오른쪽 : D 90도 방향회전
                        vector = (vector + 1) % 4;
                    } else { //왼쪽
                        vector = vector - 1;
                        if (vector == -1) {
                            vector = 3;
                        }
                    }
                    if(idx < L) { //방향전환 횟수가 끝났다면 입력받지 못하게
                        st = new StringTokenizer(br.readLine());
                        time = Integer.parseInt(st.nextToken());
                        idx++; //방향전환횟수++
                    }
                }
            }
            int nx = baamx + dx[vector];
            int ny = baamy + dy[vector];
            queue.offer(vector); //큐에 현재 백터 추가

            if(nx<0 || ny<0 || nx>N-1 || ny>N-1) return; //기저조건 : 맵밖으로 나갈 때
            if(map[nx][ny] == 2 ) return; //기저조건 : 몸이랑 부딪힐 경우

            if(map[nx][ny] == 1) { //사과가 있을 경우
                map[nx][ny] = 2; //사과는 없어지고 머리 이동
                baamx = nx; //머리정보 갱신
                baamy = ny;
            } else if(map[nx][ny] == 0){ //사과가 없을 경우
                map[nx][ny] = 2; //머리 이동
                baamx = nx;
                baamy = ny;
                map[rearx][reary] = 0; //꼬리 줄이기
                rearDist = queue.poll(); //꼬리위치 기준 벡터값 꺼내서
                rearx = rearx + dx[rearDist]; //꼬리정보 갱신
                reary = reary + dy[rearDist];

            }
            cnt++; //시간초 증가
        }
    }
}
