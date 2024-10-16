import java.io.*;
import java.util.*;

//BOJ_15661
public class Main {
    static int N, res;
    static int[] arr;
    static int[][] map;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        res = Integer.MAX_VALUE;
        for(int i=1; i<=N/2; i++) {
            arr = new int[i];
            Comb(0, 0);

        }

        System.out.println(res);

    }

    static boolean[] v;
    private static void Comb(int start, int cnt) {
        if(cnt == arr.length) {
            //조합
            v = new boolean[N];
            for(int i=0; i<arr.length; i++) {
                v[arr[i]] = true;
            }
            calc();
            return;
        }
        for(int i=start; i<N; i++) {
            arr[cnt] = i;
            Comb(i+1, cnt+1);
        }
    }

    private static void calc() {
        int red = 0;
        for(int i=0; i<arr.length; i++) {
            int cur = arr[i];
            for(int j=i+1; j<arr.length; j++) {
                int diff = arr[j];
                red += map[cur][diff];
                red += map[diff][cur];
            }
        }

        int[] blueTeam = new int[N-arr.length];
        int p = 0;
        for(int i=0; i<v.length; i++) {
            if(!v[i]) blueTeam[p++] = i;
        }

        int blue = 0;
        for(int i=0; i<blueTeam.length; i++) {
            int cur = blueTeam[i];
            for(int j=i+1; j<blueTeam.length; j++) {
                int diff = blueTeam[j];
                blue += map[cur][diff];
                blue += map[diff][cur];
            }
        }

        res = Math.min(res, Math.abs(red - blue));
    }
}