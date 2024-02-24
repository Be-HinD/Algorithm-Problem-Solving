import java.io.*;
import java.util.*;

//BOJ_4949 균형잡힌 세상
public class Main {
    static int N, M;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while(true) {
            String input = br.readLine();
            if(input.charAt(0) == '.') break;

            Stack<Character> stack = new Stack<>();

            boolean flag = false;
            for(int i=0; i<input.length(); i++) {
                char idx = input.charAt(i);

                if(idx == ')' || idx == ']') {
                    if(stack.isEmpty()) {
                        flag = true;
                        sb.append("no").append("\n");
                        break;
                    }

                    char prev = stack.pop();
                    if(idx == ')') {
                        if(prev != '(') {
                            flag = true;
                            sb.append("no").append("\n");
                            break;
                        }
                    }
                    else {
                        if(prev != '[') {
                            flag = true;
                            sb.append("no").append("\n");
                            break;
                        }
                    }
                }
                else if(idx == '(' || idx == '[') {
                    stack.push(idx);
                }
            }

            if(flag) {
                continue;
            }
            if(stack.isEmpty()) {
                sb.append("yes").append("\n");
            }
            else {
                sb.append("no").append("\n");
            }
        }

        System.out.println(sb);
    }
}