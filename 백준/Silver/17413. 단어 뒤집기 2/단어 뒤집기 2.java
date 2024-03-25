import java.io.*;
import java.util.*;

//BOJ_17413 단어 뒤집기 2
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();

        StringBuilder sb = new StringBuilder();

        Stack<Character> stack = new Stack<>();
        for(int i=0; i<input.length(); i++) {
            char idx = input.charAt(i);

            if(idx == '<') {
                //지금껏 나온 값들을 담기
                while(!stack.isEmpty()) {
                    sb.append(stack.pop());
                }
                sb.append(idx);
                while(true) {
                    i++;
                    char next = input.charAt(i);
                    if(next == '>') {
                        sb.append(next);
                        break;
                    }
                    sb.append(next);
                }
                continue;
            }
            else if(idx == ' ') {
                while(!stack.isEmpty()) {
                    sb.append(stack.pop());
                }
                sb.append(idx);
                continue;
            }
            stack.push(idx);
        }


        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        System.out.println(sb);

    }
}