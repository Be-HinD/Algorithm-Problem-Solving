import java.io.*;
import java.util.*;

//BOJ_16953 A -> B
public class Main {
    static int input, target, res;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        input = Integer.parseInt(st.nextToken());

        target = Integer.parseInt(st.nextToken());

        res = Integer.MAX_VALUE;

        dfs(input, 0);

        System.out.println(res == Integer.MAX_VALUE ? -1 : res + 1);

    }

    static void dfs(long x, int cnt) {

        //기저조건
        if(cnt > res) return;
        if(x > target) return;
        if(x == target) {
            res = cnt;
        }
        // x < target
        //동작
        //*2
        dfs(x * 2, cnt + 1);
        //1 추가
        String y = x + "1";
        dfs(Long.parseLong(y), cnt + 1);


    }
}