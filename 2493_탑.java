import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main{
    static int N;
    static Stack<peek_idx> stack;
    static int num = 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        stack = new Stack<peek_idx>();
        st = new StringTokenizer(br.readLine());
        peek_idx idx;
        for(int i=0; i<N;i++) {
            idx= new peek_idx(Integer.parseInt(st.nextToken()), num++);
            if(stack.isEmpty()) {
                System.out.print(0 + " ");
                stack.push(idx);
            } else {
                while(true) {
                    if(stack.isEmpty()) {
                        sb.append(0 + " ");
                        stack.push(idx);
                        break;
                    }
                    if(stack.peek().height >= idx.height) {
                        sb.append(stack.peek().num + " ");
                        stack.push(idx);
                        break;
                    } else {
                        stack.pop();
                    }
                }
            }
        }
        System.out.println(sb);
    }
}
class peek_idx{
    int num;
    int height;
    peek_idx(int height, int num){
        this.height = height;
        this.num = num;
    }
}
