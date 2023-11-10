import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ_1874 스택 수열
public class Main {
    static int N, pointer;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        N = Integer.parseInt(br.readLine());

        Stack<Integer> s = new Stack<>();

        int p = 0; //0부터 시작
        for(int i=0; i<N; i++) {
            int num = Integer.parseInt(br.readLine());

            //현재 이루어진 수열 보다 입력이 크다면
            if(num > p) {
                for(int j=p + 1; j<=num; j++) {
                    s.push(j); //입력만큼 push
                    sb.append("+").append("\n");
                }

                p = num; //수열 값 갱신
            }

            else if(s.peek() != num) {
                //스택에서 꺼낸값과 입력이 동일하지 않다면
                System.out.println("NO");
                return;
            }

            s.pop();
            sb.append("-").append("\n");

        }

        System.out.println(sb);
    }
}