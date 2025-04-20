import java.io.*;
import java.util.*;

//BOJ_1863
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        /**
         * 문제 난독 존나 옴
         * 낮아지는 지점에 대해서 카운팅
         * **/

        Stack<Integer> stack = new Stack<>();

        int n = Integer.parseInt(br.readLine());

        int[][] arr = new int[n][2];
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr[i][0] = x;
            arr[i][1] = y;
        }

        Arrays.sort(arr, (o1, o2) -> o1[0] - o2[0]);
        int res = 0;
        for(int i=0; i<n; i++) {
            int[] next = arr[i];

            while (!stack.isEmpty() && stack.peek() > next[1]) {
                stack.pop();
                res++;
            }

            // 같은 높이는 넣지 않음 (변화가 없음)
            if (stack.isEmpty() || stack.peek() < next[1]) {
                stack.push(next[1]);
            }
        }

        while (!stack.isEmpty()) {
            if (stack.pop() != 0) res++;
        }

        System.out.println(res);
    }
}