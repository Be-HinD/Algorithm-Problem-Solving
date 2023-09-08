import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, U, L;
    static int cnt;
    static boolean check;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        if(N > 999) {
            check = true;
            cnt++;
        }
        if(U>7999 || L>259) cnt += 2;
        if(cnt == 3) System.out.println("Very Good");
        else if(cnt==1 && check) System.out.println("Good");
        else System.out.println("Bad");
    }
}
