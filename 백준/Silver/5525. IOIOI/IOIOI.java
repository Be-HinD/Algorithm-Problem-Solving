import java.io.*;
import java.util.*;

//BOJ_5525
class Main {
    static int N, M, res;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        /**
         * 접근법
         *
         * **/

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        char[] s = br.readLine().toCharArray();

        int p = 2*N + 1;
        for(int i=0; i<s.length; i++) {
            if(s[i] == 'I') {
                char prev = 'I';
                int cnt = 1;
                int pointer = i+1;
                for(int j=i+1; j<s.length; j++) {
                    pointer = j;
                    if(prev == s[j]) { //false
                        break;
                    }
                    prev = s[j];
                    cnt++;
                }
                if(cnt >= p) {
                    res += ((cnt - p) / 2) + 1;
                }
                i = pointer-1;
            }
        }

        System.out.println(res);

    }
}