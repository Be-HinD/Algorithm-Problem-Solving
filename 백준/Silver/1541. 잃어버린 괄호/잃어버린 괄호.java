import java.io.*;
import java.util.*;

//BOJ_1541 잃어버린 괄호
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int sum = Integer.MAX_VALUE;
        StringTokenizer st = new StringTokenizer(br.readLine(), "-");

        while(st.hasMoreTokens()) {
            int temp = 0;

            StringTokenizer add = new StringTokenizer(st.nextToken(), "+");

            while(add.hasMoreTokens()) {
                temp += Integer.parseInt(add.nextToken());
            }

            if (sum == Integer.MAX_VALUE) {
                sum = temp;
            }
            else {
                sum -= temp;
            }
        }

        System.out.println(sum);


    }
}