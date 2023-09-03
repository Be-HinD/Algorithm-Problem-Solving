import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main {
    static int N, Min;
    static boolean[] v;
    static int map[][], ans[]; //입력값이 인접행렬로 들어옴

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //도시의 수
        //단, 한 번 갔던 도시로는 다시 갈 수 없다.
        //(맨 마지막에 여행을 출발했던 도시로 돌아오는 것은 예외)
        //가장 적은 비용 경로 찾기
        //비용은 방향가중치
        //모든 도시간의 비용은 양의 정수
        //W[i][i]는 항상 0이다.
        //경우에 따라서 도시 i에서 도시 j로 갈 수 없는 경우도 있으며
        // 이럴 경우 W[i][j]=0
        //행렬 최대값은 10*10 -> 인접행렬 사용가능
        map = new int[N][N];
        for(int i=0; i<N; i++) { //인접행렬 입력
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        v = new boolean[N]; //방문배열 초기화
        ans = new int[N]; //순열의 결과를 담을 배열 초기화
        Min = Integer.MAX_VALUE; //최소값 비교를 위한 MAX값 초기화
        Permutation(0);
        System.out.println(Min); //결과 출력
    }

    private static void Permutation(int cnt) {
        if(cnt == N) {
            //순회할 수 있는 모든 경로의 순열에 대해 로직 수행
            logic();
        } else {
            for(int i=0; i<N; i++) {
                if(v[i]) continue;
                v[i] = true;
                ans[cnt] = i;
                Permutation(cnt + 1);
                v[i] = false;
            }
        }
    }

    private static void logic() {
        int dist = 0; //거리 합
        for(int i=0; i<N; i++) { //순열의 도시순서대로
            int idx = (i+1)%N; //맨 마지막 도시에서 시작도시까지 돌아오는 경우까지 고려
            int value = map[ans[i]][ans[idx]];
            if(value == 0) return; //경로가 없는 경우 종료
            dist += value; //경로가 있다면 가중치 합산
            if(Min < dist) return; //백트래킹 : 합산된 거리가 최소값보다 커질 시 즉시 리턴
        }
        Min = Math.min(Min, dist); //결과값 비교
    }
}
