    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.*;

    //BOJ_2504 괄호의 값
    public class Main {
        static int N, M, res;
        public static void main(String[] args) throws IOException {

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    //        StringTokenizer st = new StringTokenizer(br.readLine());

            String input = br.readLine();

            Stack<Character> stack = new Stack<>();

            int temp = 1;
            for(int i=0; i<input.length(); i++) {
                char idx = input.charAt(i);

                if(idx == '(') {
                    stack.push(idx);
                    temp *= 2;
                }
                if(idx == '[') {
                    stack.push(idx);
                    temp *= 3;
                }

                else {
                    //닫는 괄호의 경우
                    if(stack.isEmpty()) {
                        System.out.println(0);
                        return;
                    }

                    if (idx == ')') {
                        if (stack.peek() == '(') {

                            if (input.charAt(i-1) == '(') {
                                res += temp;
                            }

                            stack.pop();
                            temp /= 2;

                        } else {
                            System.out.println(0);
                            return;
                        }
                    } else if (idx == ']') {
                        if (stack.peek() == '[') {

                            if (input.charAt(i-1) == '[') {
                                res += temp;
                            }
                            temp /= 3;
                            stack.pop();
                        } else {
                            System.out.println(0);
                            return;
                        }
                    }
                }
            }

            if(!stack.isEmpty()) {
                System.out.println(0);
                return;
            }
            
            System.out.println(res);

        }
    }