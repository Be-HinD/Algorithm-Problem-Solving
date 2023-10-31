import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ_1647 도시 분할 계획
public class Main {
    static int N, M, K, res, maxWeight;
    static int[][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();

        N = Integer.parseInt(st.nextToken()); //정점 개수
        M = Integer.parseInt(st.nextToken()); //간선 개수

        //플로이드 워셜 초기화 단계
        dist = new int[N][N];
        for(int i=0; i<N; i++) Arrays.fill(dist[i], Integer.MAX_VALUE);
        for(int i=0; i<N; i++) {
            dist[i][i] = 0;
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            dist[x][y] = -1; //정방향
            dist[y][x] = 1; //역방향
        }

//        System.out.println("****플로이드 전****");
//        for(int i=0; i<N; i++) {
//            System.out.println(Arrays.toString(dist[i]));
//        }

        Floyd();

//        System.out.println();
//        System.out.println("****플로이드 후****");
//
//        for(int i=0; i<N; i++) {
//            System.out.println(Arrays.toString(dist[i]));
//        }

        K = Integer.parseInt(br.readLine());

        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            int pre = Integer.parseInt(st.nextToken()) - 1;
            int post = Integer.parseInt(st.nextToken()) - 1;
            if(dist[pre][post] == Integer.MAX_VALUE) sb.append(0).append("\n");
            else sb.append(dist[pre][post]).append("\n");
        }

        System.out.println(sb);

    }

    private static void Floyd() {
        //모든 정점(i,j)들에 대해 l정점을 들리고 갈 수 있는 경우의 수를 체크 
        for(int l=0; l<N; l++) {
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    //각 좌표들에 대해서 정방향인지 양방향인지 체크
                    if(dist[i][l] == 1 && dist[l][j] == 1) {
                        dist[i][j] = 1;
                    } else if(dist[i][l] == -1 && dist[l][j] == -1) {
                        dist[i][j] = -1;
                    }
                }
            }
        }
    }
}