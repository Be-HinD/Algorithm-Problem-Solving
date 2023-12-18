import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ_9251 LCS
public class Main {
    static String a, b;
    static char[] arr1, arr2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
        a = br.readLine();
        arr1 = new char[a.length()+1];
        for(int i=0; i<a.length(); i++) {
            arr1[i+1] = a.charAt(i);
        }
        b = br.readLine();
        arr2 = new char[b.length()+1];
        for(int i=0; i<b.length(); i++) {
            arr2[i+1] = b.charAt(i);
        }
        int[][] dp = new int[a.length()+1][b.length()+1];
        for(int i=1; i<=a.length(); i++) {
            for(int j=1; j<=b.length(); j++) {
                if(arr1[i] == arr2[j]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
                else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        int x = a.length();
        int y = b.length();
        String res = "";
        
        while(x != 0 && y != 0) {
            if(dp[x-1][y] == dp[x][y]) {
                x -= 1;
            }
            else if(dp[x][y-1] == dp[x][y]) {
                y -= 1;
            }
            else {
                res += arr1[x];
                x -= 1;
                y -= 1;
            }
        }

        String lcs = "";
        for(int i=res.length()-1; i>=0; i--) {
            lcs += res.charAt(i);
        }

        System.out.println(dp[arr1.length-1][arr2.length-1]);
        System.out.println(lcs);
    }
}