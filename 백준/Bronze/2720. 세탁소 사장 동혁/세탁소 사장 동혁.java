import java.io.*;

public class Main {
    static int[] unit = new int[]{25, 10, 5, 1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            int cent = Integer.parseInt(br.readLine());

            for(int i=0; i<4; i++) {
                int idx = cent / unit[i];
                cent = cent - (unit[i] * idx);
                sb.append(idx).append(" ");
            }

            sb.append("\n");

        }

        System.out.println(sb);
    }
}