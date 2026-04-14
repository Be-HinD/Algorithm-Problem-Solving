import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        
        StringBuilder sb = new StringBuilder();
        while(n-->0) {
            int idx = Integer.parseInt(st.nextToken());
            if(idx < x) {
                sb.append(idx).append(" ");
            }
        }

        System.out.println(sb.toString());
    }
}