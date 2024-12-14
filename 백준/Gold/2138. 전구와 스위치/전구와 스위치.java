import java.io.*;
import java.util.*;

//BOJ_2138
class Main {
    static int N, res;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        /**
         * 접근법
         * N <= 100,000, 단순 dfs로 풀면 터짐
         *
         * **/

        N = Integer.parseInt(br.readLine());
        res = Integer.MAX_VALUE;
        char[] origin = br.readLine().toCharArray();
        char[] cur = br.readLine().toCharArray();

        char[] arr = new char[2];
        arr[0] = '1'; arr[1] = '0';
        char[] temp = origin.clone();
        temp[0] = arr[temp[0] - '0'];
        temp[1] = arr[temp[1] - '0'];
        int cnt = 1;
        for(int i=1; i<N; i++) {
            if(temp[i-1] == cur[i-1]) continue;
            temp[i-1] = arr[temp[i-1] - '0'];
            temp[i] = arr[temp[i] - '0'];
            if(i+1 != N) temp[i+1] = arr[temp[i+1] - '0'];
            cnt++;
        }
        if(temp[N-1] == cur[N-1]) {
            res = cnt;
        }

        temp = origin.clone(); cnt = 0;

        for(int i=1; i<N; i++) {
            if(temp[i-1] == cur[i-1]) continue;
            temp[i-1] = arr[temp[i-1] - '0'];
            temp[i] = arr[temp[i] - '0'];
            if(i+1 != N) temp[i+1] = arr[temp[i+1] - '0'];
            cnt++;
        }
        if(temp[N-1] == cur[N-1]) {
            res = Math.min(res, cnt);
        }

        System.out.println(res == Integer.MAX_VALUE ? -1 : res);

    }
}