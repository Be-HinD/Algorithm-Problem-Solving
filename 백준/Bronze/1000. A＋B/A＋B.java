import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
	static int A;
	static int B;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        sb.append(A+B);
        System.out.println(sb);
    }
}