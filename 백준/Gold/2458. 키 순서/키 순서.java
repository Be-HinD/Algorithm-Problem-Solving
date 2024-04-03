import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N+1][N+1]; //입력은 1부터

        int i, j;
        for(int m=0; m<M; ++m) {
            st = new StringTokenizer(br.readLine());
            i = Integer.parseInt(st.nextToken());
            j = Integer.parseInt(st.nextToken());
            map[i][j] = 1; //i키<j키 인접행렬
        }

        for(int m=1; m<=N; m++) {
            map[m][0] = -1;
        }
        int answer = 0; //결과값
        for(int k=1; k<=N; ++k) {
            if(map[k][0] == -1) gtDfs(k); //탐색되지 않은 정점만 탐색!
        }
        //메모된 인접행렬의 상태를 이용하여 자신보다 작은학생수 카운팅!!
        for(int k=1; k<=N; k++) {
            for(int l=1; l<=N; l++) {
                map[0][l] += map[k][l];
            }
        }

        //자신보다 키가 큰 학생수와 작은 학생수의 합이 N-1인지 확인!!
        for(int m=1; m<=N; m++) {
            if(map[m][0] + map[0][m] == N-1) answer++;
        }
        System.out.println(answer);
    }

    private static void gtDfs(int k) {
        for(int i=1; i<=N; i++) { //자신의 인접행렬 처리
            if(map[k][i] == 1) { //나보다 크고
                if(map[i][0] == -1) { //미탐색된 상태
                    //탐색 시작
                    gtDfs(i);
                }

                //탐색을 완료했거나 이미 탐색된 대상이어서 탐색하지 않고 내려온 경우
                // i보다 큰 대상이 1명이상이면 k보다 큰 간접대상이 존재가능한 상황!
                // ==> 새롭게 알게된 간접관계를 나와의 직접 관계로 인접행렬에 반영
                if(map[i][0]>0) {
                    for(int j=1; j<=N; j++) {
                        if(map[i][j]==1) {
                            map[k][j] = 1;
                        }
                    }
                }
            }
        }
        //자신의 인접행렬을 모두 처리 ==> 직/간접 관계가 인접행렬에 다 반영되어 있는 상태!!
        //자신보다 큰 학생수를 카운팅해서 메모하고 리턴하기
        int cnt = 0;
        for(int j=1; j<=N; j++) {
            cnt += map[k][j];
        }

        map[k][0] = cnt;
    }
}