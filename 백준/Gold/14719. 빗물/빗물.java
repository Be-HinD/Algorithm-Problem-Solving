import java.io.*;
import java.util.*;

//BOJ_14719
public class Main {
    static int h, w, res;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        int[] arr = new int[w];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<w; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=1; i<arr.length-1; i++) {
            // i를 기준으로 얼마나 채워져야 하는지 계산
            int leftMax = arr[i];
            for(int j=i-1; j>=0; j--) {
                leftMax = Math.max(leftMax, arr[j]);
            }

            int rightMax = arr[i];
            for(int j=i+1; j<arr.length; j++) {
                rightMax = Math.max(rightMax, arr[j]);
            }

            res += Math.min(leftMax, rightMax) - arr[i];
        }

        System.out.println(res);

    }
}