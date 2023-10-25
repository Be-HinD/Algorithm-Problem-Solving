import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
//2048(Easy) DFS 및 시뮬레이션 풀이
public class Main {
    static int N, res;
    static int[] dx = new int[]{-1,1,0,0}; //북,남,동,서
    static int[] dy = new int[]{0,0,1,-1};
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
            //해당 방향으로 맵 변경
//            System.out.println("************변경 전*****************");
//            System.out.println("방향 값 : " + i);
//            for(int j=0; j<N; j++) {
//                System.out.println(Arrays.toString(copy[j]));
//            }
            logic(copy, i);

//            System.out.println("************변경 후*****************");
//            System.out.println("방향 값 : " + i);
//            for(int j=0; j<N; j++) {
//                System.out.println(Arrays.toString(copy[j]));
//            }

            //다음 이동을 위한 재귀호출
            dfs(cnt+1, copy);

            //백트래킹 필요 X
        }
    }
    private static void logic(int[][] map, int dist) {
        if(dist == 0) { //위
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    //0,0을 기준으로 0,1부터 0이 아닌 숫자 탐색
                    for(int k=j+1; k<N; k++) {
                        if(map[k][i] != 0) { //0이라면 다음 칸으로
                            if (map[j][i] == map[k][i]) { //합쳐야 할 때
                                map[j][i] += map[k][i];
                                map[k][i] = 0;
                                break;
                            } else if (map[j][i] != map[k][i]) { //값이 다르면 올리기
                                if(map[j][i] == 0) {
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