import java.io.*;
import java.util.StringTokenizer;

//BOJ_2563 색종이
public class Main {
    static int N, res;
    static boolean[][] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new boolean[101][101];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            draw(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        for(int i=0; i<101; i++) {
            for(int j=0; j<101; j++) {
                if(arr[i][j]) res++;
            }
        }


        System.out.println(res);

    }

    private static void draw(int y, int x) {
        for(int i=x; i<x+10; i++) {
            for(int j=y; j<y+10; j++) {
                arr[i][j] = true;
            }
        }
    }
}