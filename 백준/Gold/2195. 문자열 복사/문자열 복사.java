import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

//BOJ_2195
public class Main {
    static int res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String p = br.readLine();

        int idx = 0;
        
        for (int i = 0; i < p.length(); i++) {
            if (s.indexOf(p.substring(idx, i+1)) != -1) continue;
            res++;
            idx = i;
        }
        System.out.println(res+1);

    }
}
