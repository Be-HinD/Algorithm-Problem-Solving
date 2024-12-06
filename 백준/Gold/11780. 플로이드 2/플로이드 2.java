import java.io.*;
import java.util.*;

//BOJ_10159
class Main {
    static class Stopover {
        List<Integer> list = new ArrayList<>();
    }
    static int N, M, res;
    static int[][] map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        /**
         * 접근법
         *
         * **/

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        map = new int[N+1][N+1];
        for(int i=0; i<=N; i++) Arrays.fill(map[i], 1000000);
        for(int i=1; i<=N; i++) map[i][i] = 0;

        Stopover[][] list = new Stopover[N+1][N+1];

        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
                list[i][j] = new Stopover();
            }
        }
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            map[s][e] = Math.min(map[s][e],w);
        }


        for(int k=1; k<=N; k++) {
            for(int i=1; i<=N; i++) {
                for(int j=1; j<=N; j++) {
                    int diff = map[i][k] + map[k][j];

                    if(diff < map[i][j]) {

                        map[i][j] = diff;
                        //갱신이 되는 시점에
                        list[i][j].list.clear();
                        for(int idx : list[i][k].list) {
                            list[i][j].list.add(idx);
                        }
                        list[i][j].list.add(k);
                        for(int idx : list[k][j].list) {
                            list[i][j].list.add(idx);
                        }
                    }
                }
            }
        }


        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
                if(map[i][j] == 1000000) {
                    sb.append(0).append(" ");
                    continue;
                }
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }

        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
                if(i==j || map[i][j] == 1000000) sb.append(0).append("\n");
                else {
                    sb.append(2 + list[i][j].list.size()).append(" ");
                    sb.append(i).append(" ");
                    for(int idx : list[i][j].list) {
                        sb.append(idx).append(" ");
                    }
                    sb.append(j).append("\n");
                }
            }
        }

        System.out.println(sb);

    }
}