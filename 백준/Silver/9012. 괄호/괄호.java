    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.*;

    //BOJ_9012 괄호
    public class Main {
        static int N;

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    //        StringTokenizer st = new StringTokenizer(br.readLine());
            StringBuilder sb = new StringBuilder();

            N = Integer.parseInt(br.readLine());

            for(int i=0; i<N; i++) {
                String input = br.readLine();
                boolean flag = true;
                Stack<Character> stack = new Stack<>();
                for(int j=0; j<input.length(); j++) {
                    char idx = input.charAt(j);

                    if(idx == '(') {
                        stack.push(idx);
                    }

                    else
                    {
                        if(stack.isEmpty()) {
                            flag = false;
                            break;
                        }

                        if(stack.peek() == '(') {
                            stack.pop();
                        }
                    }
                }

                if(flag && stack.isEmpty()) {
                    sb.append("YES").append("\n");
                    continue;
                }
                sb.append("NO").append("\n");
            }


            System.out.println(sb);
        }
    }