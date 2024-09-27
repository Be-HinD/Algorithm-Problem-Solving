import java.io.*;
import java.util.*;

//BOJ_17952 과제는 끝나지 않아!
public class Main {
    static int N, res;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        Stack<int[]> stack = new Stack<>(); // 0 : 점수, 1 : 남은시간

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            if(t == 0) {
                if(stack.isEmpty()) continue;
                int[] task = stack.pop();
                task[1]--;
                if(task[1]==0) {
                    res += task[0];
                    continue;
                }
                stack.push(task);
            }
            else {
                int score = Integer.parseInt(st.nextToken());
                int time = Integer.parseInt(st.nextToken());
                if(time-1 == 0) {
                    res += score;
                    continue;
                }
                stack.push(new int[]{score, time-1});
            }

        }

        System.out.println(res);

    }
}