import java.io.*;

//BOJ_5585
public class Main {
    static int[] coin = new int[]{500, 100, 50, 10, 5, 1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int pay = 1000 - Integer.parseInt(br.readLine());

        int res = 0;
        for(int i=0; i<coin.length; i++) {
            if(pay < coin[i]) continue;
            res += pay / coin[i];
            pay %= coin[i];
        }

        System.out.println(res);

    }
}