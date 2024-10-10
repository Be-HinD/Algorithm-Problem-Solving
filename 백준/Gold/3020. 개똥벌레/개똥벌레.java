import java.io.*;
import java.util.*;

//BOJ_3020
public class Main {
    static int N, H;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());   //높이

        int[] left = new int[H+1];    //종유석
        int[] right = new int[H+1];   //석순

        for(int i=0; i<N; i++) {
            int idx = Integer.parseInt(br.readLine());
            if(i % 2 == 0) {
                //석순 left
                left[idx]++;
            }
            else {
                right[H+1-idx]++;
            }
        }
        for(int i=H-1; i>0; i--) {
            left[i] += left[i+1];
        }

        for(int i=1; i<=H; i++) {
            right[i] += right[i-1];
        }

        int[] res = new int[H+1];

        int min = Integer.MAX_VALUE;
        for(int i=1; i<=H; i++) {
            res[i] = left[i] + right[i];
            min = Math.min(min, res[i]);
        }

        int ans = 0;
        for(int i=1; i<=H; i++) {
            if(res[i] == min) ans++;
        }

        System.out.println(min + " " + ans);

    }
}