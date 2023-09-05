import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, G, R, ans; //입력값 및 결과값
    static int map[][], rArr[], gArr[]; //입력배열, red배양액 배열, green배양액 배열
    static boolean[] v; //조합에서 사용할 방문배열
    static int[] dx = new int[] {-1,1,0,0}; //방향벡터
    static int[] dy = new int[] {0,0,-1,1}; //방향벡터
    static ArrayList<int[]> list; //배양액을 심을 수 있는 좌표들을 관리하는 리스트
    static Queue<int[]> queue = new ArrayDeque<>(); //BFS에서 사용할 전역 큐

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        list = new ArrayList<>();
        for (int i = 0; i < N; i++) { //맵 입력
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int idx = Integer.parseInt(st.nextToken());
                map[i][j] = idx;
                if (idx == 2) list.add(new int[]{i, j});
            }
        }

        v = new boolean[list.size()]; //list길이에서 조합을 사용
        rArr = new int[R];
        gArr = new int[G];
        greenPermu(0, 0);
        System.out.println(ans);
    }

    private static void greenPermu(int start, int cnt) { //green배양액을 심을 위치를 뽑기위한 조합
        if(cnt == G) {
            //순열 생성완료
            redPermu(0, 0); //green을 다 뽑았으면 red를 선택하러
        } else {
            for(int i=start; i<list.size(); i++) {
                if(v[i]) continue; //방문배열을 사용한 이유는 선택된 green자리를 제외한 나머지 자리에서 red자리를 선택해야하기 때문
                v[i] = true;
                gArr[cnt] = i;
                greenPermu(i + 1,cnt + 1);
                v[i] = false;
            }
        }
    }

    private static void redPermu(int start, int cnt) { //red배양액을 심을 위치를 뽑기위한 조합
        if(cnt == R) {
            //순열 생성완료
            bfs(); //배양액 퍼트리기
        } else {
            for(int i=start; i<list.size(); i++) {
                if(v[i]) continue;
                v[i] = true;
                rArr[cnt] = i;
                redPermu(i + 1, cnt + 1);
                v[i] = false;
            }
        }
    }

    private static void bfs() { //배양액을 퍼트리며 동작 확인
        //임시 맵 깊은 복사
        Point[][] temp = new Point[N][M];
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                temp[i][j] = new Point(map[i][j], 0);
            }
        }
        //그린부터 심으면서 큐에 추가
        for(int i=0; i<gArr.length; i++) {
            int[] idx = list.get(gArr[i]);
            queue.offer(new int[]{idx[0], idx[1]});
            temp[idx[0]][idx[1]] = new Point(3, 0);
        }
        //레드
        for(int i=0; i<rArr.length; i++) {
            int[] idx = list.get(rArr[i]);
            queue.offer(new int[]{idx[0], idx[1]});
            temp[idx[0]][idx[1]] = new Point(4, 0);
        }

        int sum = 0; //꽃의 개수

        while(!queue.isEmpty()) {
            int[] idx = queue.poll();
            int time = temp[idx[0]][idx[1]].time; //현재위치 시간
            int type = temp[idx[0]][idx[1]].type; //현재위치 상태
            if(temp[idx[0]][idx[1]].type == -1) continue; //꽃이 된 자리가 큐에 있었을 경우

            for(int i=0; i<4; i++) {
                int nx = idx[0] + dx[i];
                int ny = idx[1] + dy[i];
                if(nx<0 || ny<0 || nx>N-1 || ny>M-1) continue; //예외처리
                if(temp[nx][ny].type == 1 || temp[nx][ny].type == 2) { //확장 가능
                    temp[nx][ny].type = type;
                    temp[nx][ny].time = time + 1;
                    queue.offer(new int[]{nx, ny});
                    continue;
                }
                if(temp[nx][ny].type != type && temp[nx][ny].type != -1) { //다른 배양액이 존재할 경우
                    if((temp[idx[0]][idx[1]].time + 1) == temp[nx][ny].time) { //동시간대 겹치는 경우
                        temp[nx][ny].type = -1; //꽃 생성
                        sum++;
                    }
                }
            }
        }
        ans = Math.max(ans, sum); //결과값 비교 및 갱신
    }
    static class Point { //맵 좌표의 정보 클래스 -> 임시배열에서 사용
        int type;
        int time;
        Point(int type, int time) {
            this.type = type;
            this.time = time;
        }
    }
}
