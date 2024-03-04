import java.io.*;

//BOJ_2839 설탕 배달
public class Main {
    static int N, res;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        //5를 한번씩 빼면서 매번 3으로 나뉘어지는지 확인
        // 11을 예시로 들면 5를 한번 빼면 6이남고 6때는 3으로 나눠지니까 임시 기록
        // 6에서 5를 한번 더 빼면 1이 남으니까 더 이상 계산이 되지않으니 임시 기록했던 값을 도출

        int temp = -1;
        int cnt = 0;

        if(N % 5 == 0) {
            System.out.println(N/5);
            return;
        }

        while(N > -1) {
            if(N%3 == 0) {
                //시작부터 3으로 나누어지는 경우
                temp = (N/3) + cnt; //임시 기록
            }
            if(N >= 5) {
                //N이 5 이상이라면
                N -= 5;
                cnt++;
            }
            else {
                //N이 5이하라면
                N -= 3;
                cnt++;
            }
            if(N < 3) break;
        }

        System.out.println(temp);
    }
}