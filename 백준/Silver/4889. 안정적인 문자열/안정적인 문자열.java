import java.io.*;
import java.util.*;

//BOJ_4889
public class Main {
    static int T, res;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true) {
            res = 0;
            T++;
            String cur = br.readLine();
            if(cur.charAt(0) == '-') break;

            int openCnt = 0;
            for(char idx : cur.toCharArray()) {

                if(idx == '{') {
                    openCnt++;
                }
                else {
                    if (openCnt == 0) {
                        res++;
                        openCnt++;
                        continue;
                    }
                    openCnt--;
                }
            }
            res += openCnt/2;
            sb.append(T).append(". ").append(res).append("\n");


        }

        System.out.println(sb);
    }
}