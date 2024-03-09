import java.io.*;

//BOJ_1439 뒤집기
public class Main {
    static String N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = br.readLine();

        //연속된 숫자 탐색
        int zeroCnt = 0;
        int firstCnt = 0;
        int prev = 0;
        prev = N.charAt(0) - '0';
        for(int i=1; i<N.length(); i++) {
            int idx = N.charAt(i) - '0';
            if(prev != idx) {
                if(prev == 0) zeroCnt++;
                else firstCnt++;
            }
            prev = idx;
        }

        if(prev == 0) zeroCnt++;
        else firstCnt++;

        if(zeroCnt==0 && firstCnt==0) System.out.println(0);
        else {
            int res = Math.min(zeroCnt, firstCnt);
            System.out.println(res);
        }

    }
}