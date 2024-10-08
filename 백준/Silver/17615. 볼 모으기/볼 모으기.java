import java.io.*;

//BOJ_17615
public class Main {
    static int N, res;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        N = Integer.parseInt(br.readLine());

        String ball = br.readLine();
        int R = 0, B = 0;
        for(int i=0; i<N; i++) {
            if(ball.charAt(i) == 'R') R++;
            else B++;
        }

        char lastBall;
        if(ball.charAt(ball.length()-1) == 'R') lastBall = 'R';
        else lastBall = 'B';

        int lastCnt = 1;
        for(int i=N-2; i>=0; i--) {
            if(ball.charAt(i) != lastBall) break;
            else {
                lastCnt++;
            }
        }

        int tempA = R, tempB = B;
        if(lastBall == 'R') tempA = R - lastCnt;
        else tempB = B - lastCnt;

        res = Math.min(tempA, tempB);

        if(ball.charAt(0) == 'R') lastBall = 'R';
        else lastBall = 'B';

        lastCnt = 1;
        for(int i=1; i<ball.length(); i++) {
            if(ball.charAt(i) != lastBall) break;
            else {
                lastCnt++;
            }
        }

        if(lastBall == 'R') {
            R -= lastCnt;
            res = Math.min(res, R);
        }
        else {
            B -= lastCnt;
            res = Math.min(res, B);
        }

        System.out.println(res);

    }
}