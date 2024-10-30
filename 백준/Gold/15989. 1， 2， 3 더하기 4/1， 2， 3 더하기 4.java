    import java.io.*;
    import java.util.*;

    //BOJ_13164
    public class Main {
        static int T;
        static int[] arr;
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st;
            StringBuilder sb = new StringBuilder();

            T = Integer.parseInt(br.readLine());

            while(T-- > 0) {
                int N = Integer.parseInt(br.readLine());

                int[] dp = new int[N+1];
                Arrays.fill(dp, 1);

                for(int i=2; i<=N; i++) {
                    dp[i] += dp[i-2];
                }

                for(int i=3; i<=N; i++) {
                    dp[i] += dp[i-3];
                }

                sb.append(dp[N]).append("\n");
            }

            System.out.println(sb);

        }
    }