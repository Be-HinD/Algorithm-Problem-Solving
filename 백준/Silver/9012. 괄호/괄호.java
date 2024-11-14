import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

//BOJ_22115
public class Main {
    static int N, S, res;
    static int[] arr;
    static boolean[] v;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        while(N-- > 0) {
            String s = br.readLine();

            if(isBalanced(s) && isCorrect(s)) {
                System.out.println("YES");
            }
            else {
                System.out.println("NO");
            }
        }
    }
    private static boolean isCorrect(String s) {
        int left = 0;

        for(char idx : s.toCharArray()) {
            if(idx == ')' && left == 0) return false;
            if(idx == ')' && left > 0) left--;
            if(idx == '(') left++;
        }

        return true;
    }

    private static boolean isBalanced(String s) {
        int left = 0, right = 0;
        for(char idx : s.toCharArray()) {
            if(idx == '(') left++;
            else right++;
        }

        if(left == right) return true;
        return false;
    }
}