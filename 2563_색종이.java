import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] map = new int[100][100]; //고정맵
    static int N, x, y;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        for(int i=0; i<N; i++) { // 반복동안 x,y좌표부터 10by10 공간 1로 할당
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            for(int k=x; k<x+10; k++) {
                for(int l=y; l<y+10; l++) {
                    map[k][l] = 1;
                }
            }
        }
        int sum = 0;
        for(int i=0; i<100; i++) { //배열을 완전탐색하면서 1인공간의 합을 구함
            for(int j=0; j<100; j++) {
                if(map[i][j] == 1) sum++;
            }
        }
        System.out.println(sum);
    }
}
