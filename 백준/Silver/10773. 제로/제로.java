import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ_10773 제로
public class Main {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        Stack<Integer> s = new Stack<>();
        for(int i=0; i<N; i++) {
            int num = Integer.parseInt(br.readLine());
            if(num == 0) s.pop();
            else s.push(num);
        }

        int res = 0;
        while(!s.isEmpty()) {
            res += s.pop();
        }

        System.out.println(res);

    }
}