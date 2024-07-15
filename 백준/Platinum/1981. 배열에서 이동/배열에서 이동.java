import java.io.*;
import java.util.*;

//BOJ_1981 배열에서 이동
public class Main {
    static int N, res;
    static int[][] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N][N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(binarySearch());
    }

    static int binarySearch() {
        int low = 0;
        int high = Integer.MAX_VALUE;

        while(low < high) {
            final int mid = low + (high - low) / 2;

            if(bfs(mid)) {
                high = mid;
            }
            else {
                low = mid + 1;
            }

        }
        return high;
    }

    static int[] dx = new int[]{0,0,1,-1};
    static int[] dy = new int[]{1,-1,0,0};
    static boolean bfs(int max) {
        PriorityQueue<int[]> q = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return (o1[3] - o1[2]) - (o2[3] - o2[2]);
            }
        });

        q.offer(new int[]{0,0,arr[0][0],arr[0][0]});    //nx,ny,min,max

        int[][][] v = new int[N][N][201];
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                for(int k=0; k<201; k++) {
                    v[i][j][k] = 201;
                }
            }
        }

        while(!q.isEmpty()) {
            int[] cur = q.poll();

            if(cur[0] == N-1 && cur[1] == N-1) {
                int tempMin = Math.min(cur[2], arr[N-1][N-1]);
                int tempMax = Math.max(cur[3], arr[N-1][N-1]);
                int diff = tempMax - tempMin;
                if(diff <= max) return true;
                continue;
            }


            for(int i=0; i<4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if(nx<0 || ny<0 || nx>=N || ny>=N) continue;

                int tempMin = Math.min(cur[2], arr[nx][ny]);
                int tempMax = Math.max(cur[3], arr[nx][ny]);
                int diff = tempMax - tempMin;

                if(tempMax >= v[nx][ny][tempMin]) continue;


                if(diff > max) continue;

                v[nx][ny][tempMin] = tempMax;
                q.offer(new int[]{nx, ny, tempMin, tempMax});

            }
        }

        return false;
    }
}