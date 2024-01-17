import java.io.*;
import java.util.*;

//BOJ_9935 문자열 폭발
public class Main {
    static String input, boom;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        input = br.readLine();
        boom = br.readLine();
        Stack<Character> stack = new Stack<>();
        for(int i=0; i<input.length(); i++) {
            stack.push(input.charAt(i));
            if(stack.size() >= boom.length()) {
                boolean flag = true;
                for(int j=0; j<boom.length(); j++) {
                    if(stack.get(stack.size() - boom.length() + j) != boom.charAt(j)) {
                        flag = false;
                        break;
                    }
                }
                if(flag) {
                    for(int j=0; j<boom.length(); j++) stack.pop();
                }
            }
        }
        for(char idx : stack) sb.append(idx);
        System.out.println(sb.length() > 0 ? sb.toString() : "FRULA");
    }
}