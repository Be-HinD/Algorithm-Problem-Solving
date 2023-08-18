import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, K; //입력값
    static int[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //수빈이 위치
        K = Integer.parseInt(st.nextToken()); //동생 위치
        visited = new int[100055]; //방문배열 초기화
        bfs();

        System.out.println(visited[K]-1); //결과 출력
    }

    private static void bfs() {
        Queue<Integer> q = new ArrayDeque<>(); //큐 생성
        q.offer(N); //수빈이 위치 큐에 추가
        visited[N] = 1; //방문체크용 1 갱신

        while(!q.isEmpty()) {
            int point = q.poll();
            int way1 = point + 1;
            int way2 = point - 1;
            int way3 = point * 2;
            if(way1 < 100001 && way1 > -1) { //null처리
                if(visited[way1] == 0) {
                    visited[way1] = visited[point] + 1; //너비 관리
                    q.offer(way1); //way1 경우의 케이스 추가
                }
            }
            if(way2 < 100001 && way2 > -1) { //null처리
                if(visited[way2] == 0) {
                    visited[way2] = visited[point] + 1; //너비 관리
                    q.offer(way2); //way2 경우의 케이스 추가
                }
            }
            if(way3 < 100001 &&way3 > -1) { //널 예외처리
                if(visited[way3] == 0) {
                    visited[way3] = visited[point] + 1; //너비 관리
                    q.offer(way3); //way3 경우의 케이스 추가
                }
            }
        }
    }
}
