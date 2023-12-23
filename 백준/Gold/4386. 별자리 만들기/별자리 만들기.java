import java.io.*;
import java.util.*;

//BOJ_4386 별자리 만들기
public class Main {
    static int N;
    static double[][] map, dist;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); //별의 개수
        //N이 최대 100개로 2차원 배열로 입력
        map = new double[N][2];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            map[i][0] = Double.parseDouble(st.nextToken());
            map[i][1] = Double.parseDouble(st.nextToken());
        }

        dist = new double[N][N];
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(i==j) continue;
                double distance = Math.sqrt(Math.pow(map[i][0] - map[j][0], 2)
                        + Math.pow(map[i][1] - map[j][1], 2));
                dist[i][j] = distance;
                dist[j][i] = distance;
            }
        }
        //MST Prim으로 풀이

        double res = Prim(0);

        System.out.printf("%.2f", res);

    }

    private static double Prim(int start) {
        PriorityQueue<double[]> pq = new PriorityQueue<>(new Comparator<double[]>() {
            @Override
            public int compare(double[] o1, double[] o2) {
                return (int) (o1[1] - o2[1]);
            }
        });
        boolean[] v = new boolean[N];
        pq.offer(new double[]{start,0});

        double res = 0;
        while(!pq.isEmpty()) {
            double[] cur = pq.poll();
            int vertex = (int) cur[0];

            if(v[vertex]) continue;

            v[vertex] = true;
            res += cur[1];

            for(int i=0; i<N; i++) {
                if(v[i]) continue;
                pq.offer(new double[]{i, dist[vertex][i]});
            }
        }
        return res;
    }
}