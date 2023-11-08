import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ_2468 안전영역
public class Main {
    static int N, num;
    static String input;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuffer sb = new StringBuffer();

        N = Integer.parseInt(br.readLine());

        //연산 시작
        Stack<Integer> stack = new Stack<>();
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            input = st.nextToken();
            switch(input) {
                case "push":
                    num = Integer.parseInt(st.nextToken());
                    stack.push(num);
                    break;
                case "pop":
                    if(stack.isEmpty()) {
                        sb.append(-1).append("\n");
                        break;
                    }
                    sb.append(stack.pop()).append("\n");
                    break;
                case "size":
                    sb.append(stack.size()).append("\n");
                    break;
                case "empty":
                    if(stack.isEmpty()) {
                        sb.append(1).append("\n");
                        break;
                    }
                    sb.append(0).append("\n");
                    break;
                case "top":
                    if(stack.isEmpty()) {
                        sb.append(-1).append("\n");
                        break;
                    }
                    sb.append(stack.peek()).append("\n");
            }
        }

        System.out.println(sb);

    }
}