import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

//BOJ_2661
public class Main {
    static int N;
    static String res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        /**
         * 키워드
         * 인접한 두 부분수열이 같지않은 수 중 가장 작은 수
         * 1,2,3으로만 이루어진 수열
         * 최대한 작은수로 앞의자리수를 채우기 == 그리디
         * **/

        N = Integer.parseInt(br.readLine());
        dfs("", 0);

    }

    private static void dfs(String prev, int cnt) {
        if(!isBadNum(prev)) return;

        if(cnt == N) {
            System.out.println(prev);
            System.exit(0);
        }


        //1을 추가했을 경우
        dfs(prev + "1", cnt+1);
        //2을 추가했을 경우
        dfs(prev + "2", cnt+1);
        //3을 추가했을 경우
        dfs(prev + "3", cnt+1);

    }

    private static boolean isBadNum(String num) {
        int size = num.length() - (num.length()/2);
        String last = "";
        for(int i=num.length()-1; i>=size; i--) {
            last += num.charAt(i);
            String diff = "";
            for(int j=i-1; j>=(i - last.length()); j--) {
                diff += num.charAt(j);
            }
            if(last.equals(diff)) return false;
        }

        return true;
    }
}