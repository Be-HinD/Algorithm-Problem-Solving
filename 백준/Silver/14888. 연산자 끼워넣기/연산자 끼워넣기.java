import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ_14888
public class Main {
    static int N, minRes, maxRes;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());

        arr = new int[N];
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] oper = new int[4];
        st = new StringTokenizer(br.readLine());

        for(int i=0; i<4; i++) {
            oper[i] = Integer.parseInt(st.nextToken());
        }

        // N == 11, 완탐 == dfs

        minRes = 1000000055;
        maxRes = -1000000055;

        dfs(arr[0], 1, oper);

        System.out.println(maxRes +"\n" + minRes);

    }
    private static void dfs(int prev, int cnt, int[] oper) {
        if(cnt == N) {    //기저조건
            minRes = Math.min(minRes, prev);
            maxRes = Math.max(maxRes, prev);
        }

        for(int i=0; i<4; i++) {
            if(oper[i] == 0) continue;
            int[] temp = oper.clone();
            if(i==0) {
                temp[i]--;
                dfs(prev+arr[cnt], cnt+1, temp);
            }
            else if(i==1) {
                temp[i]--;
                dfs(prev-arr[cnt], cnt+1, temp);
            }
            else if(i==2) {
                temp[i]--;
                dfs(prev*arr[cnt], cnt+1, temp);
            }
            else {
                temp[i]--;
                dfs(prev/arr[cnt], cnt+1, temp);
            }
        }

    }
}