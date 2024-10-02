import java.io.*;

//BOJ_2116 주사위 쌓기
public class Main {
    static class DICE {
        int A, B, C, D, E, F;

        public DICE(String[] idx) {
            A = Integer.parseInt(idx[0]);
            B = Integer.parseInt(idx[1]);
            C = Integer.parseInt(idx[2]);
            D = Integer.parseInt(idx[3]);
            E = Integer.parseInt(idx[4]);
            F = Integer.parseInt(idx[5]);
        }

        public int sideSearch(int top) {
            if(top == A) {
                return F;
            }
            else if(top == B) {
                return D;
            }
            else if(top == C) {
                return E;
            }
            else if(top == D) {
                return B;
            }
            else if(top == E) {
                return C;
            }
            else {
                return A;
            }

        }
    }
    static int N, res;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        DICE[] tower = new DICE[N];
        for(int i=0; i<N; i++) {
            tower[i] = new DICE(br.readLine().split(" "));
        }

        boolean[] max;

        for(int bottom =1; bottom <=6; bottom++) {
            int temp = 0;
            max = new boolean[7];
            max[bottom] = true;
            int top = tower[0].sideSearch(bottom);
            max[top] = true;
            temp += searchMaxValue(max);
            int prevTop = top;

            for (int i=1; i<N; i++) {   //i번 주사위 순회
                max = new boolean[7];
                max[prevTop] = true;
                top = tower[i].sideSearch(prevTop);
                max[top] = true;
                temp += searchMaxValue(max);
                prevTop = top;
            }
            res = Math.max(res, temp);
        }

        System.out.println(res);

    }

    private static int searchMaxValue(boolean[] v) {
        for(int j=6; j>=1; j--) {
            if(!v[j]) {
                return j;
            }
        }
        return -1;
    }
}