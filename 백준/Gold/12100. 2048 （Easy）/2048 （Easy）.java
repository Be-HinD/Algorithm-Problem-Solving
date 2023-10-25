import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
//2048(Easy) DFS 및 시뮬레이션 풀이
public class Main {
    static int N, res;
    static int[][] inMap;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        inMap = new int[N][N];
        for(int i=0; i<N; i++) { //맵 입력
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                inMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, inMap);

        System.out.println(res);

    }

    private static void dfs(int cnt, int[][] map) {
        //기저조건
        if(cnt == 5) {
            //배열에서 최대값 찾기
            int max = 0;
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(map[i][j] > max) max = map[i][j];
                }
            }
            res = Math.max(res, max); //결과값 갱신
            return;
        }

        for(int i=0; i<4; i++) {
            int[][] copy = new int[N][N];
            for(int j=0; j<N; j++) { //깊은 복사
                copy[j] = map[j].clone();
            }

            logic(copy, i);

            //다음 이동을 위한 재귀호출
            dfs(cnt+1, copy);

            //백트래킹 필요 X
        }
    }
    private static void logic(int[][] map, int dist) {
        //각 dist에 대해서
        //i,j 좌표에 대해서
        //i,j+1부터 비교 시작
        //비교하는 값이 0이라면 다음 칸 탐색
        //0이 아닌 수를 찾고 해당 값이 같은 값이라면 현재칸에 증감 및 0으로 배열 값 갱신
        //다른 값일 경우 현재 칸이 0이면 현재 칸으로 단순 이동 후 계속 탐색 진행!!
        //현재 칸에 값이 있다면 이동하지않고 break.
        if(dist == 0) { //위
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    //0,0을 기준으로 0,1부터 0이 아닌 숫자 탐색
                    for(int k=j+1; k<N; k++) {
                        if(map[k][i] != 0) { //0이라면 다음 칸으로
                            if (map[j][i] == map[k][i]) { //합쳐야 할 때
                                map[j][i] += map[k][i];
                                map[k][i] = 0;
                                break; //탈출
                            } else if (map[j][i] != map[k][i]) { //값이 다르다면
                                if(map[j][i] == 0) { //현재 칸이 0이라면 현재칸으로 이동
                                    map[j][i] = map[k][i];
                                    map[k][i] = 0;
                                } else { //현재 칸에 값이 있다면 이동 x
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        } else if(dist == 1) { //아래
            for(int i=N-1; i>=0; i--) {
                for(int j=N-1; j>=0; j--) {
                    for(int k=j-1; k>=0; k--) {
                        if(map[k][i] != 0) {
                            if (map[j][i] == map[k][i]) { //합쳐야 할 때
                                map[j][i] += map[k][i];
                                map[k][i] = 0;
                                break;
                            } else if (map[j][i] != map[k][i]) { //값이 다르면 내리기
                                if (map[j][i] == 0) {
                                    map[j][i] = map[k][i];
                                    map[k][i] = 0;
                                } else {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        } else if(dist == 2) { //오른쪽
            for(int i=0; i<N; i++) {
                for(int j=N-1; j>=0; j--) {
                    for(int k=j-1; k>=0; k--) {
                        if(map[i][k] != 0) {
                            if (map[i][j] == map[i][k]) { //합쳐야 할 때
                                map[i][j] += map[i][k];
                                map[i][k] = 0;
                                break;
                            } else if (map[i][j] != map[i][k]) { //값이 다르면 내리기
                                if (map[i][j] == 0) {
                                    map[i][j] = map[i][k];
                                    map[i][k] = 0;
                                } else {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        } else if(dist == 3) { //왼쪽
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    for(int k=j+1; k<N; k++) {
                        if(map[i][k] != 0) { //0이 아닌 숫자를 찾았을 때
                            if (map[i][j] == map[i][k]) { //합쳐야 할 때
                                map[i][j] += map[i][k];
                                map[i][k] = 0;
                                break;
                            } else if (map[i][j] != map[i][k]) { //값이 다르면 올리기
                                if (map[i][j] == 0) {
                                    map[i][j] = map[i][k];
                                    map[i][k] = 0;
                                } else {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}