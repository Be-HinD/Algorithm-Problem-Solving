    import java.io.*;
    import java.util.*;

    //BOJ_13164
    public class Main {
        static int N, res;
        static int[] arr;
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st;

            N = Integer.parseInt(br.readLine());

            arr = new int[N];

            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int[][] dp = new int[N][2];

            dp[0][0] = arr[0];
            dp[0][1] = arr[0];
            
            res = arr[0];
            for(int i=1; i<N; i++) {
                dp[i][0] = Math.max(dp[i-1][0] + arr[i], arr[i]);
                dp[i][1] = Math.max(dp[i-1][0], dp[i-1][1]+arr[i]);
                res = Math.max(res, dp[i][0]);
                res = Math.max(res, dp[i][1]);
            }

            System.out.println(res);

        }
    }