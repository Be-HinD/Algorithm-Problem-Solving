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

        /**
         * i번째 열에서 채워져야 하는 빗물의 칸 수 == Math.min(왼쪽 최대값, 오른쪽 최대값)
         * 전제조건 : 현재 칸 수 보다 왼쪽/오른쪽 최대값들이 커야함.
         * 사용할 수 있는 스킬 : 현재 i원소의 왼쪽/오른쪽 최대값을 구하기 위한 누적합
         * **/
        int[] arr = new int[w];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<w; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        //왼쪽 최대값 배열
        int[] leftMax = new int[w];
        leftMax[0] = arr[0];
        for(int i=1; i<w; i++) {
            leftMax[i] = Math.max(arr[i], leftMax[i-1]);
        }

        //오른쪽 최대값 배열
        int[] rightMax = new int[w];
        rightMax[w-1] = arr[w-1];
        for(int i=w-2; i>=0; i--) {
            rightMax[i] = Math.max(arr[i], rightMax[i+1]);
        }

        for(int i=1; i<arr.length-1; i++) {
            if(leftMax[i-1] < arr[i] || rightMax[i+1] < arr[i]) continue;
            res += Math.min(leftMax[i-1], rightMax[i+1]) - arr[i];
        }

        System.out.println(res);

    }
}