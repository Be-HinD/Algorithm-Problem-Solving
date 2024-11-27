import java.io.*;
import java.util.*;

//BOJ_2812
public class Main {
    static int N, M, res;
    static char[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        /**
         * 키워드
         * 가장 많은 고층 빌딩이 보이는 고층 빌딩을 찾으려고 한다
         * 접근법
         * 전형적인 스택문제
         * **/

        N = Integer.parseInt(st.nextToken()); //빌딩 수
        M = Integer.parseInt(st.nextToken());
        arr = br.readLine().toCharArray();

        Stack<Character> stack = new Stack<>();

        for(char idx : arr) {
            while(!stack.isEmpty() && M != 0) {
                if((stack.peek() - '0') < (idx-'0')) {
                    stack.pop();
                    M--;
                    continue;
                }
                break;
            }
            stack.push(idx);
        }

        while(M-- > 0) {
            stack.pop();
        }

        StringBuilder sb = new StringBuilder();
        for(char idx : stack) {
            sb.append(idx);
        }

        System.out.println(sb);

    }
}