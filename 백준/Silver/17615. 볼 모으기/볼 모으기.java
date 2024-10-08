import java.io.*;

//BOJ_17615
public class Main {
    static int N, res;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        N = Integer.parseInt(br.readLine());

        /**
         * 현재
         * **/

        char[] arr = new char[N];
        String ball = br.readLine();
        int R = 0, B = 0;
        for(int i=0; i<N; i++) {
            arr[i] = ball.charAt(i);
            if(arr[i] == 'R') R++;
            else B++;
        }

        char lastBall;
        if(arr[N-1] == 'R') lastBall = 'R';
        else lastBall = 'B';

        int lastCnt = 1;
        for(int i=N-2; i>=0; i--) {
            if(arr[i] != lastBall) break;
            else {
                lastCnt++;
            }
        }

        if(lastBall == 'R') R -= lastCnt;
        else B -= lastCnt;

        char moveBall;
        if (R <= B) {
            System.out.println(R);
        } else {
            System.out.println(B);
        }

    }
}