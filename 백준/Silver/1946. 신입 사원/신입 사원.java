import java.io.*;
import java.util.*;

//BOJ_1946
class Main {
    static int T, N, res;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        /**
         * 접근법
         * 동석차 x
         * 두 개의 조건중에
         * **/

        T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            N = Integer.parseInt(br.readLine()); //지원자 수
            int[][] arr = new int[N][2];
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                int resumeScore = Integer.parseInt(st.nextToken());
                int interviewScore = Integer.parseInt(st.nextToken());
                arr[i][0] = resumeScore;
                arr[i][1] = interviewScore;
            }

            Arrays.sort(arr, (o1, o2) -> o1[0] - o2[0]); //서류기준 오름차순

            //바로 이전 등수의 사람과 다른 성적을 비교해서 떨어지면 두 개다 떨어지는 경우
            int[] prev = new int[N];
            prev[0] = arr[0][1];
            int res = N;
            for(int i=1; i<N; i++) {
                if(prev[i-1] < arr[i][1]) { //i지원자의 인터뷰 점수가 더 낮다면 두 점수 모두 낮은 경우
                    res--;
                }
                prev[i] = Math.min(prev[i-1], arr[i][1]);
            }

            sb.append(res).append("\n");
        }

        System.out.println(sb);

    }
}