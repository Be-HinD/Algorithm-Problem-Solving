    import java.io.*;
    import java.util.*;

    //BOJ_13164
    public class Main {
        static final int mod = 1000000;
        static int res;
        static char[] arr;
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st;

            String code = br.readLine();

            arr = code.toCharArray();

            int[] dp = new int[5001];

            if(arr[0] == '0') {
                System.out.println(0);
                return;
            }
            dp[0] = 1;
            dp[1] = 1;

            for(int i=2; i<=code.length(); i++) {
                if(arr[i-1]-'0' != 0) dp[i] = dp[i-1] % mod;
                String idx = String.valueOf(arr[i-2]) + String.valueOf(arr[i-1]);
                if(Integer.parseInt(idx) > 9 & Integer.parseInt(idx) < 27) {
                    dp[i] = (dp[i] + dp[i-2]) % mod;
                }
            }

            System.out.println(dp[code.length()]);
        }
    }